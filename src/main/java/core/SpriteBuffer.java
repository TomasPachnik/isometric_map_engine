package core;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteBuffer {
    private BufferedImage template;
    private BufferedImage redTemplate;
    private BufferedImage forest;
    private BufferedImage castle;
    private BufferedImage greenTemplate;

    public SpriteBuffer() throws IOException {
        template = ImageIO.read(getClass().getClassLoader().getResourceAsStream("template.png"));
        redTemplate = ImageIO.read(getClass().getClassLoader().getResourceAsStream("redTemplate.png"));
        forest = ImageIO.read(getClass().getClassLoader().getResourceAsStream("sprites/forest.png"));
        castle = ImageIO.read(getClass().getClassLoader().getResourceAsStream("sprites/castle.png"));
        greenTemplate = ImageIO.read(getClass().getClassLoader().getResourceAsStream("greenTemplate.png"));
    }

    public BufferedImage getTemplate() {
        return template;
    }

    public BufferedImage getForest() {
        return forest;
    }

    public BufferedImage getRedTemplate() {
        return redTemplate;
    }

    public BufferedImage getCastle() {
        return castle;
    }

    public BufferedImage getGreenTemplate() {
        return greenTemplate;
    }

    public void setGreenTemplate(BufferedImage greenTemplate) {
        this.greenTemplate = greenTemplate;
    }
}
