package init;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import listeners.MousePositionRightPressed;
import gui.Renderer;
import annotations.Autowired;

public class GlobalListeners {

    @Autowired
    private MousePositionRightPressed mousePositionRightPressed;
    @Autowired
    private Renderer renderer;

    public void initListeners() {
        renderer.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePositionRightPressed.setPosition(e.getX(), e.getY());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });
    }
}
