

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

public class Main extends JComponent implements ActionListener {
   //FIELDS
   private int screenWidth, screenHeight;
   private JFrame frame;
   
   Main() {
      screenWidth = 1000;
      screenHeight = 650;
      
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
      //frame.addKeyListener(ship);
      //frame.addKeyListener(startScreen);
   }
   
   public void actionPerformed(ActionEvent e) {
      
      repaint();
   }
   
   public void paintComponent(Graphics g) {
      
   }
}

class Run {
   public static void main(String[] args) {
      Main m = new Main();
   }
}