import java.util.Random;
import java.awt.Color;

public class SandManager {
   private static final int[] DOWN = {0, 1};
   private static final int[] LEFT = {-1, 1};
   private static final int[] RIGHT = {1, 1};


   private int width;
   private int height;
   private int[][] board;
   private Random mover;
   private ColorShift colorShift;
   
   /** ------------------------------- CONSTRUCTOR ------------------------------
   */
   public SandManager(int width, int height) {
      this.width = width;
      this.height = height;
      board = new int[height][width];
      mover = new Random();
      colorShift = new ColorShift(new Color(0xb05151), new Color(0x51b064), new Color(0xb05175));
   }
   
   /** --------------------------- KEY FUNCTIONALITY -----------------------------
   */   

   // pre: board[y][x] != 0
   private int[] fallDirection(int x, int y) {
      // can fall straight down?
      if(!bool(board[y+1][x])) {
         return new int[]{0, 1};
      }
      
      int direction = mover.nextBoolean()?-1:1; // pick a direction
      if(validMove(x + direction, y+1)) { // can x move in that direction?
         return new int[]{direction, 1};
      } else if(validMove(x - direction, y+1)) { // can x move in the opposite direction?
         return new int[]{-1 * direction, 1};
      }
      
      return new int[]{0, 0};
   }
   
   private void move(int x, int y, int[] direction) {
      if(direction.length == 2) {
         int temp = board[y][x];
         board[y][x] = 0;
         board[y + direction[1]][x + direction[0]] = temp;
      }
   }
   
   public void tic() {
      for(int i = height - 2; i >= 0; i--) { // backwards iteration allows sand to fall together
         for(int j = width - 1; j >= 0; j--) {
            if(board[i][j] != 0) { // sand needs to be processed
               move(j, i, fallDirection(j, i));
            }
         }
      }   
   }
   
   /** -------------------------- GETTER/SETTER ----------------------------------
   */
   public int at(int x, int y) {
      return board[y][x];
   }
   
   public void spawn(int x, int y) {
      int range = 2;
      for(int i = (-1 * range); i <= range; i++) {
         for(int j = (-1 * range); j <= range; j++) {
            if(mover.nextDouble() < 0.25d && validMove(x + i, y + j)) {
               board[y + j][x + i] = colorShift.nextColorAsHex();
            }
         }
      }
   }
   /** ------------------------------ UTIL ---------------------------------------
   */
   private boolean bool(int i) {
      return i != 0;
   }
   
   // returns true if board[y][x] is valid and has no sand in it
   private boolean validMove(int x, int y) {
      return ((x >= 0) && (x < width) && (y >= 0) && (y < height) && (board[y][x] == 0));
   }
   
}