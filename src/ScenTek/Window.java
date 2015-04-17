
package scentek;

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
 
    /**Whether or not the window is windowed*/ 
    private boolean windowed;
    
    // Window settings
    /**The size of the window*/
    private int winWidth, winHeight;//TODO changed in constructor
    /**initial size of the window*/
    private final int initWidth, initHeight;
    /**The title of the window*/
    private String title = "Hello";
    /**The window's id in opengl*/
    private long window;
    
    /**
     * 
     * @param title title of the window
     * @param width initial width of the window
     * @param height initial height of the window
     */
    protected Window(String title, int width, int height){
        this.title = title;
        winWidth = width;
        winHeight = height;
        initWidth = winWidth;
        initHeight = winHeight;
        goWindowed(width, height);
        
    }
    /**
     * Creates a window
     * @param title title of the window
     * @param width starting width of the window
     * @param height starting height of the window
     * @param fullscreen whether or not the window starts fullscreen
     */
    protected Window(String title, int width, int height, boolean fullscreen){
        this.title = title;
        winWidth = height;
        winHeight = width;
        initWidth = winWidth;
        initHeight = winHeight;
        windowed = !fullscreen;
        toggleFullscreen();
    }
    /**
     * Toggles the window between fullscreen and windowed mode
     */
    protected void toggleFullscreen(){
        windowed = !windowed;
        if(!windowed){
            goFullscreen();
        }else{
            goWindowed(winWidth, winHeight);
            
        }
    }
    /**
     * Sets the window to fullscreen mode
     */
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
    /**
     * Changes the screen to windowed mode with the initial width and height
     */
    protected void goWindowed(){
        goWindowed(initWidth, initHeight);
    }
    /**
     * Changes the screen to windowed mode with the passed width and height
     * @param width
     * @param height 
     */
    protected void goWindowed(int width, int height){
        long oldWindow;
        
        oldWindow = glfwCreateWindow(width, height, title, NULL, NULL);
        try{
            swapWindows(oldWindow);
        }catch(Exception e){
            
        }
        windowed = true;
    }
    /**
     * 
     * @return the current OpenGL handle of the window
     */
    protected long getHandle(){
        return window;
    }
    
    /**
     * Changes over the window to a new window handle
     * @param newWindow the new handle
     */
    private void swapWindows(long newWindow){
        long oldWindow = window;
        window = newWindow;
        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
        glfwDestroyWindow(oldWindow);
        
    }
    /**
     * initializes the window
     */
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