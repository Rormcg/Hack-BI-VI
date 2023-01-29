
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JComponent;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main extends JComponent implements ActionListener, MouseListener{
   //FIELDS
   private int screenWidth, screenHeight;
   private JFrame frame;
   private BufferedImage background;
   private Location[] locations;
   private Flight[] flights;
   
   Main() {
      screenWidth = 1000;
      screenHeight = 650;
      try {
      background = ImageIO.read(new File("C:\\Users\\rorym\\OneDrive - bishopireton.org\\Documents\\Git\\Hack-BI-VI\\src\\OIP.jpg"));
      } catch (IOException e) {}
      
      
      locations = new Location[]{new Location(200, 200, "New York"), new Location(700, 600, "Los Angeles")};
      flights = new Flight[]{new Flight(locations[0], locations[1])};
      
      frame = new JFrame("Global");
      Container content = frame.getContentPane();
      content.setBackground(Color.BLACK);
      content.add(this);
      
      Timer t = new Timer(1, this);
      t.start();
      
      frame.setSize(screenWidth+17, screenHeight+40);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.addMouseListener(this);
   }
   
   public void actionPerformed(ActionEvent e) {
      for(int i = 0; i < flights.length; i ++) {
         flights[i].update();
      }
      repaint();
   }
   
   public void paintComponent(Graphics g) {
      g.drawImage(background, 0, 0, screenWidth, screenHeight, null);
      
      for(int i = 0; i < locations.length; i ++) {
         locations[i].draw(g);
      }
      for(int i = 0; i < flights.length; i ++) {
         flights[i].draw(g);
      }
   }
   
    public void mousePressed(MouseEvent e) {
      //System.out.println("H");
       for(int i = 0; i < locations.length; i ++) {
          if(Math.sqrt(Math.abs(Math.pow(locations[i].getPosition().x + locations[i].getSize() * 0.5 - e.getX(), 2) + Math.pow(locations[i].getPosition().y + locations[i].getSize() * 1.5- e.getY(), 2))) < locations[i].getSize() * 0.8) {
             locations[i].setShowName(!locations[i].getShowName());
          }
       }
       for(int i = 0; i < flights.length; i ++) {
          if(Math.sqrt(Math.abs(Math.pow(flights[i].getPosition().x + flights[i].getSize() * 0.5 - e.getX(), 2) + Math.pow(flights[i].getPosition().y + locations[i].getSize() * 1.5 - e.getY(), 2))) < flights[i].getSize() * 0.8) {
             flights[i].setShowName(!flights[i].getShowName());
          }
       }
    }

    public void mouseReleased(MouseEvent e) {
       for(int i = 0; i < locations.length; i ++) {
          locations[i].setShowName(false);
       }
       for(int i = 0; i < flights.length; i ++) {
          flights[i].setShowName(false);
       }
    }

    public void mouseEntered(MouseEvent e) {
       
    }

    public void mouseExited(MouseEvent e) {
       
    }

    public void mouseClicked(MouseEvent e) {
       
    }
}

class Run {
   public static void main(String[] args) {
      Main m = new Main();
   }
}