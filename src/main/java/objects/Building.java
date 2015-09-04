package objects;

import enums.BuildingEnum;

public class Building extends GameObject {
    private BuildingEnum building;
    private int length_x;
    private int length_y;

    public Building(BuildingEnum building, int length_x, int length_y) {
        super();
        this.building = building;
        this.length_x = length_x;
        this.length_y = length_y;
    }

    public BuildingEnum getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEnum building) {
        this.building = building;
    }

    public int getLength_x() {
        return length_x;
    }

    public void setLength_x(int length_x) {
        this.length_x = length_x;
    }

    public int getLength_y() {
        return length_y;
    }

    public void setLength_y(int length_y) {
        this.length_y = length_y;
    }
}
