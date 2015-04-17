
package scentek;

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
    /**Gives ability to access errors*/
    private GLFWErrorCallback errorCallback;
    /**the fps limit of the game*/
    private final int fps_limit = 30;
    /**The label of the window*/
    private String gameName;
    /**The currently displayed window*/
    private Window window;
    /**the current working directory*/
    public static final String WORKING_DIR = System.getProperty("user.dir");
    /**
     * Creates a game with the label passed
     * @param name the label for the game window
     * @param windowed whether or not the game is windowed at start
     */
    public Game(String name, boolean windowed){
        gameName = name;
        init();
        //create window
        //TODO: implement a read settings to create window(maybe a constructor?)
        window = new Window(gameName, 600, 800, windowed);

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window.getHandle(), this);
        // Enable v-sync
        glfwSwapInterval(1);
        // Make the window visible
        glfwShowWindow(window.getHandle());

            
        
    }
    /**
     * Creates a windowed Game with the passed name
     * @param name label for game window
     */
    public Game(String name){
        this(name, true);
    }
    /**
     * Toggles fullscreen mode
     */
    public void toggleFullscreen(){// Bug occurs resulting any shape to lose it's color and change to white when this method is used
        window.toggleFullscreen();
        glfwSetKeyCallback(window.getHandle(), this);
    }
    /**
     * Initialize glfw
     */
    private void init(){
        // initialize GLFW
        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
 
        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( glfwInit() != GL_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");
        
 
        
    }
    /**
     * Ends the game by telling glfw that the window should close
     */
    public void endGame(){
        glfwSetWindowShouldClose(window.getHandle(), GL_TRUE);
    }
    /**
     * Runs the game and loops it
     */
    public void run(){
        System.out.println("Hello LWJGL " + Sys.getVersion() + "!");
        
        GLContext.createFromCurrent();
        initVars();
        glClearColor(0.0f,0.0f,0.0f,0.0f);
        double lastTime = glfwGetTime();
        while ( glfwWindowShouldClose(window.getHandle()) == GL_FALSE ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
 
           
            if(glfwGetTime()-lastTime >= (double)1/fps_limit){
                update(glfwGetTime()-lastTime);
                glfwSwapBuffers(window.getHandle()); // swap the color buffers
                lastTime = glfwGetTime();
            }
            glfwPollEvents();// Poll for window events. 
        }
        glfwTerminate();
        errorCallback.release();
    }
    
    
    @Override
    /**
     * The key listener method, calls @see interpretKeys
     */
    public void invoke(long winHandle, int key, int scancode, int action, int mods){
        interpretKeys(key, action);
    }
    /**
     * Interprets keys that are pressed
     * @param key the OpenGL KeyID of the pressed key
     * @param action the event ID
     */
    public abstract void interpretKeys(int key, int action);
    /**
     * updates the game state
     * @param elapsed the time elapsed since last frame
     */
    public abstract void  update(double elapsed);
    /**
     * Initializes variable that require an opengl context
     */
    public abstract void initVars();
}
