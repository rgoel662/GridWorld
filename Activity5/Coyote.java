/**
 * The coyote class. The coyote will randomly wander the grid and place stones
 * When it hits a wall, or runs into an actor, or walks 5 steps, it will sleep 
 * for 5 steps. If the coyote runs into a boulder, it will blow up and die.
 * 
 * @author Rishabh Goel
 */

import info.gridworld.grid.*;
import info.gridworld.actor.*;
import java.awt.Color;
import java.util.ArrayList;

public class Coyote extends Critter{
	
	private int steps; // the number of steps run
	private int sleep; // the number of sleep steps done
	private int dir; // the direction of the coyote
	private boolean hasExploded; // whether or not the coyote has exploded
	private boolean hasHitWall; // whether or not the coyote hit a wall
	
	public Coyote(){
		int[] dirs = {Location.NORTH, Location.NORTHEAST, Location.EAST
					, Location.SOUTHEAST, Location.SOUTH, Location.SOUTHWEST
					, Location.WEST, Location.NORTHWEST};
		
		int randomNum = (int)(Math.random() * 8);
		
		dir = dirs[randomNum]; //sets a random direction
		setColor(null);
		
		steps = 0;
		sleep = 0;
		hasExploded = false;
		hasHitWall = false;
	}
	
	/**
	 * Gets the actors by looking only directly in front of it
	 * 
	 * @return	The actors that have been searched
	 */
	public ArrayList<Actor> getActors(){
		ArrayList<Actor> actors = new ArrayList<>();
		if (getGrid().isValid(getLocation().getAdjacentLocation(getDirection())) && 
			getGrid().get(getLocation().getAdjacentLocation(getDirection())) instanceof Actor)
			actors.add(getGrid().get(getLocation().getAdjacentLocation(getDirection())));
		return actors;
	}
	
	
	/**
	 * Processes all the actors that is in front of it. If it is a kaboom
	 * then the coyote explodes
	 * 
	 * @param actors	The list of actors to process
	 */
	public void processActors(ArrayList<Actor> actors){
		if (actors.isEmpty())
			return;
		
		if (actors.get(0) instanceof Boulder && steps < 6){
			hasExploded = true;
		}
	}
	
	/**
	 * Gets all the move locations. If the coyote is sleeping, it just returns
	 * its own location. If the coyote has just run into something, it begins sleeping
	 * 
	 * @return	All the possible move locations
	 */
	public ArrayList<Location> getMoveLocations(){
		ArrayList<Location> locs = new ArrayList<>();
		if (sleep == 0){
			if (steps == 5){
				sleep = 1;
				locs.add(getLocation());
			} else if (!getGrid().isValid(getLocation().getAdjacentLocation(getDirection()))){
				sleep = 1;
				locs.add(getLocation());
				hasHitWall = true;
			} else if (getGrid().get(getLocation().getAdjacentLocation(getDirection())) instanceof Actor){
				sleep = 1;
				locs.add(getLocation());
			} else {
				steps ++;
				locs.add(getLocation().getAdjacentLocation(getDirection()));
			}
		} else {
			if (sleep < 6){
				locs.add(getLocation());
				sleep ++;
			} 
			if (sleep == 6){
				return getGrid().getEmptyAdjacentLocations(getLocation());
			}
		}
		return locs;
	}
	
	/**
	 * Chooses the move location. If coyote is sleeping, it just returns
	 * its own location. If coyote's sleep is over then it chooses a 
	 * random direction and moves in that direction. If the coyote hadn't
	 * run into a wall, it will place a stone in a random location.
	 * 
	 * @param locs	The possible move locations
	 * @return		The location to move
	 */
	public Location selectMoveLocation(ArrayList<Location> locs){
		if (sleep > 0 && sleep < 6)
			return getLocation();
		else if (sleep > 0){
			sleep = 0;
			steps = 1;
			
			if (!hasHitWall){
				int locNum1 = (int)(Math.random() * locs.size());
				Stone stone = new Stone();
				stone.putSelfInGrid(getGrid(), locs.get(locNum1));
			}
			hasHitWall = false;
			
			int locNum = (int)(Math.random() * locs.size());
			setDirection(getLocation().getDirectionToward(locs.get(locNum)));
			return locs.get(locNum);
		} else {
			return getLocation().getAdjacentLocation(getDirection());
		}
	}
	
	/**
	 * Actually moves. If the coyote has exploded, it removes itself from
	 * the grid
	 * 
	 * @param loc 	The location to move
	 */
	public void makeMove(Location loc){
		if(hasExploded){
			Kaboom kaboom = new Kaboom();
			kaboom.putSelfInGrid(getGrid(), getLocation().getAdjacentLocation(getDirection()));
			removeSelfFromGrid();
			return;
		}
		moveTo(loc);
	}
}
