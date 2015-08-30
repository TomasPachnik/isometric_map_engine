package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Stats extends JComponent {

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
