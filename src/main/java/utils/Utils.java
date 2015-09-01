package utils;

import java.awt.Point;
import java.awt.geom.Point2D;

import static utils.Constants.TILE_WIDTH;
import static utils.Constants.TILE_HEIGHT;

public class Utils {

    public static Point getIsoXY(float col, float row) {
        int isoHalfWidth = TILE_WIDTH / 2;
        int isoHalfHeight = TILE_HEIGHT / 2;
        int isoX = (int) ((col - row) * isoHalfWidth);
        int isoY = (int) ((col + row) * isoHalfHeight);
        return new Point(isoX, isoY);
    }

    public static Point getMinimapIsoXY(float col, float row) {
        int isoX = (int) ((col - row));
        int isoY = (int) ((col + row));
        return new Point(isoX, isoY);
    }

    public static Point2D.Float getColRow(int isoX, int isoY) {
        float col = (((float) isoX) / ((float) TILE_WIDTH)) + (((float) isoY) / ((float) TILE_HEIGHT));
        float row = (((float) isoY) / ((float) TILE_HEIGHT)) - ((float) isoX) / ((float) TILE_WIDTH);
        return new Point2D.Float(col, row);
    }

}
