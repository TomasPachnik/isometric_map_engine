package listeners;

import global.GlobalValues;
import gui.Renderer;
import gui.RightPanel;
import init.GlobalInit;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D.Float;

import javax.swing.SwingUtilities;

import logic.RendererLogic;
import objects.World;
import annotations.Autowired;
import utils.Utils;
import static utils.Constants.TILES_PER_SIDE;

public class RendererMouseListener implements MouseListener, MouseMotionListener {

    @Autowired
    private Renderer renderer;
    @Autowired
    private World world;
    @Autowired
    private MousePositionRightPressed mousePositionRightPressed;
    @Autowired
    private GlobalInit globalInit;
    @Autowired
    private GlobalValues globalValues;
    @Autowired
    private MouseDataObject mouseDataObject;
    @Autowired
    private RightPanel rightPanel;
    @Autowired
    private RendererLogic rendererLogic;

    @Override
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            world.setOffset_x(mousePositionRightPressed.getTemporaryOffsetX() + (e.getX() - mousePositionRightPressed.getX()));
            world.setOffset_y(mousePositionRightPressed.getTemporaryOffsetY() + (e.getY() - mousePositionRightPressed.getY()));
            globalInit.init();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Float colRow = Utils.getColRow(e.getX() - world.getOffset_x(), e.getY() - world.getOffset_y());
        int x = (int) Math.ceil(colRow.getX());
        int y = (int) Math.ceil(colRow.getY());
        if (x >= 0 && x < TILES_PER_SIDE && y >= 0 && y < TILES_PER_SIDE) {
            Utils.getColRow(x, y);
            globalValues.setCurrentlySelectedTile_x(x);
            globalValues.setCurrentlySelectedTile_y(y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // build mode
        if (globalValues.getConstructBuilding() != null) {
            if (globalValues.isCanBuild()) {
                Float colRow = Utils.getColRow(e.getX() - world.getOffset_x(), e.getY() - world.getOffset_y());
                int x = (int) Math.ceil(colRow.getX());
                int y = (int) Math.ceil(colRow.getY());
                if (x >= 0 && x < TILES_PER_SIDE && y >= 0 && y < TILES_PER_SIDE) {
                    Utils.getColRow(x, y);
                    globalValues.setCurrentlySelectedTile_x(x);
                    globalValues.setCurrentlySelectedTile_y(y);
                }
                rendererLogic.build(x, y);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
