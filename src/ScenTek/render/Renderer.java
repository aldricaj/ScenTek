
package ScenTek.render;

/**
 * This interface will handle rendering
 * @author aaldrich
 */
public class Renderer {
    
    private Renderer instance;
    private Renderer(){
        
    }
    
    public void render(float[][] pts, int[] indices){
        
    }
    /**
     * Get an instance of the renderer
     * @return current instance
     */
    public Renderer getInstance(){
        return (instance == null) ? (instance = new Renderer()) : (instance); // ensure that instance has been created, if not, make one
    }
    
    
}
