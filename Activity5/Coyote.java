import info.gridworld.grid.*;
import info.gridworld.actor.*;
import java.awt.Color;
import java.util.ArrayList;

public class Coyote extends Critter{
	
	private int steps;
	private int sleep;
	private boolean exploded;
	private boolean hitWall;
	
	public Coyote(){
		int[] dirs = {Location.NORTH, Location.NORTHEAST, Location.EAST
					, Location.SOUTHEAST, Location.SOUTH, Location.SOUTHWEST
					, Location.WEST, Location.NORTHWEST};
		
		int randomNum = (int)(Math.random() * 8);
		
		setDirection(dirs[randomNum]);
		setColor(null);
		
		steps = 0;
		sleep = 0;
		exploded = false;
		hitWall = false;
	}
	
	public ArrayList<Actor> getActors(){
		ArrayList<Actor> actors = new ArrayList<>();
		if (getGrid().isValid(getLocation().getAdjacentLocation(getDirection())) && 
			getGrid().get(getLocation().getAdjacentLocation(getDirection())) instanceof Actor)
			actors.add(getGrid().get(getLocation().getAdjacentLocation(getDirection())));
		return actors;
	}
	
	public void processActors(ArrayList<Actor> actors){
		if (actors.isEmpty())
			return;
		
		if (actors.get(0) instanceof Boulder && steps < 6){
			exploded = true;
		}
	}
	
	public ArrayList<Location> getMoveLocations(){
		ArrayList<Location> locs = new ArrayList<>();
		if (sleep == 0){
			if (steps == 5){
				sleep = 1;
				locs.add(getLocation());
			} else if (!getGrid().isValid(getLocation().getAdjacentLocation(getDirection()))){
				sleep = 1;
				locs.add(getLocation());
				hitWall = true;
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
	
	public Location selectMoveLocation(ArrayList<Location> locs){
		if (sleep > 0 && sleep < 6)
			return getLocation();
		else if (sleep > 0){
			sleep = 0;
			steps = 1;
			
			if (!hitWall){
				int locNum1 = (int)(Math.random() * locs.size());
				Stone stone = new Stone();
				stone.putSelfInGrid(getGrid(), locs.get(locNum1));
			}
			hitWall = false;
			
			int locNum = (int)(Math.random() * locs.size());
			setDirection(getLocation().getDirectionToward(locs.get(locNum)));
			return locs.get(locNum);
		} else {
			return getLocation().getAdjacentLocation(getDirection());
		}
	}
	
	public void makeMove(Location loc){
		if(exploded){
			Kaboom kaboom = new Kaboom();
			kaboom.putSelfInGrid(getGrid(), getLocation().getAdjacentLocation(getDirection()));
			removeSelfFromGrid();
			return;
		}
		moveTo(loc);
	}
}
