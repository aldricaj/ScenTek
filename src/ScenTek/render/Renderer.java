
package ScenTek.render;

/**
 * This interface will handle rendering
 * @author aaldrich
 */
public class Renderer {
    private Renderer instance;
    private Renderer(){
        
    }
    
    /**
     * Renders the passed points using Triangles made from the passed indices
     * @param pts the points to be rendered. In order in clockwise order
     * @param indices indices in the pts array that creates triangles
     */
    public void render(float[][] pts, int[] indices, ShaderProgram shader){
        
    }
    
    public Renderer getInstance(){
        return (instance == null) ? instance : (instance = new Renderer());
    }
    
}
