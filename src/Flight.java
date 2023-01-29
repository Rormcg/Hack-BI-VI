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
   private Point2D.Double position;
   private Location loc1;
   private Location loc2;
   private Point2D.Double velocity;
   private int size;
   private String name;
   private BufferedImage plane;
   private boolean flightEnded;
   
   Flight(Location l1, Location l2) {
      flightEnded = false;
      showName = false;
      loc1 = l1;
      loc2 = l2;
      position = new Point2D.Double(loc1.getPosition().x, loc1.getPosition().y);
      velocity = new Point2D.Double((loc2.getPosition().x - loc1.getPosition().x), loc2.getPosition().y - loc1.getPosition().y);
      velocity.setLocation(velocity.x / Math.sqrt(velocity.x * velocity.x + velocity.y * velocity.y), velocity.y / Math.sqrt(velocity.x * velocity.x + velocity.y * velocity.y));
      velocity.setLocation(velocity.x, velocity.y);
      size = 16;
      name = loc1.getName() + " - " + loc2.getName();
      
      try {
      plane = ImageIO.read(new File("src/plane.png"));
      } catch (IOException e) {}
   }
   
   public void draw(Graphics g) {
      if(showName) {
         
         Point2D.Double traveler = new Point2D.Double(loc1.getPosition().x, loc1.getPosition().y);
         Point2D.Double traveler2 = new Point2D.Double();
         int count = 0;
         while(traveler.x != loc2.getPosition().x) {
            count ++;
            g.setColor(new Color(52, 194, 13));
            traveler2.setLocation(traveler.x + (velocity.x * 30), traveler.y + (velocity.y * 30));
            if(Math.sqrt(Math.pow(traveler.x - loc2.getPosition().x, 2) + Math.pow(traveler.y - loc2.getPosition().y, 2)) > 
            Math.sqrt(Math.pow(traveler.x - traveler2.x, 2) + Math.pow(traveler.y - traveler2.y, 2))) {
               if(count % 2 == 0) {
                  g.drawLine((int)traveler.x, (int)traveler.y, (int)traveler2.x, (int)traveler2.y);
               }
               traveler.setLocation(traveler2.x, traveler2.y);
            } else {
               //System.out.println("X");
               if(count % 2 == 0) {
                  g.drawLine((int)traveler.x, (int)traveler.y, (int)loc2.getPosition().x, (int)loc2.getPosition().y);
               }
               traveler.setLocation(loc2.getPosition().x, loc2.getPosition().y);
            }
         }
      }
      
      Graphics2D g2D = (Graphics2D) g;
      g2D.translate((int)(position.x), (int)(position.y));
      g2D.rotate(getDirection(velocity.x, velocity.y));
      
      g.drawImage(plane, (int)(-size * 1.4), (int)(-size * 1.2), size * 3, size * 3, null);
      
      g2D.rotate(-getDirection(velocity.x, velocity.y));
      g2D.translate(-(int)(position.x), -(int)(position.y));
      //g.setColor(Color.GREEN);
      //g.fillOval((int)(position.x - 8), (int)(position.y - 8), size, size);
      
      if(showName) {
         g.setColor(new Color(52, 194, 13));
         g.fillRect((int)(position.x - size / 2.0), (int)(position.y - size * 1.7), name.length() * 8, 20);
         g.setFont(new Font("sans-serif", Font.BOLD, 15));
         g.setColor(Color.BLACK);
         g.drawString(name, (int)(position.x - size * 0.4), (int)(position.y - size * 0.8));
         //g.fillPolygon(new int[] {(int)(position.x - size * 0.5), (int)(position.x), (int)(position.x + size * 0.5)},
         //              new int[] {(int)(position.y - size * 1.7) + 20, (int)(position.y - size * 1.7), (int)(position.y - size * 1.7) + 20}, 3);
      }
   }
   
   public void update(double speed) {
      if(Math.sqrt(Math.pow(position.x - loc2.getPosition().x, 2) + Math.pow(position.y - loc2.getPosition().y, 2)) > 
      Math.sqrt(Math.pow(position.x - position.x - velocity.x * speed, 2) + Math.pow(position.y - position.y - velocity.y * speed, 2))) {
         position.setLocation(position.x + velocity.x * speed, position.y + velocity.y * speed);
      } else {
         flightEnded = true;
      }
   }
   
   public void setShowName(boolean a) {
      showName = a;
   }
   
   public Point2D.Double getPosition() {
      return position;
   }
   
   public int getSize() {
      return size;
   }
   
   public String getName() {
      return name;
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