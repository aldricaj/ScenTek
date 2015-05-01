
package ScenTek.render;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import org.lwjgl.opengl.GL20;
import static org.lwjgl.opengl.GL30.*;

/**
 * This interface will handle rendering
 * @author aaldrich
 */
public class Renderer {
    private int simpleVAO, simpleVBO;
    FloatBuffer simpleBuffer;
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
        simpleBuffer = BufferUtils.createFloatBuffer(2);
        
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
                    float[][] vertData = processVerts(vertices, mode);
                    float[] coords = vertData[0];
                    float[] colors = vertData[1];
                    simpleBuffer.put(coords);
                    simpleBuffer.put(colors);
                    
                    GL20.glVertexAttribPointer(0, Vertex.num_elements, GL11.GL_FLOAT, false, Vertex.element_size);
                }
                
                else throw new IllegalArgumentException("Wrong Vertex Type Passed");
                break;
            case TEXTURED:
                
                break;
        }
        
        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_BYTE, 0);
        
    }
    
    private float[][] processVerts(Vertex[] verts, RenderMode mode){
        float[][] vertData = null;
        switch(mode){
            case SIMPLE:
                vertData = new float[2][];
                vertData[0] = new float[Vertex.num_elements*verts.length]; //coords
                vertData[1] = new float[4*verts.length]; // color
                for(int i = 0; i < vertData[0].length;i+=Vertex.num_elements){
                    float[] v = verts[i/4].getCoords();
                    vertData[0][i] = v[0];
                    vertData[0][i+1] = v[1];
                    vertData[0][i+2] = v[2];
                    vertData[0][i+3] = v[3];
                }
                for(int i = 0; i < vertData[1].length; i+= 4){
                    float[] v = ((ColoredVertex)verts[i/4]).getRGBA();
                    vertData[1][i] = v[0];
                    vertData[1][i+1] = v[1];
                    vertData[1][i+2] = v[2];
                    vertData[1][i+3] = v[3];
                }
                break;
        }
        return vertData;
    }
    
    
}
