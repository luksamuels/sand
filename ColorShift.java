import java.awt.Color;

public class ColorShift {
   public final int STEPS = 256; // number of steps between color 1 and color 2

   Color color1;
   Color color2;
   Color color3;
   
   // keeps track of how "far along" we are between c1 and c2;
   // it is necessary because if dr/dg/db is very small, it may
   // never change.
   int i;
   
   double dr;
   double dg;
   double db;
   
   public ColorShift(Color c1, Color c2) {
      init(c1, c2, null);
   }
   
   public ColorShift(Color c1, Color c2, Color c3) {
      init(c1, c2, c3);
   }
   
   public ColorShift(int c1, int c2) {
      init(new Color(c1), new Color(c2), null);
   }
   
   public ColorShift(int c1, int c2, int c3)  {
      init(new Color(c1), new Color(c2), new Color(c3));
   }
   
   private void init(Color c1, Color c2, Color c3) {
      color1 = c1;
      color2 = c2;
      color3 = c3;
      
      dr = (c2.getRed() - c1.getRed()) / (double)STEPS;
      dg = (c2.getGreen() - c1.getGreen()) / (double)STEPS;
      db = (c2.getBlue() - c1.getBlue()) / (double)STEPS;
  
      i = 0;
   }
   
   private void cycle() {
      if(color3 == null) {
         init(color2, color1, null);
      } else {
         init(color2, color3, color1);
      }
      
   }
   
   public Color nextColor() {
      return new Color(nextColorAsHex());
   }
   
   public int nextColorAsHex() {
      int r = color1.getRed() + (int)Math.round(i * dr);
      int g = color1.getGreen() + (int)Math.round(i * dg);
      int b = color1.getBlue() + (int)Math.round(i * db);
      
      if(i++ >= STEPS) {
         cycle();
      }
      
      return (r << 16) + (g << 8) + b;
   }
}
   
   
   
   
