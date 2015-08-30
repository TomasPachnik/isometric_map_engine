package listeners;

import gui.Renderer;
import init.GlobalInit;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D.Float;

import javax.swing.SwingUtilities;

import objects.World;
import annotations.Autowired;
import utils.Utils;
import static utils.Constants.TILES_PER_SIDE;

public class MouseMotionListenerImpl implements MouseMotionListener {

    @Autowired
    private Renderer renderer;
    @Autowired
    private World world;
    @Autowired
    private MousePositionRightPressed mousePositionRightPressed;
    @Autowired
    private GlobalInit globalInit;

    @Override
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            world.setOffset_x(mousePositionRightPressed.getTemporaryOffsetX() + (e.getX() - mousePositionRightPressed.getX()));
            world.setOffset_y(mousePositionRightPressed.getTemporaryOffsetY() + (e.getY() - mousePositionRightPressed.getY()));
            globalInit.init();
            renderer.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Float colRow = Utils.getColRow(e.getX() - world.getOffset_x(), e.getY() - world.getOffset_y());
        int x = (int) Math.ceil(colRow.getX());
        int y = (int) Math.ceil(colRow.getY());
        if (x >= 0 && x < TILES_PER_SIDE && y >= 0 && y < TILES_PER_SIDE) {
            world.getMap()[x][y].setSelected(true);
            renderer.repaint();
           // System.out.println(x + ":" + y);
        }
    }

}
