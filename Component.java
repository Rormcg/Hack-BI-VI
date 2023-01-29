
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JComponent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Component extends JComponent implements ActionListener, MouseListener{
   //FIELDS
   private final int SCREEN_WIDTH, SCREEN_HEIGHT;
   private BufferedImage background, pause, play, add, subtract;
   private final Location[] LOCATIONS;
   private final ArrayList<Flight> FLIGHTS;
   private final Button PAUSE_BUTTON, ADD_FLIGHT_BUTTON, SPEED_UP_BUTTON, SLOW_DOWN_BUTTON;
   private boolean paused;
   private double speed;
   
   Component() {
      SCREEN_WIDTH = 1000;
      SCREEN_HEIGHT = 650;
      paused = false;
      speed = 0.1;
      
      try {
         background = ImageIO.read(new File("src/map2.png"));
      } catch (IOException ignored) {}
      
      try {
         subtract = ImageIO.read(new File("src/subtraction.png"));
      } catch (IOException ignored) {}
      
      try {
         add = ImageIO.read(new File("src/add-button.png"));
      } catch (IOException ignored) {}
      
      try {
         pause = ImageIO.read(new File("src/pause.png"));
      } catch (IOException ignored) {}
      
      try {
         play = ImageIO.read(new File("src/play.png"));
      } catch (IOException ignored) {}
      
      PAUSE_BUTTON = new Button(700, 600, 50, pause, play);
      ADD_FLIGHT_BUTTON = new Button(50, 50, 50, add);
      SPEED_UP_BUTTON = new Button(640, 600, 50, add);
      SLOW_DOWN_BUTTON = new Button(760, 600, 50, subtract);
      
      LOCATIONS = new Location[]{new Location(820, 280, "Washington, D.C."), new Location(880, 220, "New York"),
      new Location(820, 565, "Miami"), new Location(560, 170, "Minneapolis"), new Location(440, 480, "Austin"),
      new Location(100, 360, "Los Angeles"), new Location(120, 60, "Seattle"), new Location(640, 240, "Chicago"),
      new Location(350, 280, "Denver"), new Location(730, 400, "Atlanta"), new Location(910, 180, "Boston"),
      new Location(230, 390, "Phoenix"), new Location(250, 240, "Salt Lake City"),
      new Location(60, 240, "San Francisco"), new Location(610, 485, "New Orleans")};
      FLIGHTS = new ArrayList<>();
      FLIGHTS.add(new Flight(LOCATIONS[5], LOCATIONS[3]));

      JFrame FRAME = new JFrame("Global");
      Container content = FRAME.getContentPane();
      content.setBackground(Color.BLACK);
      content.add(this);
      
      Timer t = new Timer(1, this);
      t.start();
      
      FRAME.setSize(SCREEN_WIDTH +17, SCREEN_HEIGHT +40);
      FRAME.setLocationRelativeTo(null);
      FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      FRAME.setVisible(true);
      FRAME.addMouseListener(this);
   }
   
   public void actionPerformed(ActionEvent e) {
      if(!paused) {
         for(int i = 0; i < FLIGHTS.size(); i ++) {
            FLIGHTS.get(i).update(speed);
            if(FLIGHTS.get(i).getFlightEnded()) {
               FLIGHTS.remove(i--);
            }
         }
      }
      repaint();
   }
   
   public void paintComponent(Graphics g) {
      g.drawImage(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
      
      PAUSE_BUTTON.draw(g);
      SLOW_DOWN_BUTTON.draw(g);
      SPEED_UP_BUTTON.draw(g);
      ADD_FLIGHT_BUTTON.draw(g);

      for (Location location : LOCATIONS) {
         location.draw(g);
      }
      for (Flight flight : FLIGHTS) {
         flight.draw(g);
      }
   }
   
    public void mousePressed(MouseEvent e) {
      //System.out.println("H");
       for (Location location : LOCATIONS) {
          if (Math.sqrt(Math.abs(Math.pow(location.getPOSITION().x + location.getSIZE() * 0.5 - e.getX(), 2) + Math.pow(location.getPOSITION().y + location.getSIZE() * 1.5 - e.getY(), 2))) < location.getSIZE() * 0.8) {
             location.setShowName(!location.getShowName());
          }
       }
       for(int i = 0; i < FLIGHTS.size(); i ++) {
          if(Math.sqrt(Math.abs(Math.pow(FLIGHTS.get(i).getPOSITION().x + FLIGHTS.get(i).getSIZE() * 0.5 - e.getX(), 2) + Math.pow(FLIGHTS.get(i).getPOSITION().y + LOCATIONS[i].getSIZE() * 1.5 - e.getY(), 2))) < FLIGHTS.get(i).getSIZE() * 0.8) {
             FLIGHTS.get(i).setShowName(!FLIGHTS.get(i).getShowName());
          }
       }
       
       
       
       if(Math.sqrt(Math.pow(PAUSE_BUTTON.getPOSITION().x + 15 - e.getX(), 2) + Math.pow(PAUSE_BUTTON.getPOSITION().y + 30 - e.getY(), 2)) <= PAUSE_BUTTON.getSIZE() * 0.5) {
         PAUSE_BUTTON.setWhichName(!PAUSE_BUTTON.getWhichName());
         paused = !paused;
       }
       if(Math.sqrt(Math.pow(SPEED_UP_BUTTON.getPOSITION().x + 15 - e.getX(), 2) + Math.pow(SPEED_UP_BUTTON.getPOSITION().y + 30 - e.getY(), 2)) <= SPEED_UP_BUTTON.getSIZE() * 0.5) {
        speed += 0.02; 
       }
       if(Math.sqrt(Math.pow(SLOW_DOWN_BUTTON.getPOSITION().x + 15 - e.getX(), 2) + Math.pow(SLOW_DOWN_BUTTON.getPOSITION().y + 30 - e.getY(), 2)) <= SLOW_DOWN_BUTTON.getSIZE() * 0.5) {
         if(speed > 0.02) {
            speed -= 0.02;
         }
       }
       if(Math.sqrt(Math.pow(ADD_FLIGHT_BUTTON.getPOSITION().x+ 15 - e.getX(), 2) + Math.pow(ADD_FLIGHT_BUTTON.getPOSITION().y + 30 - e.getY(), 2)) <= ADD_FLIGHT_BUTTON.getSIZE() * 0.5) {
         String l1 = JOptionPane.showInputDialog(this, "Enter Departure Location:").trim();
         String l2 = JOptionPane.showInputDialog(this, "Enter Arrival Location:").trim();
         int locL1 = 345345;
         int locL2 = 345345;
         for(int i = 0; i < LOCATIONS.length; i++) {
            if(l1.equalsIgnoreCase(LOCATIONS[i].getNAME())) {
               locL1 = i;
            }
            if(l2.equalsIgnoreCase(LOCATIONS[i].getNAME())) {
               locL2 = i;
            }
         }
         if(locL1 == 345345 || locL2 == 345345) {
            JOptionPane.showMessageDialog(this, "Invalid Location");
         } else {
            FLIGHTS.add(new Flight(LOCATIONS[locL1], LOCATIONS[locL2]));
         }
       }
    }

    public void mouseReleased(MouseEvent e) {
       for (Location location : LOCATIONS) {
          location.setShowName(false);
       }
       for (Flight flight : FLIGHTS) {
          flight.setShowName(false);
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
      new Component();
   }
}