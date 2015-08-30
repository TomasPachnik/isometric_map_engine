package objects;

import static utils.Constants.TILES_PER_SIDE;

public class World {
    private Tile[][] map;
    private int offset_x = 0;
    private int offset_y = 0;

    public World() {
        setMap(new Tile[TILES_PER_SIDE][TILES_PER_SIDE]);
        for (int i = 0; i < TILES_PER_SIDE; i++) {
            for (int j = 0; j < TILES_PER_SIDE; j++) {
                map[i][j] = new Tile();
            }
        }
    }

    public Tile[][] getMap() {
        return map;
    }

    public void setMap(Tile[][] map) {
        this.map = map;
    }

    public int getOffset_x() {
        return offset_x;
    }

    public void setOffset_x(int offset_x) {
        this.offset_x = offset_x;
    }

    public int getOffset_y() {
        return offset_y;
    }

    public void setOffset_y(int offset_y) {
        this.offset_y = offset_y;
    }
}
