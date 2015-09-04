package gui;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.geom.Point2D.Float;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import listeners.MousePositionRightPressed;
import objects.Tile;
import objects.World;
import utils.Utils;
import annotations.Autowired;
import core.SpriteBuffer;
import static utils.Constants.TILES_PER_SIDE;
import static utils.Constants.RENDER_MINIMAP;
import static utils.Constants.MINIMAP_SIDE;
import static utils.Constants.TILE_HEIGHT;
import static utils.Constants.TILE_WIDTH;
import static utils.Constants.RENDERER_HEIGHT;
import static utils.Constants.RENDERER_WIDTH;
import static utils.Constants.MINIMAP_DISPLAY_SIDE;

public class RightPanel extends Canvas {
    private BufferStrategy strategy;
    private Timer timer;
    private TimerTask renderTask;
    private Graphics2D graphics;
    private BufferedImage backgroundImage;
    private double ratio_x;
    private double ratio_y;
    private int window_x;
    private int window_y;
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
        calculateSides();

        start();

    }

    private void render(BufferedImage img) {
        BufferedImage image = img;
        Graphics2D graphics3 = (Graphics2D) image.getGraphics();
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
        graphics3 = (Graphics2D) image.getGraphics();
        graphics3.setColor(Color.WHITE);
        graphics3.drawRect(calculateWindowXPosition(), calculateWindowYPosition(), window_x, window_y);
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
                render(deepCopy(backgroundImage));
            }
        };

        timer.schedule(renderTask, 0, RENDER_MINIMAP);
    }

    private BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    private int calculateWindowXPosition() {
        int number = (int) (((world.getOffset_x() - (TILE_WIDTH * TILES_PER_SIDE / 2)) * (-1)) / ratio_x);
        System.out.println(Utils.calculateProportion(MINIMAP_DISPLAY_SIDE, TILES_PER_SIDE, number));
        return Utils.calculateProportion(MINIMAP_DISPLAY_SIDE, MINIMAP_SIDE, number);
    }

    private int calculateWindowYPosition() {
        int number = (int) ((world.getOffset_y() * (-1)) / ratio_y);
        return Utils.calculateProportion(MINIMAP_DISPLAY_SIDE, MINIMAP_SIDE, number);
    }

    private void calculateSides() {
        ratio_x = TILES_PER_SIDE * TILE_WIDTH / MINIMAP_SIDE;
        ratio_y = TILES_PER_SIDE * TILE_HEIGHT / MINIMAP_SIDE;
        window_x = Utils.calculateProportion(RENDERER_HEIGHT - MINIMAP_DISPLAY_SIDE, TILES_PER_SIDE * TILE_WIDTH, MINIMAP_DISPLAY_SIDE);
        window_y = Utils.calculateProportion(RENDERER_WIDTH, TILES_PER_SIDE * TILE_HEIGHT, MINIMAP_DISPLAY_SIDE);
    };

}
