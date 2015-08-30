package gui;

import static utils.Constants.TILES_PER_SIDE;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D.Float;
import javax.swing.JComponent;
import listeners.MousePositionRightPressed;
import static utils.Constants.TILE_WIDTH;
import static utils.Constants.TILE_HEIGHT;
import core.SpriteBuffer;
import objects.Tile;
import objects.World;
import static utils.Constants.VISIBLE_TILES;
import utils.Utils;
import annotations.Autowired;

public class Renderer extends JComponent {
    @Autowired
    private World world;
    @Autowired
    private SpriteBuffer spriteBuffer;
    @Autowired
    private MousePositionRightPressed mousePositionRightPressed;

    public void render(Graphics g) {
        int x = 0;
        int y = 0;
        Float colRow = Utils.getColRow(mousePositionRightPressed.getMiddle_x(), mousePositionRightPressed.getMiddle_y());
        int middle_x = (int) Math.ceil(colRow.getX());
        int middle_y = (int) Math.ceil(colRow.getY());
        System.out.println(middle_x + ":" + middle_y);
        for (int i = middle_x - VISIBLE_TILES; i < middle_x + VISIBLE_TILES; i++) {
            for (int j = middle_y - VISIBLE_TILES; j < middle_y + VISIBLE_TILES; j++) {
                if (i >= 0 && i < TILES_PER_SIDE && j >= 0 && j < TILES_PER_SIDE) {
                    Point position = Utils.getIsoXY(i, j);
                    Tile tile = world.getMap()[i][j];
                    x = position.x - TILE_HEIGHT + world.getOffset_x();
                    y = position.y - TILE_WIDTH + world.getOffset_y();

                    switch (tile.getTerrain().getType()) {
                    case GRASS:
                        g.drawImage(spriteBuffer.getTemplate(), x, y, null);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        render(g);
    }

}
