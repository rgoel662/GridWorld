/**
 * A stone is like a rock but turns into a boulder after some time
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

public class Stone extends Rock{
	private int lifetime; // lifetime of the stone
	private final int THRESHOLD = 3; // at what point the stone turns green
	
	
	public Stone(){
		lifetime = (int)(Math.random() * 200) + 1; // random value for lifetime
		if (lifetime <= THRESHOLD)
			setColor(Color.GREEN);
		else
			setColor(null);
	}
	
	/**
	 * Sets the stone with a fixed lifetime
	 * 
	 * @param lifeIn	The fixed lifetime to set the lifetime of the stone to
	 */
	public Stone(int lifeIn){
		lifetime = lifeIn;
		if (lifetime <= THRESHOLD)
			setColor(Color.GREEN);
		else
			setColor(null);
	}
	
	/**
	 * If lifetime is within the threshold, the color of the stone turns to green
	 * indicating that it will turn into a boulder in the next 3 steps
	 * When lifetime is 0, it removes itself from the grid and places a boulder.
	 */
	public void act(){
		lifetime --;
		if (lifetime == THRESHOLD){
			setColor(Color.GREEN);
		}
		if (lifetime <= 0){
			Location loc = getLocation();
			Grid<Actor> grid = getGrid();
			removeSelfFromGrid();
			
			Boulder kb = new Boulder();
			kb.putSelfInGrid(grid, loc);
		}
	}
}
