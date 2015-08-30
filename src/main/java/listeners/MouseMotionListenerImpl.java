package listeners;

import gui.Renderer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D.Float;

import objects.World;
import annotations.Autowired;
import utils.Utils;

public class MouseMotionListenerImpl implements MouseMotionListener {

    @Autowired
    private Renderer renderer;
    @Autowired
    private World world;

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Float colRow = Utils.getColRow(e.getX(), e.getY());
        int x = (int) Math.ceil(colRow.getX());
        int y = (int) Math.ceil(colRow.getY());
        if (x >= 0 && y >= 0) {
            world.getMap()[x][y].setSelected(true);
            renderer.repaint();
        }
    }

}
