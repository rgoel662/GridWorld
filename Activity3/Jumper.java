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

import java.awt.Color;

public class Jumper extends Bug{

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public Jumper()
    {
      setColor(Color.BLUE);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
      Grid<Actor> grid = getGrid();
      
      Location currentLoc = getLocation();
      Location next = getLocation().getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
      Location next2 = getLocation().getAdjacentLocation(getDirection());
      
      if (grid.isValid(next) && 
        (grid.get(next) == null || grid.get(next) instanceof Flower)){
        
        moveTo(next);
        
        Blossom flower = new Blossom();
        flower.putSelfInGrid(grid, currentLoc);
      } else if (grid.isValid(next2) && 
          (grid.get(next2) == null || grid.get(next2) instanceof Flower)){ 
          
          moveTo(next2);
      
          Blossom flower = new Blossom();
          flower.putSelfInGrid(grid, currentLoc);
                
      } else {
        turn();
      }
    }
}
