/**
 * The Roadrunner class. The roadrunner will wander around the grid, bumping 
 * into things as it goes. Depending on what it bumps into it will react differently.
 * Usually the coyote is the animal to be feared but this time, the roadrunner
 * bodies the coyote anytime it gets near.
 * 
 * @author Rishabh Goel
 */

import info.gridworld.grid.*;
import info.gridworld.actor.*;
import java.awt.Color;
import java.util.ArrayList;

public class RR extends Critter{
    private boolean hasExploded; // to check whether or not rr has exploded
    private boolean hasBumped; // to check whether the rr has bumped into coyote

    public RR(){
        setColor(null);
        setDirection(Location.NORTH); // direction is predetermined
        hasExploded = false;
        hasBumped = false;
    }

    /**
     * This method does nothing because rr doesn't get any actors
     * 
     * @return literally nothing
     */
    public ArrayList<Actor> getActors(){
        return null;
    }

    /**
     * This method does nothing but is here to override the method in Critter.java
     * that does something
     * 
     * @param actors    The actors to be processed which in this case will always be none
     */
    public void processActors(ArrayList<Actor> actors){
        return;
    }

    /**
     * Finally a method that does something. This method will get all the possible
     * move locations for the rr. It will try to move forward, jumping either 1, 2, or 3
     * spaces at a time. If it runs into a coyote, it bodies it, if it runs into a boulder
     * it dies. If it runs into an actor, it will stop before it hits it. If the location
     * directly in front of the rr is occupied, it will try to move in a random direction.
     * 
     * @return  A list of possible locations to move
     */
    public ArrayList<Location> getMoveLocations(){
        ArrayList<Location> locs = new ArrayList<>();
        Grid<Actor> grid = getGrid();

        Location loc1 = getLocation().getAdjacentLocation(getDirection());
        Location loc2 = loc1.getAdjacentLocation(getDirection());
        Location loc3 = loc2.getAdjacentLocation(getDirection());

        if(grid.isValid(loc1) && grid.get(loc1) instanceof Actor){ // if it runs into an actor on the first go

            //If it is a coyote or a boulder, it will be the only location the rr can move to
            if (grid.get(loc1) instanceof Boulder || grid.get(loc1) instanceof Coyote){
                locs.add(loc1);
                return locs;
            } else { // if it is just any actor, it chooses new location and tries again
                int[] dirs = {Location.NORTH, Location.NORTHEAST, Location.EAST
					, Location.SOUTHEAST, Location.SOUTH, Location.SOUTHWEST
					, Location.WEST, Location.NORTHWEST};
		
                int randomNum = (int)(Math.random() * dirs.length);
                
                setDirection(dirs[randomNum]);
                
                return getMoveLocations();
            }

        //if the square in front is empty, it does the same as above, but for the square in front of that one
        } else if (grid.isValid(loc1) && grid.get(loc1) == null){
            locs.add(loc1);
            if (grid.isValid(loc2) && grid.get(loc2) instanceof Actor){
                if (grid.get(loc2) instanceof Boulder || grid.get(loc2) instanceof Coyote){
                    locs.add(loc2);
                    return locs;
                } else {
                    return locs;
                }
            
            //if the second square is empty, it will finally check the third square
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

    /**
     * Selects a move location randomly. If the location to move to
     * is a coyote or boulder, it will hit the specific toggles accordingly
     * 
     * @param locs  The options to move to
     * @return      Where the rr will move
     */
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

    /**
     * Actually makes the move for the rr.
     * 
     * @param loc   The location to move to
     */
    public void makeMove(Location loc){
        System.out.println(loc);
        if(hasExploded){
			Kaboom kaboom = new Kaboom();
			kaboom.putSelfInGrid(getGrid(), loc);
			removeSelfFromGrid();
            hasExploded = false;
			return;
		} else if (hasBumped){
            getGrid().get(loc).removeSelfFromGrid();
            moveTo(loc);
            hasBumped = false;

            SickCoyote coyote = new SickCoyote();

            ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(loc);
            int rand = (int)(Math.random() * locs.size());

            coyote.putSelfInGrid(getGrid(), locs.get(rand));
        }else{
            moveTo(loc);
        }
    }
}