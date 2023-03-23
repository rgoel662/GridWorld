import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import java.util.ArrayList;
import java.util.List;

public class Jumper extends Bug{

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public Jumper()
    {
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
		Grid<Actor> grid = getGrid();
		
		Location currentLoc = getLocation();
		Location next = getLocation().getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
		
		if (grid.isValid(next) && 
			(grid.get(next) == null || grid.get(next) instanceof Blossom || 
			grid.get(next) instanceof Flower)){
				
			moveTo(next);
			
			Blossom flower = new Blossom();
			flower.putSelfInGrid(grid, currentLoc);
			
		} else {
			turn();
		}
    }
}
