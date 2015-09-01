package gui;

import static utils.Constants.TILES_PER_SIDE;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D.Float;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
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

public class Renderer extends Canvas {
    private BufferStrategy strategy;
    private Timer timer;
    private TimerTask renderTask;
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
        this.setLocation(-20, -20);
    }

    public void render() {
        Graphics2D bkG = (Graphics2D) strategy.getDrawGraphics();
        bkG.fillRect(0, 0, getWidth(), getHeight());

        int x = 0;
        int y = 0;
        Float colRow = Utils.getColRow(mousePositionRightPressed.getMiddle_x(), mousePositionRightPressed.getMiddle_y());
        int middle_x = (int) Math.ceil(colRow.getX());
        int middle_y = (int) Math.ceil(colRow.getY());
        for (int i = middle_x - VISIBLE_TILES; i < middle_x + VISIBLE_TILES; i++) {
            for (int j = middle_y - VISIBLE_TILES; j < middle_y + VISIBLE_TILES; j++) {
                if (i >= 0 && i < TILES_PER_SIDE && j >= 0 && j < TILES_PER_SIDE) {
                    Point position = Utils.getIsoXY(i, j);
                    Tile tile = world.getMap()[i][j];
                    x = position.x - TILE_HEIGHT + world.getOffset_x();
                    y = position.y - TILE_WIDTH + world.getOffset_y();

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

    /**
     * Stops the rendering cycle so that the application can close gracefully.
     */
    protected void stop() {
        renderTask.cancel();
    }

    // @Override
    // public void paint(Graphics g) {
    // render(g);
    // }

}
