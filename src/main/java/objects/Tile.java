package objects;

public class Tile {
    private Terrain terrain;
    private GameObject object;

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public GameObject getObject() {
        return object;
    }

    public void setObject(GameObject object) {
        this.object = object;
    }
}
