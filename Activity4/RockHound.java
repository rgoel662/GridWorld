import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;

import java.awt.Color;
import java.util.ArrayList;

public class RockHound extends Critter{
    
    public void processActors(ArrayList<Actor> actors){
        for (Actor actor: actors){
            if (actor instanceof Rock){
                actor.removeSelfFromGrid();
            }
        }
    }
}