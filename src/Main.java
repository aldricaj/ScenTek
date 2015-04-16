import ScenTek.RenderablePoly;
import ScenTek.geom.Point;
import ScenTek.geom.Poly;
import ScenTek.render.*;
import java.awt.Color;
import java.util.Random;
import scentek.Game;
import static org.lwjgl.glfw.GLFW.*;

public class Main extends Game{
    private RenderablePoly po;
    public static void main(String[] args){
        new Main("Hello!", false);
    }
    public Main(String name){
        super(name);
        
    }
    public Main(String name, boolean fullscreen) {
        super(name, fullscreen);
    }
    
    @Override
    public void interpretKeys(int key, int action){
        if(key == GLFW_KEY_F11 && action == GLFW_RELEASE){
            toggleFullscreen();
        }
        if(key == GLFW_KEY_SPACE && action == GLFW_RELEASE){
            po.toggleVisibility();
            System.out.println(""+ po.isVisible());
        }
        if(key == GLFW_KEY_ESCAPE){
            endGame();
        }
    }
    SimpleRenderer r;
    @Override
    public void update(double elapsed){
        if (r == null)
            r = SimpleRenderer.getInstance();  
        
        
        Point[] p = new Point[]{
            new Point(0.0f, 0.0f),
            new Point(0.5f, 0.0f),
            new Point(0.5f, 0.5f),
            new Point(0.0f, 0.5f)
        };
        po = new RenderablePoly(Color.CYAN, p);
        po.render();
       
    }
}
