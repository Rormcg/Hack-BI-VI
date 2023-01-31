import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Graphics;


public class Button {
   private final Point POSITION;
   private final BufferedImage IMAGE_1;
   private final BufferedImage IMAGE_2;
   private final int SIZE;
   private boolean whichName;
   
   Button(int x, int y, int s, BufferedImage n) {
      POSITION = new Point(x, y);
      IMAGE_1 = n;
      IMAGE_2 = n;
      SIZE = s;
      whichName = true;
   }
   
   Button(int x, int y, int s, BufferedImage n1, BufferedImage n2) {
      POSITION = new Point(x, y);
      IMAGE_1 = n1;
      IMAGE_2 = n2;
      SIZE = s;
      whichName = true;
   }
   
   public void draw(Graphics g) {
      if(whichName) {
         g.drawImage(IMAGE_1, POSITION.x - (int)(SIZE * 0.5), POSITION.y - (int)(SIZE * 0.5), SIZE, SIZE, null);
      } else {
         g.drawImage(IMAGE_2, POSITION.x - (int)(SIZE * 0.5), POSITION.y - (int)(SIZE * 0.5), SIZE, SIZE, null);
      }
   }
   
   public Point getPOSITION() {
      return POSITION;
   }
   
   public int getSIZE() {
      return SIZE;
   }
   
   public boolean getWhichName() {
      return whichName;
   }
   
   public void setWhichName(boolean a) {
      whichName = a;
   }
}