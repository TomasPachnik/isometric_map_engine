package init;

import listeners.MousePositionRightPressed;
import objects.World;
import annotations.Autowired;
import static utils.Constants.RENDERER_HEIGHT;
import static utils.Constants.RENDERER_WIDTH;

public class GlobalInit {

    @Autowired
    private World world;
    @Autowired
    private MousePositionRightPressed mousePositionRightPressed;

    public void init() {
        int x = RENDERER_HEIGHT / 2;
        int y = RENDERER_WIDTH / 2;
        mousePositionRightPressed.setMiddle_x(x - world.getOffset_x());
        mousePositionRightPressed.setMiddle_y(y - world.getOffset_y());
    }
}
