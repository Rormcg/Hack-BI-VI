import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.geom.Point2D;

public class Flight {
   private boolean showName;
   private final Point2D.Double POSITION;
   private final Location LOCATION_1;
   private final Location LOCATION_2;
   private final Point2D.Double VELOCITY;
   private final int SIZE;
   private final String NAME;
   private BufferedImage plane;
   private boolean flightEnded;
   
   Flight(Location l1, Location l2) {
      flightEnded = false;
      showName = false;
      LOCATION_1 = l1;
      LOCATION_2 = l2;
      POSITION = new Point2D.Double(LOCATION_1.getPOSITION().x, LOCATION_1.getPOSITION().y);
      VELOCITY = new Point2D.Double((LOCATION_2.getPOSITION().x - LOCATION_1.getPOSITION().x), LOCATION_2.getPOSITION().y - LOCATION_1.getPOSITION().y);
      VELOCITY.setLocation(VELOCITY.x / Math.sqrt(VELOCITY.x * VELOCITY.x + VELOCITY.y * VELOCITY.y), VELOCITY.y / Math.sqrt(VELOCITY.x * VELOCITY.x + VELOCITY.y * VELOCITY.y));
      VELOCITY.setLocation(VELOCITY.x, VELOCITY.y);
      SIZE = 16;
      NAME = LOCATION_1.getNAME() + " - " + LOCATION_2.getNAME();
      
      try {
      plane = ImageIO.read(new File("src/plane.png"));
      } catch (IOException ignored) {}
   }
   
   public void draw(Graphics g) {
      if(showName) {
         
         Point2D.Double traveler = new Point2D.Double(LOCATION_1.getPOSITION().x, LOCATION_1.getPOSITION().y);
         Point2D.Double traveler2 = new Point2D.Double();
         int count = 0;
         while(traveler.x != LOCATION_2.getPOSITION().x) {
            count ++;
            g.setColor(new Color(52, 194, 13));
            traveler2.setLocation(traveler.x + (VELOCITY.x * 30), traveler.y + (VELOCITY.y * 30));
            if(Math.sqrt(Math.pow(traveler.x - LOCATION_2.getPOSITION().x, 2) + Math.pow(traveler.y - LOCATION_2.getPOSITION().y, 2)) >
            Math.sqrt(Math.pow(traveler.x - traveler2.x, 2) + Math.pow(traveler.y - traveler2.y, 2))) {
               if(count % 2 == 0) {
                  g.drawLine((int)traveler.x, (int)traveler.y, (int)traveler2.x, (int)traveler2.y);
               }
               traveler.setLocation(traveler2.x, traveler2.y);
            } else {
               //System.out.println("X");
               if(count % 2 == 0) {
                  g.drawLine((int)traveler.x, (int)traveler.y, LOCATION_2.getPOSITION().x, LOCATION_2.getPOSITION().y);
               }
               traveler.setLocation(LOCATION_2.getPOSITION().x, LOCATION_2.getPOSITION().y);
            }
         }
      }
      
      Graphics2D g2D = (Graphics2D) g;
      g2D.translate((int)(POSITION.x), (int)(POSITION.y));
      g2D.rotate(getDirection(VELOCITY.x, VELOCITY.y));
      
      g.drawImage(plane, (int)(-SIZE * 1.4), (int)(-SIZE * 1.2), SIZE * 3, SIZE * 3, null);
      
      g2D.rotate(-getDirection(VELOCITY.x, VELOCITY.y));
      g2D.translate(-(int)(POSITION.x), -(int)(POSITION.y));

      if(showName) {
         g.setColor(new Color(52, 194, 13));
         g.fillRect((int)(POSITION.x - SIZE / 2.0), (int)(POSITION.y - SIZE * 1.7), NAME.length() * 8, 20);
         g.setFont(new Font("sans-serif", Font.BOLD, 15));
         g.setColor(Color.BLACK);
         g.drawString(NAME, (int)(POSITION.x - SIZE * 0.4), (int)(POSITION.y - SIZE * 0.8));
      }
   }
   
   public void update(double speed) {
      if(Math.sqrt(Math.pow(POSITION.x - LOCATION_2.getPOSITION().x, 2) + Math.pow(POSITION.y - LOCATION_2.getPOSITION().y, 2)) >
      Math.sqrt(Math.pow(POSITION.x - POSITION.x - VELOCITY.x * speed, 2) + Math.pow(POSITION.y - POSITION.y - VELOCITY.y * speed, 2))) {
         POSITION.setLocation(POSITION.x + VELOCITY.x * speed, POSITION.y + VELOCITY.y * speed);
      } else {
         flightEnded = true;
      }
   }
   
   public void setShowName(boolean a) {
      showName = a;
   }
   
   public Point2D.Double getPOSITION() {
      return POSITION;
   }
   
   public int getSIZE() {
      return SIZE;
   }

   public boolean getShowName() {
      return showName;
   }

   public boolean getFlightEnded() {
      return flightEnded;
   }
   
   private double getDirection(double x, double y) {
      double dir = Math.atan(y / x) * 180 / Math.PI;
      
      if((x < 0 && y > 0) || (x < 0 && y < 0)) {
         dir += 270;
      } else  {
         dir += 90;
      } 
      return dir * (Math.PI / 180);
   }
}