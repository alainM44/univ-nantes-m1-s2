package archive2D;


import java.awt.Adjustable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JCanvas extends JPanel{
	private static final long serialVersionUID = 1L;

	private LinkedList  drawables = new LinkedList();



	public JScrollPane JCanvastoSrcoll(JCanvas jc){
		JScrollPane my_ScrollPane = new JScrollPane(jc);
		my_ScrollPane.setPreferredSize(this.getPreferredSize());
		my_ScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		my_ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		my_ScrollPane.setAutoscrolls(true);
		my_ScrollPane.setViewportView(jc);
		my_ScrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		my_ScrollPane.getVerticalScrollBar().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				repaint();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				repaint();
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				repaint();
				
			}
		});

		my_ScrollPane.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				Adjustable source = e.getAdjustable();
				// getValueIsAdjusting() returns true if the user is currently
				// dragging the scrollbar's knob and has not picked a final value
				if (e.getValueIsAdjusting()) {
					repaint();
					return;
				}
				repaint();

			}
		});
		my_ScrollPane.getVerticalScrollBar().addAdjustmentListener(new  AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				Adjustable source = e.getAdjustable();
								
				// getValueIsAdjusting() returns true if the user is currently
				// dragging the scrollbar's knob and has not picked a final value
				if (e.getValueIsAdjusting()) {
					repaint();
					return;
				}
				repaint();
			}
		});

		return my_ScrollPane;
	}

	public void paintComponent(Graphics g) {
		for (Iterator iter = drawables.iterator(); iter.hasNext();) {
			IDrawable d = (IDrawable) iter.next();
			d.draw(g);	
		}
	}


	public void addDrawable(IDrawable d) {
		drawables.add(d);
		repaint();
	}

	public void removeDrawable(IDrawable d) {
		drawables.remove(d);
		repaint();
	}

	public void clear() {
		drawables.clear();
		repaint();
	}


	public boolean isFree(Rectangle rect) {
		for (Iterator iter = drawables.iterator(); iter.hasNext();) {
			IDrawable element = (IDrawable) iter.next();
			if(element.getRectangle().intersects(rect)){
				return false;
			}
		}
		return true;
	}

	public boolean isAlone(IDrawable drawable) {
		Rectangle rect = drawable.getRectangle();
		for (Iterator iter = drawables.iterator(); iter.hasNext();) {
			IDrawable element = (IDrawable) iter.next();
			if(!element.equals(drawable) &&  element.getRectangle().intersects(rect)) {
				return false;
			}
		}
		return true;
	}


}
