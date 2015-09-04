package core;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteBuffer {
    private BufferedImage template;
    private BufferedImage redTemplate;
    private BufferedImage forest;

    public SpriteBuffer() throws IOException {
        template = ImageIO.read(getClass().getClassLoader().getResourceAsStream("template.png"));
        redTemplate = (ImageIO.read(getClass().getClassLoader().getResourceAsStream("redTemplate.png")));
        forest = (ImageIO.read(getClass().getClassLoader().getResourceAsStream("sprites/forest.png")));
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

}
