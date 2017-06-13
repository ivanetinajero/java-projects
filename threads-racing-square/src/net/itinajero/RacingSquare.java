package net.itinajero;

/*
 * File: RacingSquare.java
 * -----------------------
 * This class is an example of a runnable class that animates
 * a square running a race.
 */

import acm.graphics.*;
import acm.util.*;
import java.awt.*;

public class RacingSquare extends GRect implements Runnable {
	
   /* constants */
	private static final double SIZE = 20;
	private static final double STEP = 5;

	/* private instance variables */
	private int myIndex;
	private boolean[] finishers;
	private RandomGenerator rgen = RandomGenerator.getInstance();
   
	public RacingSquare(int index, boolean[] finished) {
		super(SIZE, SIZE);  // calls GRect constructor
		setFilled(true);
		myIndex = index;
		finishers = finished;
	}
	
	public void run() {
		// have not finished when we start
		finishers[myIndex] = false;
		
		// run the race
		for(int i = 0; i < 100; i++) {
			pause(rgen.nextInt(50, 150));
			move(STEP, 0);
		}

		// watch out for photo finishes!!  (POTENTIALLY BUGGY!)
		
		// count how many others finished before me
		int count = 0;
		for(int i = 0; i < finishers.length; i++) {
			if (finishers[i]) count++;
		}
		
		// perhaps do a victory dance
		// pause(50);
		
		// mark myself as having finished
		finishers[myIndex] = true;

		// if no one finished before me, then I win! (turn red)
		if (count == 0) {
			setColor(Color.RED);
		}
	}
		
}