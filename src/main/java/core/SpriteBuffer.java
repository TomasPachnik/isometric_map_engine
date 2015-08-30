package core;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteBuffer {
    private BufferedImage template;
    private BufferedImage greyscale;
    private BufferedImage forest;

    public SpriteBuffer() throws IOException {
        template = ImageIO.read(getClass().getClassLoader().getResourceAsStream("template.png"));
        greyscale = ImageIO.read(getClass().getClassLoader().getResourceAsStream("greyscale.png"));
        forest = (ImageIO.read(getClass().getClassLoader().getResourceAsStream("sprites/forest.png")));
    }

    public BufferedImage getTemplate() {
        return template;
    }

    public BufferedImage getGreyscale() {
        return greyscale;
    }

    public BufferedImage getForest() {
        return forest;
    }

    public void setForest(BufferedImage forest) {
        this.forest = forest;
    }

}
