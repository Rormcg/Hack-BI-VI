import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;

public class Location {
   private final Point POSITION;
   private final String NAME;
   private boolean showName;
   private final int SIZE;
   
   Location(int x, int y, String n) {
      POSITION = new Point(x, y);
      NAME = n;
      SIZE = 20;
   }
   
   public void draw(Graphics g) {
      g.setColor(Color.RED);
      g.fillOval(POSITION.x - 10, POSITION.y - 10, SIZE, SIZE);
      g.setColor(Color.ORANGE);
      g.fillOval(POSITION.x - 6, POSITION.y - 6, (int)(SIZE * 3.0/5), (int)(SIZE * 3.0/5));
      
      if(showName) {
         g.setColor(new Color(150, 150, 150));
         g.fillRect((int)(POSITION.x - SIZE / 2.0), (int)(POSITION.y - SIZE * 1.7), NAME.length() * 9, 20);
         g.setFont(new Font("sans-serif", Font.BOLD, 15));
         g.setColor(Color.BLACK);
         g.drawString(NAME, (int)(POSITION.x - SIZE * 0.4), POSITION.y - SIZE);
      }
   }
   
   public Point getPOSITION() {
      return POSITION;
   }
   
   public void setShowName(boolean a) {
      showName = a;
   }
   
   public int getSIZE() {
      return SIZE;
   }
   
   public String getNAME() {
      return NAME;
   }
   
   public boolean getShowName() {
      return showName;
   }
}