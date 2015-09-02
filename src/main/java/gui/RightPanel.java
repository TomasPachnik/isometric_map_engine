package gui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.geom.Point2D.Float;
import java.awt.geom.Rectangle2D;
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
        BufferedImage image = new BufferedImage(TILES_PER_SIDE * 2, TILES_PER_SIDE * 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics3 = (Graphics2D) image.createGraphics();
        graphics3.setComposite(AlphaComposite.Clear);
        graphics3.fillRect(0, 0, TILES_PER_SIDE * 2, TILES_PER_SIDE * 2);

        graphics = (Graphics2D) strategy.getDrawGraphics();
        graphics3.setComposite(AlphaComposite.Src);
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
        graphics3.setColor(Color.WHITE);
        image = resizeImage(image);
        graphics3 = (Graphics2D) image.getGraphics();
        graphics3.drawRect(calculateWindowXPosition(), calculateWindowYPosition(), window_x, window_y);

        graphics.drawImage(img, null, 0, 0);
        graphics.drawImage(image, null, 0, 0);
        graphics.dispose();
        strategy.show();
        Toolkit.getDefaultToolkit().sync();


        File outputfile = new File("saved.png");
        try {
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
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
        return resizeImage(img);
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
        return (world.getOffset_x() / (TILES_PER_SIDE * TILE_WIDTH / MINIMAP_SIDE) * (-1)) + (MINIMAP_SIDE / 2);
    }

    private int calculateWindowYPosition() {
        return (world.getOffset_y() / (TILES_PER_SIDE * TILE_HEIGHT / MINIMAP_SIDE) * (-1));
    }

    private void calculateSides() {
        ratio_x = TILES_PER_SIDE * TILE_WIDTH / MINIMAP_SIDE;
        ratio_y = TILES_PER_SIDE * TILE_HEIGHT / MINIMAP_SIDE;
        window_x = (int) Math.round((RENDERER_HEIGHT - MINIMAP_SIDE) / ratio_x);
        window_y = (int) Math.round(RENDERER_WIDTH / ratio_y);
        System.out.println(window_x + ":" + window_y);
    };

}
