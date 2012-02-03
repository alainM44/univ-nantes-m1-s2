/**********************
 * Classe Java Timer
 * TD Test + TP JUnit
 * @auteur Arnaud Lanoix
 * @version 1
 ************************/

import java.io.*;
import java.util.*;
import java.util.GregorianCalendar;

public class Timer {

    protected int ring ; // 1 - 10

    protected int hour ; // 0 - 23
    protected int min ; // 0 - 59

    protected boolean active ; // true/false
    protected boolean ringing ; //true/false
    protected Calendar calendar;
    
    /** 
     * @version 1 
     * Le constructeur initialise le timer avec les valeurs 
     * passées en paramètres. Le timer n'est pas actif et
     * il ne sonne pas.
     * @author Arnaud Lanoix
     * @param ring choix de sonnerie entre 1 et 10
     * @param hour choix d'heure entre 0 et 23
     * @param min choix de minutes entre 0 et 59
     * @throws TimerException un des parametres est hors limite
     */ 
    public Timer(int ring,
		 int hour,
		 int min,
		 Calendar calendar)
	throws TimerException {
	if (hour < 0) {
	    throw new TimerException("bad hour: inf value");
	}
	else if (hour > 23) {
	    throw new TimerException("bad hour: sup value");
	}
	else if (min < 0) {
	    throw new TimerException("bad min: inf value");
	}
	else if (min > 59) {
	    throw new TimerException("bad min: sup value");
	}
	else if ((ring < 1) || (ring > 10)) {
	    throw new TimerException("bad ring: out of limits");
	}
	else {
	    this.ring = ring;
	    this.hour = hour;
	    this.min = min;
	    this.calendar=calendar;
	}
	active = false;
	ringing = false;
    }
    
    /** 
     * @version 1 
     * choisi une nouvelle sonnerie
     * @author Arnaud Lanoix
     * @param ring choix de sonnerie entre 1 et 10
     * @throws TimerException le choix de sonnerie est impossible
     */
    public void selectRing(int ring)
	throws TimerException {
	if ((ring < 1) || (ring > 10)) {
	    throw new TimerException("bad ring: out of limits");
	}
	this.ring = ring;
    }
    

    /** 
     * @version 1 
     * active/desactive le timer
     * si le timer est actif et qu'il est l'heure, alors il sonne.
     * @author Arnaud Lanoix
     * @param active true si actif, false si inactif
     */
	protected void setActive(boolean active) {
	    this.active = active;
	    if (this.active) {
	//	Calendar calendar = new GregorianCalendar();
		if (this.hour == calendar.get(Calendar.HOUR))
		    if (this.min == calendar.get(Calendar.MINUTE)) 
			ringing = true;
	    }
	}


    /** 
     * @version 1 
     * incremente le temps avec les minutes en parametre.
     * change donc l'heure et la min fixée précédemment.
     * @author Arnaud Lanoix
     * @param incmin min ajoutées du temps fixé précédemment
     */		
    public void addMin(int addedmin) {
	int addedhour = 0;
	int newmin = 0;
	int newhour = 0;
	while (addedmin > 59) {
	    addedhour++;
	    addedmin = addedmin - 60;
	}
	newmin = this.min + addedmin;
	if (newmin > 59) {
	    addedhour++;
	    newmin = newmin - 60;
	}
	newhour = this.hour + addedhour;
	while (newhour > 23) {
	    newhour = newhour - 24;
	}
	this.hour = newhour;
	this.min = newmin;
    }

   /** 
     * @version 1 
     * @author Arnaud Lanoix
     * @param object objet à comparer pour l'égalité 
     * @return true si l'objet est égal au timer courant
     */ 
    public boolean equals(Object object) { 
	if (object != null && object instanceof Timer) { 
	    return this.ring == ((Timer) object).ring 
		&& this.hour ==((Timer) object).hour 
		&& this.min == ((Timer) object).min
		&& this.active == ((Timer) object).active
		&& this.ringing == ((Timer) object).ringing; 
	} 
	else return false ; 
    } 

    /** 
     * @version 1 
     * @author Arnaud Lanoix
     * @return une chaine de caractère caractérisant le timer
     */ 
    public String toString() {
	String str = "";
       	str += hour + ":" + min;
	if (active) { 
	    str += "  (active";
	    if (ringing)
		str += ":ringing";
	    str += ")";
	}
	return str;
    }
    
    public static void main(String args[]) {    
	try {
	   Timer timer = new Timer(3, 23, 59, new GregorianCalendar());
	    timer.setActive(true);
	    System.out.println(timer);
	    timer.addMin(55);
	    System.out.println(timer);
       }
       catch (TimerException e) {
	   System.out.println(e);
       }
    }


}
