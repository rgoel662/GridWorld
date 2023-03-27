import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;

import java.awt.Color;
import java.util.ArrayList;

public class KingCrab extends CrabCritter{
    public void processActors(ArrayList<Actor> actors){
        Grid<Actor> grid = getGrid();
        for (Actor actor: actors){
            int newDir = actor.getLocation().getDirectionToward(getLocation()) + Location.HALF_CIRCLE;
            actor.setDirection(newDir);

            Location loc = actor.getLocation().getAdjacentLocation(newDir);
            if (grid.isValid(loc) && grid.get(loc) == null){
                actor.moveTo(loc);
            } else {
                actor.removeSelfFromGrid();
            }
        }
    }
}