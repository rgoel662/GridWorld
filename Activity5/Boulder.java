/**
 * A boulder is really just a nice name for bomb. When this boulder reaches 
 * a certain threshold, it just turns into a bomb and explodes.
 * 
 * @author Rishabh Goel
 */

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import java.awt.Color;

public class Boulder extends Actor{
	private int lifetime; // the lifetime of the boulder
	private final int THRESHOLD = 3; // the point at which the boulder will turn red
	
	public Boulder(){
		lifetime = (int)(Math.random() * 201) + 1; // setting the lifetime of boulder
		if (lifetime <= THRESHOLD)
			setColor(Color.RED);
		else
			setColor(null);
	}
	
	/**
	 * Sets the life of the boulder to a fixed amount
	 * 
	 * @param lifeIn	The amount to set the life of the boulder to
	 */
	public Boulder(int lifeIn){
		lifetime = lifeIn;
		if (lifetime <= THRESHOLD)
			setColor(Color.RED);
		else
			setColor(null);
	}
	
	/**
	 * Ticks down the boulder's life, when it reaches 0, it destroys itself
	 * and places in its wake a Kaboom
	 */
	public void act(){
		lifetime --;
		if (lifetime == THRESHOLD){
			setColor(Color.RED);
		}
		if (lifetime <= 0){
			Location loc = getLocation();
			Grid<Actor> grid = getGrid();
			removeSelfFromGrid();
			Kaboom kb = new Kaboom();
			kb.putSelfInGrid(grid, loc);
		}
	}
}
