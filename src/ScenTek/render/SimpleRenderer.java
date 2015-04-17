
package ScenTek.render;

import java.awt.Color;
import java.io.IOException;
import java.nio.*;
import java.util.Arrays;
import java.util.logging.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import scentek.Game;
//TODO finish rendering starting at setting up index VBO
/**
 *
 * @author Andrew
 */
public class SimpleRenderer extends Renderer{
    /**The color that is currently being used to render. The default is Red*/
    private float[] color = {1.0f, 0.0f, 0.0f, 1.0f};
    /**VBO id for a feature*/
    private int colorVBO, vertVBO, indexVBO;
    /**The shader used*/
    private static SimpleShaderProgram shader;
    private static SimpleRenderer instance;
    /**
     * Returns an instance of SimpleRenderer
     * @return 
     */
    public static SimpleRenderer getInstance(){
        return ((instance == null) ? (instance = new SimpleRenderer()) : (instance));
    }
    /**
     * Creates the renderer, should only be called by @see getInstance()
     */
    private SimpleRenderer() {
        color = new float[4];
        if(shader == null) shader = new SimpleShaderProgram();
    }
    /**
     * Initialized the vao, vbos, and the shaders
     */
    private void init(){
        //create & bind VAO for use
        vao = glGenVertexArrays();
        // initialize and create VBOs for both color and vertices
        vertVBO = glGenBuffers();
        colorVBO = glGenBuffers();
        indexVBO = glGenBuffers();
        // setup shaders
        glBindAttribLocation(shader.program_id, 0, "in_Position");
        glBindAttribLocation(shader.program_id, 1, "in_Color");
    }
    /**
     * Sets the render color to the passed color. converted into a 4 size array
     * @param c 
     */
    public void setColor(Color c){
        color = c.getColorComponents(color);
    }
    /**
     * Sets the color to the passed color
     * @param r red component
     * @param g green component
     * @param b blue component
     * @param a alpha component
     */
    public void setColor(float r, float g, float b, float a){
        color[0] = r;
        color[1] = g;
        color[2] = b;
        color[3] = a;
    }

    @Override
    protected void loadSettings() {
        // ensure that the VAO is created
        if(vao < 0) init();
        
        // 1) UPDATE
            //updates the buffers to be loaded into the VAO
                // VERTICES
                FloatBuffer vertBuffer = BufferUtils.createFloatBuffer(super.points.length);
                vertBuffer.put(super.points);
                vertBuffer.flip();


                //COLORS
                    // copy the color so that each vertex has a color
                    float[] colors = new float[super.points.length * 4/3];
                    for(int i = 0; i < colors.length; i++){
                        colors[i] = color[i%4];// makes every 4th value equal, copying the color
                    }
                FloatBuffer colorBuffer = BufferUtils.createFloatBuffer(colors.length);
                colorBuffer.put(colors);
                colorBuffer.flip();

                // INDICES
                byte[] indices = super.indices;
                ByteBuffer indexBuffer = BufferUtils.createByteBuffer(indices.length);
                indexBuffer.put(indices);
                indexBuffer.flip();


            //Load info into VAO
                glBindVertexArray(vao);
                // VERTICES
                glBindBuffer(GL_ARRAY_BUFFER, vertVBO);
                glBufferData(GL_ARRAY_BUFFER, vertBuffer, GL_STATIC_DRAW);
                glVertexAttribPointer(0,4,GL_FLOAT, false, 0,0);
                glBindBuffer(GL_ARRAY_BUFFER,0);
                //COLOR
                glBindBuffer(GL_ARRAY_BUFFER, colorVBO);
                glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW);
                glVertexAttribPointer(1, 4, GL_FLOAT, false, 0,0);
                glBindBuffer(GL_ARRAY_BUFFER,0);
                glBindVertexArray(0);

            // update index VBO
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexVBO);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

            
        // 2) Get Ready to render
            glUseProgram(shader.program_id);// Load shader program
            
            glBindVertexArray(vao);
            // ready the vao vars for 
                glEnableVertexAttribArray(0);
                glEnableVertexAttribArray(1);
            // bind the index VBO
                glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexVBO);
        
        
    }

    @Override
    protected void unloadSettings() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
        glUseProgram(0);
    }
    /**
     * Represents the shader program
     */
    private class SimpleShaderProgram {
        private Shader vs, fs;
        final int program_id;
        SimpleShaderProgram(){
            try {
                vs = new VertexShader(Game.WORKING_DIR + "\\Shaders\\Simple\\simple_shader.vtx");
                fs = new FragmentShader(Game.WORKING_DIR + "\\Shaders\\Simple\\simple_shader.frag");
            } catch (IOException ex) {
                Logger.getLogger(SimpleRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            program_id = glCreateProgram();
            glAttachShader(program_id, vs.shader_id);
            glAttachShader(program_id, fs.shader_id);
            
            glBindAttribLocation(program_id, 0, "in_Position");
            glBindAttribLocation(program_id, 1, "in_Color");
            
            glLinkProgram(program_id);
            glValidateProgram(program_id);
            
            int errorCheckValue = GL11.glGetError();
            if (errorCheckValue != GL11.GL_NO_ERROR) {
                System.out.println("ERROR - Could not create the shaders");
                System.exit(-1);
            }
        }
    }
}

