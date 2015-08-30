package listeners;

import objects.World;
import annotations.Autowired;

/**
 * track mouse position on right pressed
 * 
 * @author Tomas
 *
 */
public class MousePositionRightPressed {

    private int x;
    private int y;
    private int middle_x;
    private int middle_y;
    private int temporaryOffsetX;
    private int temporaryOffsetY;
    @Autowired
    private World world;

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.temporaryOffsetX = world.getOffset_x();
        this.temporaryOffsetY = world.getOffset_y();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTemporaryOffsetX() {
        return temporaryOffsetX;
    }

    public void setTemporaryOffsetX(int temporaryOffsetX) {
        this.temporaryOffsetX = temporaryOffsetX;
    }

    public int getTemporaryOffsetY() {
        return temporaryOffsetY;
    }

    public void setTemporaryOffsetY(int temporaryOffsetY) {
        this.temporaryOffsetY = temporaryOffsetY;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getMiddle_x() {
        return middle_x;
    }

    public void setMiddle_x(int middle_x) {
        this.middle_x = middle_x;
    }

    public int getMiddle_y() {
        return middle_y;
    }

    public void setMiddle_y(int middle_y) {
        this.middle_y = middle_y;
    }
}
