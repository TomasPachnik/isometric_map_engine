package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import listeners.MousePositionRightPressed;
import objects.Tile;
import objects.World;
import utils.Utils;
import annotations.Autowired;
import core.SpriteBuffer;

public class RightPanel extends Canvas {
    private BufferStrategy strategy;
    private Timer timer;
    private TimerTask renderTask;
    private Graphics2D graphics;
    private BufferedImage backgroundImage;
    private BufferedImage image;
    @Autowired
    private World world;
    @Autowired
    private SpriteBuffer spriteBuffer;
    @Autowired
    private MousePositionRightPressed mousePositionRightPressed;

    public void init() {
        timer = new Timer();
        this.createBufferStrategy(1);
        strategy = this.getBufferStrategy();
        this.setIgnoreRepaint(true);
        graphics = (Graphics2D) strategy.getDrawGraphics();
        graphics.fillRect(0, 0, 150, 150);
        backgroundImage = mapBackground();
        start();

    }

    private void render() {
        image = backgroundImage;
        Graphics graphics3 = image.getGraphics();
        graphics = (Graphics2D) strategy.getDrawGraphics();

        for (int i = 0; i < TILES_PER_SIDE; i++) {
            for (int j = 0; j < TILES_PER_SIDE; j++) {
                Tile tile = world.getMap()[i][j];
                Point minimapIsoXY = Utils.getMinimapIsoXY(i, j);
                switch (tile.getObject().getNumber()) {
                case 0:
                    break;
                case 1:
                    graphics3.setColor(Color.BLUE);
                    graphics3.fillRect(minimapIsoXY.x + TILES_PER_SIDE, minimapIsoXY.y, 4, 4);
                    break;
                }
            }
        }
        image = resizeImage(image);
        graphics.drawImage(image, null, 0, 0);
        graphics.dispose();
        strategy.show();
        Toolkit.getDefaultToolkit().sync();

    }

    private BufferedImage mapBackground() {
        BufferedImage img = new BufferedImage(TILES_PER_SIDE * 2, TILES_PER_SIDE * 2, BufferedImage.TYPE_INT_RGB);
        Graphics graphics2 = img.getGraphics();
        for (int i = 0; i < TILES_PER_SIDE; i++) {
            for (int j = 0; j < TILES_PER_SIDE; j++) {
                Tile tile = world.getMap()[i][j];
                Point minimapIsoXY = Utils.getMinimapIsoXY(i, j);
                switch (tile.getTerrain().getType()) {
                case GRASS:
                    graphics2.setColor(Color.green.brighter());
                    graphics2.fillRect(minimapIsoXY.x + TILES_PER_SIDE, minimapIsoXY.y, 2, 2);
                    break;
                }
            }
        }
        graphics2.dispose();
        return img;
    }

    private BufferedImage resizeImage(BufferedImage originalImage) {
        BufferedImage resizedImage = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 150, 150, null);
        g.dispose();

        return resizedImage;
    }

    private void start() {
        if (renderTask != null) {
            renderTask.cancel();
        }

        renderTask = new TimerTask() {

            @Override
            public void run() {
                render();
            }
        };

        timer.schedule(renderTask, 0, 1000);
    }

    protected void stop() {
        renderTask.cancel();
    }
}
