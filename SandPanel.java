import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import java.util.Timer;
import java.util.TimerTask;

public class SandPanel extends JPanel implements MouseMotionListener, MouseListener {

   public static final int UPDATE_TICK = 14;
   public static final int DRAW_TICK = 50;

   private int board_x;
   private int board_y;
   private int pixel_size;
   private boolean mouseDown;
   private SandManager sand;
   private Timer tickTimer;
   private ColorShift colorShift;
   
   private TimerTask tick = new TimerTask() {
      public void run() {
         if(mouseDown) {
            Point p = getMousePosition();
            sand.spawn(p.x / pixel_size, p.y / pixel_size);
         }
      
         sand.tic();
         repaint();
      }
   };
   
   /** ------------------------------- CONSTRUCTOR ---------------------------------
   */
   public SandPanel(int width, int height, int pixel_size) {
      // set up fields
      this.board_x = width;
      this.board_y = height;
      this.mouseDown = false;
      this.pixel_size = pixel_size;
      this.sand = new SandManager(width, height);
      
      
      // initialize the timers
      tickTimer = new Timer();
      tickTimer.schedule(tick, 0, UPDATE_TICK);
      
      // set up the panel itself
      this.setLayout(null);
      this.addMouseMotionListener(this);
      this.addMouseListener(this);
      
   }
   
   
   /** ------------------------------- PAINT COMP -----------------------------------
   */
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      setBackground(Color.DARK_GRAY);
      
      // paint the sand
      for(int i = board_y - 1; i >= 0; i--) {
         for(int j = board_x - 1; j >= 0; j--) {
            if(sand.at(j, i) != 0) {
               g.setColor(new Color(sand.at(j, i)));
               g.fillRect(j * pixel_size, i * pixel_size, pixel_size, pixel_size);
            }
         }
      }
   }
   
   /** ------------------------- MOUSE EVENTS --------------------------------
   */
   public void mouseClicked(MouseEvent e) {}
   public void mousePressed(MouseEvent e) {
      mouseDown = true;
   }
   public void mouseReleased(MouseEvent e) {
      mouseDown = false;
   }
   public void mouseEntered(MouseEvent e) {}
   public void mouseExited(MouseEvent e) {}
   
   public void mouseDragged(MouseEvent e) {
   }
   public void mouseMoved(MouseEvent e) {}
}