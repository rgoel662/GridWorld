import info.gridworld.grid.*;
import info.gridworld.actor.*;
import java.awt.Color;
import java.util.ArrayList;

public class RR extends Critter{
    private boolean hasExploded;
    private boolean hasBumped;

    public RR(){
        setColor(null);
        setDirection(Location.NORTH);
        hasExploded = false;
        hasBumped = false;
    }

    public ArrayList<Actor> getActors(){
        return null;
    }

    public void processActors(ArrayList<Actor> actors){
        return;
    }

    public ArrayList<Location> getMoveLocations(){
        ArrayList<Location> locs = new ArrayList<>();
        Grid<Actor> grid = getGrid();

        Location loc1 = getLocation().getAdjacentLocation(getDirection());
        Location loc2 = loc1.getAdjacentLocation(getDirection());
        Location loc3 = loc2.getAdjacentLocation(getDirection());

        if(grid.isValid(loc1) && grid.get(loc1) instanceof Actor){
            if (grid.get(loc1) instanceof Boulder || grid.get(loc1) instanceof Coyote){
                locs.add(loc1);
                return locs;
            } else {
                int[] dirs = {Location.NORTH, Location.NORTHEAST, Location.EAST
					, Location.SOUTHEAST, Location.SOUTH, Location.SOUTHWEST
					, Location.WEST, Location.NORTHWEST};
		
                int randomNum = (int)(Math.random() * dirs.length);
                
                setDirection(dirs[randomNum]);
                
                return getMoveLocations();
            }
        } else if (grid.isValid(loc1) && grid.get(loc1) == null){
            locs.add(loc1);
            if (grid.isValid(loc2) && grid.get(loc2) instanceof Actor){
                if (grid.get(loc2) instanceof Boulder || grid.get(loc2) instanceof Coyote){
                    locs.add(loc2);
                    return locs;
                } else {
                    return locs;
                }
            } else if (grid.isValid(loc2) && grid.get(loc2) == null){
                locs.add(loc2);
                if (grid.isValid(loc3) && grid.get(loc3) instanceof Actor){
                    if (grid.get(loc3) instanceof Boulder || grid.get(loc3) instanceof Coyote){
                        locs.add(loc3);
                        return locs;
                    } else {
                        return locs;
                    }
                } else if (grid.isValid(loc3) && grid.get(loc3) == null){
                    locs.add(loc3);
                    return locs;
                } else {
                    return locs;
                }
            } else {
                return locs;
            }
        } else {
            int[] dirs = {Location.NORTH, Location.NORTHEAST, Location.EAST
                , Location.SOUTHEAST, Location.SOUTH, Location.SOUTHWEST
                , Location.WEST, Location.NORTHWEST};
    
            int randomNum = (int)(Math.random() * dirs.length);
            
            setDirection(dirs[randomNum]);
            
            return getMoveLocations();
        }
    }

    public Location selectMoveLocation(ArrayList<Location> locs){
        int rand = (int)(Math.random() * locs.size());
        Location choice = locs.get(rand);
        if (getGrid().get(choice) instanceof Coyote){
            hasBumped = true;
        } else if (getGrid().get(choice) instanceof Boulder){
            hasExploded = true;
        }

        return choice;
    }

    public void makeMove(Location loc){
        if(hasExploded){
			Kaboom kaboom = new Kaboom();
			kaboom.putSelfInGrid(getGrid(), getLocation().getAdjacentLocation(dir));
			removeSelfFromGrid();
			return;
		} else if (hasBumped){
            getGrid().get(loc).removeSelfFromGrid();
            moveTo(loc);

            SickCoyote coyote = new SickCoyote();

            ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations();
            int rand = (int)(Math.random() * locs.size());

            coyote.putSelfInGrid(getGrid(), locs.get(rand));
        }
    }
}