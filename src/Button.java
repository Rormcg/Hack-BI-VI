
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;


public class Button {
   private Point position;
   private BufferedImage image1;
   private BufferedImage image2;
   private int size;
   private boolean whichName;
   
   Button(int x, int y, int s, BufferedImage n) {
      position = new Point(x, y);
      image1 = n;
      image2 = n;
      size = s;
      whichName = true;
   }
   
   Button(int x, int y, int s, BufferedImage n1, BufferedImage n2) {
      position = new Point(x, y);
      image1 = n1;
      image2 = n2;
      size = s;
      whichName = true;
   }
   
   public void draw(Graphics g) {
      //g.setColor(new Color(13, 194, 88));
      
      /*g.setColor(new Color(200, 200, 200));
      g.setFont(new Font("sans-serif", Font.BOLD, 40));*/
      if(whichName) {
         g.drawImage(image1, position.x - (int)(size * 0.5), position.y - (int)(size * 0.5), size, size, null);
      } else {
         g.drawImage(image2, position.x - (int)(size * 0.5), position.y - (int)(size * 0.5), size, size, null);
      }
      //g.setColor(Color.GREEN);
      //g.fillOval(position.x, position.y, 5, 5);
   }
   
   public Point getPosition() {
      return position;
   }
   
   public int getSize() {
      return size;
   }
   
   public boolean getWhichName() {
      return whichName;
   }
   
   public void setWhichName(boolean a) {
      whichName = a;
   }
}