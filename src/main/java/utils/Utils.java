package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import static utils.Constants.TILE_WIDTH;
import static utils.Constants.TILE_HEIGHT;

public class Utils {

    public static JPanel createJPanel(int position_x, int position_y, int size_x, int size_y, Color color) {
        JPanel panel = new JPanel();
     //   panel.setLocation(position_x, position_y);
       // panel.setSize(size_x, size_y);
        panel.setBackground(color);
        return panel;
    };

    public static Point getIsoXY(float col, float row) {
        int isoHalfWidth = TILE_WIDTH / 2;
        int isoHalfHeight = TILE_HEIGHT / 2;
        int isoX = (int) ((col - row) * isoHalfWidth);
        int isoY = (int) ((col + row) * isoHalfHeight);
        return new Point(isoX, isoY);
    }

    public static Point2D.Float getColRow(int isoX, int isoY) {
        float col = (((float) isoX) / ((float) TILE_WIDTH)) + (((float) isoY) / ((float) TILE_HEIGHT));
        float row = (((float) isoY) / ((float) TILE_HEIGHT)) - ((float) isoX) / ((float) TILE_WIDTH);
        return new Point2D.Float(col, row);
    }

}
