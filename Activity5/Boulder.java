import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import java.awt.Color;

public class Boulder extends Actor{
	private int lifetime;
	private final int THRESHOLD = 3;
	
	public Boulder(){
		lifetime = (int)(Math.random() * 201) + 1;
		if (lifetime <= THRESHOLD)
			setColor(Color.RED);
		else
			setColor(null);
	}
	
	public Boulder(int lifeIn){
		lifetime = lifeIn;
		if (lifetime <= THRESHOLD)
			setColor(Color.RED);
		else
			setColor(null);
	}
	
	public void act(){
		lifetime --;
		if (lifetime == THRESHOLD){
			setColor(Color.RED);
		}
		if (lifetime <= 0){
			Location loc = getLocation();
			Grid<Actor> grid = getGrid();
			removeSelfFromGrid();
			Kaboom kb = new Kaboom();
			kb.putSelfInGrid(grid, loc);
		}
	}
}
