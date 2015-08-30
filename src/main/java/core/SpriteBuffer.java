package core;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteBuffer {
    private BufferedImage template;
    private BufferedImage greyscale;

    public SpriteBuffer() throws IOException {
        template = ImageIO.read(getClass().getClassLoader().getResourceAsStream("template.png"));
        greyscale = ImageIO.read(getClass().getClassLoader().getResourceAsStream("greyscale.png"));
    }

    public BufferedImage getTemplate() {
        return template;
    }

    public BufferedImage getGreyscale() {
        return greyscale;
    }

}
