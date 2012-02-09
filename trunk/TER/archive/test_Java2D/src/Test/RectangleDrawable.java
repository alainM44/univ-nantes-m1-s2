package Test;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class RectangleDrawable extends FormDrawable{

   public RectangleDrawable(Color color, Point pos, Dimension dim) {
      super(color, pos, dim);
      // TODO Auto-generated constructor stub
   }


   public void draw(Graphics g) {
      Color c = g.getColor();
      g.setColor(color);
      g.fillRect(rect.x,rect.y,rect.width,rect.height);
      g.setColor(c);
   }


}
