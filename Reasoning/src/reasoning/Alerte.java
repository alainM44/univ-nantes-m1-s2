package reasoning;

import java.awt.image.BufferedImage;

import middleware.IReasoning;
import middleware.IEvent;

public class Alerte implements IReasoning {

	public Alerte() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IEvent happenEvenement() {
		IEvent e=(IEvent) new Event(); 
		return e;
	}

	@Override
	public void reasonedOnImame(BufferedImage im, int time) {
		
	}


}
