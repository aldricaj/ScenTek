import ScenTek.*;
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
}
