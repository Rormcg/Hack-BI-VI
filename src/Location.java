
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;

public class Location {
   private Point position;
   private String name;
   private boolean showName;
   private int size;
   
   Location(int x, int y, String n) {
      position = new Point(x, y);
      name = n;
      size = 20;
   }
   
   public void draw(Graphics g) {
      g.setColor(Color.RED);
      g.fillOval(position.x - 10, position.y - 10, size, size);
      g.setColor(Color.ORANGE);
      g.fillOval(position.x - 6, position.y - 6, (int)(size * 3.0/5), (int)(size * 3.0/5));
      
      if(showName) {
         g.setColor(new Color(150, 150, 150));
         g.fillRect((int)(position.x - size / 2.0), (int)(position.y - size * 1.7), name.length() * 9, 20);
         g.setFont(new Font("sans-serif", Font.BOLD, 15));
         g.setColor(Color.BLACK);
         g.drawString(name, (int)(position.x - size * 0.4), (int)(position.y - size ));
         //g.fillPolygon(new int[] {(int)(position.x - size * 0.5), (int)(position.x), (int)(position.x + size * 0.5)},
         //              new int[] {(int)(position.y - size * 1.7) + 20, (int)(position.y - size * 1.7), (int)(position.y - size * 1.7) + 20}, 3);
      }
   }
   
   public Point getPosition() {
      return position;
   }
   
   public void setShowName(boolean a) {
      showName = a;
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
}