package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class DisplayMap extends JComponent {

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
