
package ScenTek.render;

import java.awt.Color;
import java.io.IOException;
import java.nio.*;
import java.util.logging.*;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
//TODO finish rendering starting at setting up index VBO
/**
 *
 * @author Andrew
 */
public class SimpleRenderer extends Renderer{
    
    private float[] color = {1.0f, 0.0f, 0.0f, 1.0f};
    private boolean colorHasChanged;
    private int colorVBO, vertVBO, indexVBO;
    private static ShaderProgram shader;
    public SimpleRenderer() {
        color = new float[4];
        if(shader != null)
            try {
                shader = new ShaderProgram(new VertexShader("/Shaders/Simple/simple_shader.vtx"), new FragmentShader("/Shaders/Simple/simple_shader.frag"));
            } catch (IOException ex) {
                shader = null;
                Logger.getLogger(SimpleRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    private void init(){
        
        
        //create & bind VAO for use
        vao = glGenVertexArrays();
        glBindVertexArray(vao);
        
        // prepare the color to be loaded
        FloatBuffer vertBuffer = BufferUtils.createFloatBuffer(super.points.length);
        vertBuffer.put(super.points);
        vertBuffer.flip();
        
        
        // prepare the color to be loaded into the VAO
        FloatBuffer colorBuffer = BufferUtils.createFloatBuffer(color.length);
        colorBuffer.put(color);
        colorBuffer.flip();
        
        // Prepare the indices to be loaded into the VAO
        ByteBuffer indexBuffer = BufferUtils.createByteBuffer(super.indices.length);
        indexBuffer.put(super.indices);
        indexBuffer.flip();
        
        // Load stuff into the VAO for drawing
        // Vertices
        vertVBO = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertVBO);
        glBufferData(GL_ARRAY_BUFFER, vertBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0,4,GL_FLOAT, false, 0,0);
        glBindBuffer(GL_ARRAY_BUFFER,0);
        
        //Color
        colorVBO = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, colorVBO);
        glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(1, 4, GL_FLOAT, false, 0,0);
        glBindBuffer(GL_ARRAY_BUFFER,0);
        
        //deselect vertex array
        glBindVertexArray(0);
        
        
        
        // setup shaders
        glBindAttribLocation(shader.program_id, 0, "in_Position");
        glBindAttribLocation(shader.program_id, 1, "in_Color");
    }
    public void setColor(Color c){
        color = c.getColorComponents(color);
        colorHasChanged = true;
    }
    
    public void setColor(float r, float g, float b, float a){
        color[0] = r;
        color[1] = g;
        color[2] = b;
        color[3] = a;
        colorHasChanged = true;
    }

    @Override
    protected void loadSettings() {
        // create vao
        if(vao < 0){ 
            init();
            glBindVertexArray(vao);
        }
        //updates the buffers
        else{
            glBindVertexArray(vao);
            FloatBuffer vertBuffer = BufferUtils.createFloatBuffer(super.points.length);
            vertBuffer.put(super.points);
            vertBuffer.flip();
        
            vertVBO = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vertVBO);
            glBufferData(GL_ARRAY_BUFFER, vertBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(0,4,GL_FLOAT, false, 0,0);
            glBindBuffer(GL_ARRAY_BUFFER,0);
            
            // reassing color values if they've changed
            if(colorHasChanged){ 
                FloatBuffer colorBuffer = BufferUtils.createFloatBuffer(color.length);
                colorBuffer.put(color);
                colorBuffer.flip();
                glBindBuffer(GL_ARRAY_BUFFER, colorVBO);
                glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW);
                glVertexAttribPointer(1, 4, GL_FLOAT, false, 0,0);
                glBindBuffer(GL_ARRAY_BUFFER,0);
            }
        }
        // ready the vao vars for use
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        
    }

    @Override
    protected void unloadSettings() {
        
    }
    
}
