import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

public class Kaboom extends Actor{
	private int lifetime;
	private final int THRESHOLD = 3;
	
	public Kaboom(){
		lifetime = THRESHOLD;
		setColor(null);
	}
	
	public void act(){
		lifetime --;
		if (lifetime == 0)
			removeSelfFromGrid();
	}
}
