package core;

import java.io.IOException;

import listeners.MouseMotionListenerImpl;
import objects.World;
import gui.DisplayMap;
import gui.Renderer;
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
    public World world() {
        return new World();
    }

    @Bean
    public MouseMotionListenerImpl mouseListenerImpl() {
        return new MouseMotionListenerImpl();
    }

    @Bean
    public SpriteBuffer spriteBuffer() throws IOException {
        return new SpriteBuffer();
    }
}
