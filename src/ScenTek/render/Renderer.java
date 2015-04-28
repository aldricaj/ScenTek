
package ScenTek.render;

import ScenTek.geom.Vertex;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * This interface will handle rendering
 * @author aaldrich
 */
public class Renderer {
    private int simpleVAO, simpleVBO;
    protected float[] points;
    protected byte[] indices;
    public enum RenderMode{
        SIMPLE, TEXTURED
    };
    private Renderer(){
        
    }
    private void initSimpleRender(){
        simpleVAO = glGenVertexArrays();
        simpleVBO = glGenBuffers();
        
    }
    
    private void initTexturedRender(){
        
    }
    
    /**
     * Render at the points and the indices passed
     * @param pts points to be rendered at
     * @param indices indices for the triangles to be used
     */
    public void render(Vertex[] vertices, RenderMode mode) throws IllegalArgumentException{
        
        switch(mode){
            case SIMPLE:
                if(vertices instanceof ColoredVertex[]){
                    
                }
                else throw new IllegalArgumentException("Wrong Vertex Type Passed");
                break;
            case TEXTURED:
                
                break;
        }
        
        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_BYTE, 0);
        
    }
    
    
}
