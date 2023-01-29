
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import javax.imageio.ImageIO;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JComponent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
   private BufferedImage pause;
   private BufferedImage play;
   private BufferedImage add;
   private BufferedImage subtract;
   private Location[] locations;
   private ArrayList<Flight> flights;
   private Button pauseButton;
   private Button addFlight;
   private Button speedUp;
   private Button slowDown;
   private boolean paused;
   private double speed;
   
   Main() {
      screenWidth = 1000;
      screenHeight = 650;
      paused = false;
      speed = 0.1;
      
      try {
      background = ImageIO.read(new File("src/map2.png"));
      } catch (IOException e) {}
      
      try {
      subtract = ImageIO.read(new File("src/subtraction.png"));
      } catch (IOException e) {}
      
      try {
      add = ImageIO.read(new File("src/add-button.png"));
      } catch (IOException e) {}
      
      try {
      pause = ImageIO.read(new File("src/pause.png"));
      } catch (IOException e) {}
      
      try {
      play = ImageIO.read(new File("src/play.png"));
      } catch (IOException e) {}
      
      pauseButton = new Button(700, 600, 50, pause, play);
      addFlight = new Button(50, 50, 50, add);
      speedUp = new Button(640, 600, 50, add);
      slowDown = new Button(760, 600, 50, subtract);
      
      locations = new Location[]{new Location(820, 280, "Washington, D.C."), new Location(880, 220, "New York"), 
      new Location(820, 565, "Miami"), new Location(560, 170, "Minneapolis"), new Location(440, 480, "Austin"),
      new Location(100, 360, "Los Angeles"), new Location(120, 60, "Seattle"), new Location(640, 240, "Chicago"),
      new Location(350, 280, "Denver"), new Location(730, 400, "Atlanta"), new Location(910, 180, "Boston"),
      new Location(230, 390, "Phoenix"), new Location(250, 240, "Salt Lake City"), 
      new Location(60, 240, "San Francisco"), new Location(610, 485, "New Orleans")};
      flights = new ArrayList<Flight>();
      flights.add(new Flight(locations[5], locations[3]));
      
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
      if(!paused) {
         for(int i = 0; i < flights.size(); i ++) {
            flights.get(i).update(speed);
            if(flights.get(i).getFlightEnded()) {
               flights.remove(i);
            }
         }
      }
      repaint();
   }
   
   public void paintComponent(Graphics g) {
      g.drawImage(background, 0, 0, screenWidth, screenHeight, null);
      
      pauseButton.draw(g);
      slowDown.draw(g);
      speedUp.draw(g);
      addFlight.draw(g);
      
      for(int i = 0; i < locations.length; i ++) {
         locations[i].draw(g);
      }
      for(int i = 0; i < flights.size(); i ++) {
         flights.get(i).draw(g);
      }
   }
   
    public void mousePressed(MouseEvent e) {
      //System.out.println("H");
       for(int i = 0; i < locations.length; i ++) {
          if(Math.sqrt(Math.abs(Math.pow(locations[i].getPosition().x + locations[i].getSize() * 0.5 - e.getX(), 2) + Math.pow(locations[i].getPosition().y + locations[i].getSize() * 1.5- e.getY(), 2))) < locations[i].getSize() * 0.8) {
             locations[i].setShowName(!locations[i].getShowName());
          }
       }
       for(int i = 0; i < flights.size(); i ++) {
          if(Math.sqrt(Math.abs(Math.pow(flights.get(i).getPosition().x + flights.get(i).getSize() * 0.5 - e.getX(), 2) + Math.pow(flights.get(i).getPosition().y + locations[i].getSize() * 1.5 - e.getY(), 2))) < flights.get(i).getSize() * 0.8) {
             flights.get(i).setShowName(!flights.get(i).getShowName());
          }
       }
       
       
       
       if(Math.sqrt(Math.pow(pauseButton.getPosition().x + 15 - e.getX(), 2) + Math.pow(pauseButton.getPosition().y + 30 - e.getY(), 2)) <= pauseButton.getSize() * 0.5) {
         pauseButton.setWhichName(!pauseButton.getWhichName());
         paused = !paused;
       }
       if(Math.sqrt(Math.pow(speedUp.getPosition().x + 15 - e.getX(), 2) + Math.pow(speedUp.getPosition().y + 30 - e.getY(), 2)) <= speedUp.getSize() * 0.5) {
        speed += 0.02; 
       }
       if(Math.sqrt(Math.pow(slowDown.getPosition().x + 15 - e.getX(), 2) + Math.pow(slowDown.getPosition().y + 30 - e.getY(), 2)) <= slowDown.getSize() * 0.5) {
         if(speed > 0.02) {
            speed -= 0.02;
         }
       }
       if(Math.sqrt(Math.pow(addFlight.getPosition().x+ 15 - e.getX(), 2) + Math.pow(addFlight.getPosition().y + 30 - e.getY(), 2)) <= addFlight.getSize() * 0.5) {
         String l1 = JOptionPane.showInputDialog(this, "Enter Departure Location:").trim();
         String l2 = JOptionPane.showInputDialog(this, "Enter Arrival Location:").trim();
         int locL1 = 345345;
         int locL2 = 345345;
         for(int i = 0; i < locations.length; i++) {
            if(l1.equalsIgnoreCase(locations[i].getName())) {
               locL1 = i;
            }
            if(l2.equalsIgnoreCase(locations[i].getName())) {
               locL2 = i;
            }
         }
         if(locL1 == 345345 || locL2 == 345345) {
            JOptionPane.showMessageDialog(this, "Invalid Location");
         } else {
            flights.add(new Flight(locations[locL1], locations[locL2]));
         }
       }
    }

    public void mouseReleased(MouseEvent e) {
       for(int i = 0; i < locations.length; i ++) {
          locations[i].setShowName(false);
       }
       for(int i = 0; i < flights.size(); i ++) {
          flights.get(i).setShowName(false);
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
