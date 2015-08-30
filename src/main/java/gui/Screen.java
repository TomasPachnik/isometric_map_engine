package gui;

import init.GlobalInit;
import init.GlobalListeners;

import java.awt.Color;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import annotations.Autowired;

public class Screen {
    private JFrame frame;

    @Autowired
    private Renderer renderer;
    @Autowired
    private MouseMotionListener mouseListenerImpl;
    @Autowired
    private GlobalListeners globalListeners;
    @Autowired
    private GlobalInit globalInit;

    public void draw() {
        globalInit.init();
        frame = new JFrame("map engine");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(806, 629);
        frame.setLayout(null);
        JPanel panel = new JPanel();
        panel.setBackground(Color.YELLOW);
        frame.add(panel);
        panel.setLayout(null);
        panel.setBounds(0, 0, 800, 600);
        panel.setVisible(true);
        Stats map1 = new Stats();
        map1.setBounds(0, 0, 800, 30);
        map1.setVisible(true);
        panel.add(map1);
        renderer.setBounds(0, 30, 800, 570);
        renderer.setVisible(true);
        renderer.addMouseMotionListener(mouseListenerImpl);
        panel.add(renderer);
        globalListeners.initListeners();
        frame.setVisible(true);
    }
}