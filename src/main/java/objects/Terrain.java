package objects;

import enums.TerrainEnum;

public abstract class Terrain {
    private TerrainEnum type;

    public Terrain(TerrainEnum type) {
        this.setType(type);
    }

    public TerrainEnum getType() {
        return type;
    }

    public void setType(TerrainEnum type) {
        this.type = type;
    }
}
