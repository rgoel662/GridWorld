import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

import java.awt.Color;

/**
 * A <code>Flower</code> is an actor that darkens over time. Some actors drop
 * flowers as they move. <br />
 * The API of this class is testable on the AP CS A and AB exams.
 */

public class Blossom extends Flower
{
    private static final Color DEFAULT_COLOR = Color.GREEN;
    private static final double DARKENING_FACTOR = 0.1;
    private int acts;
    private int lifetime = 10;

    // lose 10% of color value in each step

    /**
     * Constructs a green flower.
     */
    public Blossom()
    {
        setColor(DEFAULT_COLOR);
        acts = 0;
        lifetime = 10;
    }
    
    public Blossom(int life){
		this();
		lifetime = life;
	}

    /**
     * Causes the color of this flower to darken.
     */
    public void act()
    {
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

        setColor(new Color(red, green, blue));
        acts ++;
        if (acts >= lifetime){
			removeSelfFromGrid();
		}
        
        
    }
}
