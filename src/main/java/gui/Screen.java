package gui;

import init.GlobalInit;
import init.GlobalListeners;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import listeners.RendererMouseListener;
import listeners.RightPanelMouseListener;
import static utils.Constants.RENDERER_HEIGHT;
import static utils.Constants.RENDERER_WIDTH;
import annotations.Autowired;

public class Screen {
    private JFrame frame;

    @Autowired
    private Renderer renderer;
    @Autowired
    private RendererMouseListener rendererMouseListener;
    @Autowired
    private GlobalListeners globalListeners;
    @Autowired
    private GlobalInit globalInit;
    @Autowired
    private RightPanel rightPanel;
    @Autowired
    private RightPanelMouseListener rightPanelMouseListener;

    public void draw() {
        System.out.println(globalInit);
        globalInit.init();
        frame = new JFrame("map engine");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(806, 629);
        frame.setLayout(null);
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        panel.setLayout(null);
        panel.setBounds(0, 0, RENDERER_HEIGHT, 600);
        panel.setVisible(true);
        Stats stats = new Stats();
        stats.setBounds(0, 0, RENDERER_HEIGHT, 30);
        stats.setVisible(true);
        panel.add(stats);
        rightPanel.setBounds(RENDERER_HEIGHT - 150, 30, 150, RENDERER_WIDTH);
        rightPanel.setVisible(true);
        panel.add(rightPanel);
        renderer.setBounds(0, 30, RENDERER_HEIGHT, RENDERER_WIDTH);
        renderer.setVisible(true);
        renderer.addMouseMotionListener(rendererMouseListener);
        renderer.addMouseListener(rendererMouseListener);
        rightPanel.addMouseMotionListener(rightPanelMouseListener);
        rightPanel.addMouseListener(rightPanelMouseListener);
        panel.add(renderer);
        globalListeners.initListeners();
        frame.setVisible(true);
        renderer.init();
        rightPanel.init();
    }
}
