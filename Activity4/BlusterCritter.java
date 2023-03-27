import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;

import java.awt.Color;
import java.util.ArrayList;

public class BlusterCritter extends Critter{

    private int courage;
    private final double DARKEN = 0.10;
    private final double LIGHTEN = 0.30;

    public BlusterCritter(){
        courage = 3;
    }

    public BlusterCritter(int courageIn){
        this();
        courage = courageIn;
    }

    public ArrayList<Actor> getActors(){
        Grid<Actor> grid = getGrid();
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int row = getLocation().getRow();
        int col = getLocation().getCol();

        for (int i = row - 2; i < row + 3; i ++){
            for (int j = col - 2; j < col + 3; j++){
                Location loc = new Location(i, j);
                if (grid.isValid(loc) && grid.get(loc) != null && grid.get(loc) instanceof Critter){
                    actors.add(grid.get(loc));
                }
                if(i == row && j == col){
                    actors.remove(grid.get(loc));
                }
            }
        }

        return actors;
    }

    public void processActors(ArrayList<Actor> actors){
        if (actors.size() >= courage){
            Color c = getColor();
			int red = (int) (c.getRed() * (1 - DARKEN));
			int green = (int) (c.getGreen() * (1 - DARKEN));
			int blue = (int) (c.getBlue() * (1 - DARKEN));

			setColor(new Color(red, green, blue));
        } else {
            Color c = getColor();
            int red = Math.min(255, (int) (c.getRed() * (1 + LIGHTEN)));
            int green = Math.min((int) (c.getGreen() * (1 + LIGHTEN)), 255);
            int blue = Math.min((int) (c.getBlue() * (1 + LIGHTEN)), 255);

            setColor(new Color(red, green, blue));
        }
    }
}