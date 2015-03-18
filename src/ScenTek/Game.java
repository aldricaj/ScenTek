
package ScenTek;

import org.lwjgl.Sys;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GLContext;


/**
 * Will manage GameObjects, I/0, and the Window. Also contains the game loop
 * @author aaldrich
 */
public abstract class Game extends GLFWKeyCallback{
    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback   keyCallback;
    
    private String gameName;
    private Window window;
    
    public Game(String name, boolean windowed){
        gameName = name;
        try{
            init();
            //create window
            //TODO: implement a read settings to create window(maybe a constructor?)
            window = new Window(gameName, 800, 600, true);
                    
            // Setup a key callback. It will be called every time a key is pressed, repeated or released.
            glfwSetKeyCallback(window.getHandle(), this);
            // Enable v-sync
            glfwSwapInterval(1);
            // Make the window visible
            glfwShowWindow(window.getHandle());
            
            run();
        }finally{
            glfwTerminate();
            errorCallback.release();
        }
            
        
    }
    public Game(String name){
        this(name, true);
    }
    
    public void toggleFullscreen(){
        window.toggleFullscreen();
        glfwSetKeyCallback(window.getHandle(), this);
    }
    
    private void init(){
        // initialize GLFW
        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
 
        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( glfwInit() != GL_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");
        
 
        
    }
    public void endGame(){
        glfwSetWindowShouldClose(window.getHandle(), GL_TRUE);
    }
    private void run(){
        System.out.println("Hello LWJGL " + Sys.getVersion() + "!");
        
        GLContext.createFromCurrent();
        
        glClearColor(0.0f,0.0f,0.0f,0.0f);
        
        while ( glfwWindowShouldClose(window.getHandle()) == GL_FALSE ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
 
            glfwSwapBuffers(window.getHandle()); // swap the color buffers
 
            
            glfwPollEvents();// Poll for window events. 
        }
    }
    
    
    @Override
    public void invoke(long winHandle, int key, int scancode, int action, int mods){
        interpretKeys(key, action);
    }
    
    public abstract void interpretKeys(int key, int action);
}
