import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
public class SickCoyoteRunner {
    public static void main(String[] args) {
        BoundedGrid<Actor> mygrid = new BoundedGrid<Actor>(5,5);
        ActorWorld world = new ActorWorld(mygrid);

        world.add(new Location(0, 0), new SickCoyote(5));
        world.add(new Location(1, 1), new SickCoyote());
        world.add(new Location(2, 2), new SickCoyote(15));
        world.add(new Location(3, 3), new SickCoyote(20));

        world.show();
    }
}
