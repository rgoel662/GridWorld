/**
 * Tester class for the Coyote
 * 
 * @author Rishabh Goel
 */

import info.gridworld.grid.*;
import info.gridworld.actor.*;
import java.awt.Color;
import java.util.ArrayList;

public class CoyoteRunner{
	
	//main
	public static void main(String[] args){
		BoundedGrid<Actor> mygrid = new BoundedGrid<Actor>(10,10);
        ActorWorld world = new ActorWorld(mygrid);
        
        world.add(new Location(2,2),new Coyote());
        world.add(new Location(5,5),new Coyote());
        // world.add(new Location(4,2),new Coyote());
        // world.add(new Location(5,6),new Coyote());
        world.show();
	}
}
