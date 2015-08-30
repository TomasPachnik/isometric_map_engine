package gui;

import static utils.Constants.TILES_PER_SIDE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;
import static utils.Constants.TILE_WIDTH;
import static utils.Constants.TILE_HEIGHT;
import core.SpriteBuffer;
import objects.Tile;
import objects.World;
import utils.Utils;
import annotations.Autowired;

public class Renderer extends JComponent {
    @Autowired
    private World world;
    @Autowired
    private SpriteBuffer spriteBuffer;

    public void render(Graphics g) {
        for (int i = 0; i < TILES_PER_SIDE; i++) {
            for (int j = 0; j < TILES_PER_SIDE; j++) {
                Point position = Utils.getIsoXY(i, j);
                Tile tile = world.getMap()[i][j];
                if (tile.isSelected()) {
                    g.drawImage(spriteBuffer.getGreyscale(), position.x - TILE_HEIGHT + world.getOffset_x(), position.y - TILE_WIDTH + world.getOffset_y(),
                            null);
                } else {
                    g.drawImage(spriteBuffer.getTemplate(), position.x - TILE_HEIGHT + world.getOffset_x(), position.y - TILE_WIDTH + world.getOffset_y(), null);
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        render(g);
    }

}
