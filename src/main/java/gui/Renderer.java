package gui;

import static utils.Constants.TILES_PER_SIDE;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D.Float;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;
import listeners.MousePositionRightPressed;
import static utils.Constants.TILE_WIDTH;
import static utils.Constants.TILE_HEIGHT;
import core.SpriteBuffer;
import objects.Tile;
import objects.World;
import static utils.Constants.VISIBLE_TILES;
import utils.Utils;
import annotations.Autowired;

public class Renderer extends Canvas {
    private BufferStrategy strategy;
    private Timer timer;
    private TimerTask renderTask;
    private Graphics2D bkG;
    @Autowired
    private World world;
    @Autowired
    private SpriteBuffer spriteBuffer;
    @Autowired
    private MousePositionRightPressed mousePositionRightPressed;

    public void init() {
        timer = new Timer();
        this.createBufferStrategy(2);
        strategy = this.getBufferStrategy();
        this.setIgnoreRepaint(true);
        start();
    }

    public void render() {
        bkG = (Graphics2D) strategy.getDrawGraphics();
        bkG.fillRect(0, 0, getWidth(), getHeight());

        int x = 0;
        int y = 0;
        int offset_x = world.getOffset_x();
        int offset_y = world.getOffset_y();
        Float colRow = Utils.getColRow(mousePositionRightPressed.getMiddle_x(), mousePositionRightPressed.getMiddle_y());
        int middle_x = (int) Math.ceil(colRow.getX());
        int middle_y = (int) Math.ceil(colRow.getY());

        for (int i = middle_x - VISIBLE_TILES; i < middle_x + VISIBLE_TILES; i++) {
            for (int j = middle_y - VISIBLE_TILES; j < middle_y + VISIBLE_TILES; j++) {
                if (i >= 0 && i < TILES_PER_SIDE && j >= 0 && j < TILES_PER_SIDE) {
                    Point position = Utils.getIsoXY(i, j);
                    Tile tile = world.getMap()[i][j];
                    x = position.x - TILE_HEIGHT + offset_x;
                    y = position.y - TILE_WIDTH + offset_y;

                    switch (tile.getTerrain().getType()) {
                    case GRASS:
                        AffineTransform t = new AffineTransform();
                        t.translate(x, y);
                        bkG.drawImage(spriteBuffer.getTemplate(), t, null);
                        break;
                    }
                }
            }
        }
        bkG.dispose();
        strategy.show();
        Toolkit.getDefaultToolkit().sync();

    }

    public void start() {
        if (renderTask != null) {
            renderTask.cancel();
        }

        renderTask = new TimerTask() {

            @Override
            public void run() {
                render();
            }
        };

        timer.schedule(renderTask, 0, 20);
    }

    protected void stop() {
        renderTask.cancel();
    }

}
