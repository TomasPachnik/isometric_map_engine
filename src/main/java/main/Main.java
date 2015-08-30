package main;

import interfaces.Core;
import core.CoreImpl;
import core.ObjectConfig;
import exceptions.BeanNotFoundException;
import gui.Renderer;
import gui.Screen;

public class Main {

    public static void main(String[] args) throws BeanNotFoundException {
        Core core = new CoreImpl(ObjectConfig.class);
        Screen screen = (Screen) core.getByName("screen");
        screen.draw();
        // Screen mainScreen = new Screen();
        // mainScreen.draw();
        /*
         * Point point = Utils.getIsoXY(2, 0); System.out.println(point); Float colRow = Utils.getColRow(8, 5); System.out.println(colRow);
         */
    }

}
