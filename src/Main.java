import ScenTek.render.*;
import java.awt.Color;
import java.util.Random;
import scentek.Game;
import static org.lwjgl.glfw.GLFW.*;

public class Main extends Game{
    public static void main(String[] args){
        new Main("Hello!");
    }
    public Main(String name){
        super(name);
        
    }
    public Main(String name, boolean fullscreen) {
        super(name, fullscreen);
    }
    
    @Override
    public void interpretKeys(int key, int action){
        if(key == GLFW_KEY_F11){
            toggleFullscreen();
        }
        if(key == GLFW_KEY_ESCAPE){
            endGame();
        }
    }
    SimpleRenderer r;
    @Override
    public void update(){
        if (r == null)
            r = new SimpleRenderer();  
        
        
        float[] vertices = {
                -0.5f, 0.5f, 0.0f, 1f,
                -0.5f, -0.5f, 0f, 1f,
                0.5f, -0.5f, 0f, 1f,
                0.5f, 0.5f, 0f, 1f
        };
        byte[] indices =  {
                0, 1, 2,
                2, 3, 0
        };
        r.render(vertices, indices);
       
    }
}
