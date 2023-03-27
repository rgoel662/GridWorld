import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

public class ChameleonKid extends ChameleonCritter
{	
	public ArrayList<Actor> getActors()
    {   
        Grid<Actor> grid = getGrid();
        Location loc1 = getLocation().getAdjacentLocation(getDirection());
        Location loc2 = getLocation().getAdjacentLocation(getDirection() + Location.HALF_CIRCLE);

        ArrayList<Actor> actors = new ArrayList<Actor>();
        if (grid.isValid(loc1) && grid.get(loc1) != null)
            actors.add(grid.get(loc1));
        if(grid.isValid(loc2) && grid.get(loc2) != null)
            actors.add(grid.get(loc2));

        return actors;
    }
}
