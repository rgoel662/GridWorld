/**
 * A coyote that is dazed and turns into a healthy coyote after some time
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

public class SickCoyote extends Actor {
    private int lifetime; // lifetime of the stone
	private final int THRESHOLD = 10; // at what point the stone turns green
	
	
	public SickCoyote(){
		lifetime = THRESHOLD; // random value for lifetime
        setColor(null);
	}
	
	/**
	 * Sets the stone with a fixed lifetime
	 * 
	 * @param lifeIn	The fixed lifetime to set the lifetime of the sickcoyote to
	 */
	public SickCoyote(int lifeIn){
		lifetime = lifeIn;
		setColor(null);
	}
	
	/**
	 * If lifetime is within the threshold, the color of the stone turns to green
	 * indicating that it will turn into a coyote in the next 3 steps
	 * When lifetime is 0, it removes itself from the grid and places a coyote.
	 */
	public void act(){
		lifetime --;
		if (lifetime <= 0){
			Location loc = getLocation();
			Grid<Actor> grid = getGrid();
			removeSelfFromGrid();
			
			Coyote kb = new Coyote();
			kb.putSelfInGrid(grid, loc);
		}
	}
}
