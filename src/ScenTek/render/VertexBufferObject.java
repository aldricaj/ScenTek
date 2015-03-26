
package ScenTek.render;

import java.nio.*;
import static org.lwjgl.opengl.GL15.*;

/**
 * wraps an opengl VBO
 * @author Andrew
 */
public class VertexBufferObject {
    private int paramSize;
    public final int vbo_id;
    /**
     * Creates a VBO
     * @param b an <b>UNFLIPPED</b> Buffer
     */
    public VertexBufferObject(FloatBuffer fb){
        fb.flip();
        paramSize = fb.position(); // should == length of buffer
        
        vbo_id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo_id);
        glBufferData(GL_ARRAY_BUFFER, fb, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
    
    public int size(){
        return paramSize;
    }
}
