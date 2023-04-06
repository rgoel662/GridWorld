/**
 * A marker to show where an explosion happened
 * 
 * @author Rishabh Goel
 */

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

public class Kaboom extends Actor{
	private int lifetime; // the lifetime of a kaboom
	private final int THRESHOLD = 3; // how long the life can last
	
	public Kaboom(){
		lifetime = THRESHOLD; // sets the lifetime
		setColor(null);
	}
	
	/**
	 * When the lifetime is 0, there is no more Kaboom
	 */
	public void act(){
		lifetime --;
		if (lifetime == 0)
			removeSelfFromGrid();
	}
}
