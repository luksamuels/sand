import javax.swing.JFrame;

import java.awt.Insets;

public class SandFrame extends JFrame {

   public static final int BOARD_WIDTH = 160;
   public static final int BOARD_HEIGHT = 200;
   public static final int PIXEL_SIZE = 4;
   
   private SandPanel panel;
   
   public SandFrame() {
      // set up the frame itself
      setVisible(true);
      setTitle("Sand");
      setLayout(null);
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Insets winInsets = getInsets();
      setBounds(100, 100, (BOARD_WIDTH * PIXEL_SIZE) + winInsets.left + winInsets.right,
         (BOARD_HEIGHT * PIXEL_SIZE) + winInsets.top + winInsets.bottom);
      
      // set up the panel
      panel = new SandPanel(BOARD_WIDTH, BOARD_HEIGHT, PIXEL_SIZE);
      panel.setBounds(0, 0, BOARD_WIDTH * PIXEL_SIZE, BOARD_HEIGHT * PIXEL_SIZE);
      
      // add the panel
      add(panel);
   }
   
   
   public static void main(String[] args) {
      new SandFrame();
   }  
}