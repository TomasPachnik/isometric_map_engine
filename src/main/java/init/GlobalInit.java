package init;

import java.awt.geom.Point2D.Float;

import listeners.MousePositionRightPressed;
import objects.World;
import annotations.Autowired;

public class GlobalInit {

    @Autowired
    private World world;
    @Autowired
    private MousePositionRightPressed mousePositionRightPressed;

    public void init() {
        // size od renderer divided by 2
        int x = 800 / 2;
        int y = 570 / 2;
        mousePositionRightPressed.setMiddle_x(x - world.getOffset_x());
        mousePositionRightPressed.setMiddle_y(y - world.getOffset_y());
    }
}
