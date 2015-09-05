package logic;

import global.GlobalValues;
import gui.Renderer;
import objects.EmptyObject;
import objects.Position;
import objects.World;
import annotations.Autowired;

public class RendererLogic {

    @Autowired
    private Renderer renderer;
    @Autowired
    private World world;
    @Autowired
    private GlobalValues globalValues;

    public void build(int x, int y) {

        int max_x = x + globalValues.getConstructBuilding().getLength_x();
        int max_y = y + globalValues.getConstructBuilding().getLength_y();

        for (int i = x; i < max_x; i++) {
            for (int j = y; j < max_y; j++) {
                // true build
                if (i == x && j == y) {
                    world.getMap()[i][j].setObject(globalValues.getConstructBuilding());
                    // set reference to first point
                } else {
                    world.getMap()[i][j].setObject(new EmptyObject());
                    world.getMap()[i][j].getObject().setPosition(new Position(x, y));
                }
            }
        }
        globalValues.setConstructBuilding(null);
        globalValues.setCanBuild(false);
    }

}
