package core;

import init.GlobalInit;
import init.GlobalListeners;

import java.io.IOException;

import listeners.MouseDataObject;
import listeners.RendererMouseListener;
import listeners.MousePositionRightPressed;
import listeners.RightPanelMouseListener;
import logic.RendererLogic;
import objects.World;
import global.GlobalValues;
import gui.DisplayMap;
import gui.Renderer;
import gui.RightPanel;
import gui.Screen;
import annotations.Bean;
import annotations.Config;

@Config
public class ObjectConfig {

    @Bean
    public Screen screen() {
        return new Screen();
    }

    @Bean
    public DisplayMap displayMap() {
        return new DisplayMap();
    }

    @Bean
    public Renderer renderer() {
        return new Renderer();
    }
    
    @Bean
    public RightPanel rightPanel() {
        return new RightPanel();
    }

    @Bean
    public World world() {
        return new World();
    }

    @Bean
    public RendererMouseListener rendererMouseListener() {
        return new RendererMouseListener();
    }

    @Bean
    public SpriteBuffer spriteBuffer() throws IOException {
        return new SpriteBuffer();
    }

    @Bean
    public MousePositionRightPressed mousePositionRightPressed() {
        return new MousePositionRightPressed();
    }

    @Bean
    public GlobalListeners globalListeners() {
        return new GlobalListeners();
    }

    @Bean
    public GlobalInit globalInit() {
        return new GlobalInit();
    }
    
    @Bean
    public GlobalValues globalValues() {
        return new GlobalValues();
    }
    
    @Bean
    public MouseDataObject mouseDataObject() {
        return new MouseDataObject();
    }
    
    @Bean
    public RightPanelMouseListener rightPanelMouseListener(){
        return new RightPanelMouseListener();
    }
    
    @Bean
    public RendererLogic rendererLogic(){
        return new RendererLogic();
    }
    
}
