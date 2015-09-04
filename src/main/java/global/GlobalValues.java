package global;

import objects.Building;
import objects.Castle;

public class GlobalValues {

    private int currentlySelectedTile_x;
    private int currentlySelectedTile_y;
    private Building constructBuilding = new Castle();

    public int getCurrentlySelectedTile_x() {
        return currentlySelectedTile_x;
    }

    public void setCurrentlySelectedTile_x(int currentlySelectedTile_x) {
        this.currentlySelectedTile_x = currentlySelectedTile_x;
    }

    public int getCurrentlySelectedTile_y() {
        return currentlySelectedTile_y;
    }

    public void setCurrentlySelectedTile_y(int currentlySelectedTile_y) {
        this.currentlySelectedTile_y = currentlySelectedTile_y;
    }

    public Building getConstructBuilding() {
        return constructBuilding;
    }

    public void setConstructBuilding(Building constructBuilding) {
        this.constructBuilding = constructBuilding;
    }

}
