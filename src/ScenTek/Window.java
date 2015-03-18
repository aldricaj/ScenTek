
package ScenTek;

import org.lwjgl.Sys;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
 
import java.nio.ByteBuffer;
 
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;
/**
* The Window class will handle the drawing of the display for the game
* @author aaldrich
*/
public class Window {
 
    
    private boolean windowed;
    
    // Window settings
    private int winWidth, winHeight;//TODO changed in constructor
    private final int initWidth, initHeight;
    private String title = "Hello";
    
    
    // The window handle
    private long window;
    /**
     * 
     * @param title title of the window
     * @param width initial width of the window
     * @param height initial height of the window
     * @param keyListener input manager
     */
    protected Window(String title, int width, int height){
        this.title = title;
        winWidth = width;
        winHeight = height;
        initWidth = winWidth;
        initHeight = winHeight;
        goWindowed(width, height);
        
    }
    
    protected Window(String title, int width, int height, boolean fullscreen){
        this.title = title;
        winWidth = height;
        winHeight = width;
        initWidth = winWidth;
        initHeight = winHeight;
        windowed = !fullscreen;
        toggleFullscreen();
    }
    
    protected void toggleFullscreen(){
        windowed = !windowed;
        if(!windowed){
            goFullscreen();
        }else{
            goWindowed(winWidth, winHeight);
            
        }
    }
    
    protected void goFullscreen(){
        
        ByteBuffer vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        long oldWindow;
        oldWindow = glfwCreateWindow(GLFWvidmode.width(vidMode), GLFWvidmode.height(vidMode), title, glfwGetPrimaryMonitor(), window);
        
        
        try{
            swapWindows(oldWindow);
        }catch(Exception e){
            
        }
        windowed = false;
    }
    protected void goWindowed(){
        goWindowed(initWidth, initHeight);
    }
    protected void goWindowed(int width, int height){
        long oldWindow;
        
        oldWindow = glfwCreateWindow(width, height, title, NULL, NULL);
        try{
            swapWindows(oldWindow);
        }catch(Exception e){
            
        }
        windowed = true;
    }
    
    protected long getHandle(){
        return window;
    }
    
    
    private void swapWindows(long newWindow){
        long oldWindow = window;
        window = newWindow;
        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
        glfwDestroyWindow(oldWindow);
        
    }
 
    private void init() {
 
        // Create the window
        window = glfwCreateWindow(winWidth, winHeight, title, NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
 
        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable
 
        // Get the resolution of the primary monitor
        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        
    }
    
}