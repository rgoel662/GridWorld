import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;

import java.awt.Color;
import java.util.ArrayList;

public class QuickCrab extends CrabCritter{

    public ArrayList<Location> getMoveLocations(){
        ArrayList<Location> locations = new ArrayList<>();
        Grid<Actor> grid = getGrid();
        int dir1 = getDirection() + Location.LEFT;
        int dir2 = getDirection() + Location.RIGHT;
        Location loc1 = getLocation().getAdjacentLocation(dir1).getAdjacentLocation(dir1);
        Location loc2 = getLocation().getAdjacentLocation(dir1);
        Location loc3 = getLocation().getAdjacentLocation(dir2).getAdjacentLocation(dir2);
        Location loc4 = getLocation().getAdjacentLocation(dir2);
        
        if (grid.isValid(loc1) && grid.get(loc1) == null && grid.isValid(loc2) && grid.get(loc2) == null)
            locations.add(loc1);
        if (grid.isValid(loc3) && grid.get(loc3) == null && grid.isValid(loc4) && grid.get(loc4) == null)
            locations.add(loc3);
        
        return locations;
    }
}
