import info.gridworld.actor.Bug;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug
{
    private int idx;
    private int[]  turns = { 1, 0, 0, 0, 1, 0, 0, 3, 4,
        4, 0, 0, 1, 0, 3, 2, 0, 7,
        0, 0, 0, 3, 2, 1 };;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public DancingBug()
    {
        idx = 0;
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        int len = turns.length;
        for (int i = 0; i < turns[idx % len]; i ++){
            turn();
        }
        move();
        idx ++;
    }
}