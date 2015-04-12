import ScenTek.RenderablePoly;
import ScenTek.geom.Point;
import ScenTek.geom.Poly;
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
            r = SimpleRenderer.getInstance();  
        r.setColor(Color.red);
        r.render(new float[]{0.0f,0.0f,-0.5f, 0.0f, -0.5f, -0.5f, 0.0f, 0.5f}, new byte[]{0,1,2,2,3,0});
        Point[] p = new Point[]{
            new Point(0.0f, 0.0f),
            new Point(0.5f, 0.0f),
            new Point(0.5f, 0.5f),
            new Point(0.0f, 0.5f)
        };
        RenderablePoly po = new RenderablePoly(Color.RED, p);
        po.render();
        
        
    }
}
