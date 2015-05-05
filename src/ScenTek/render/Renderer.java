
package ScenTek.render;

import java.io.*;
import java.nio.*;
import java.util.logging.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL15;
import static org.lwjgl.opengl.GL15.*;
import org.lwjgl.opengl.GL20;
import static org.lwjgl.opengl.GL20.*;
import org.lwjgl.opengl.GL30;
import static org.lwjgl.opengl.GL30.*;
import ScenTek.Game;

/**
 * This interface will handle rendering
 * @author aaldrich
 */
public class Renderer {
    private int simpleVAO, simpleVBO, simpleShaderID, indexVBO;
    private FloatBuffer simpleBuffer;
    private ByteBuffer indexBuffer;
    private static Renderer instance;
    public enum RenderMode{
        SIMPLE, TEXTURED
    };
    public static Renderer getInstance(){
        return (instance == null) ? (instance = new Renderer()) : instance;
    }
    private Renderer(){
        indexBuffer = BufferUtils.createByteBuffer(2);
        indexVBO = glGenBuffers();
        initSimpleRender();
        initTexturedRender();
    }
    private void initSimpleRender(){
        simpleVAO = glGenVertexArrays();
        simpleVBO = glGenBuffers();
        simpleBuffer = BufferUtils.createFloatBuffer(2);
        // set up shaders
        VertexShader vs = null;
        FragmentShader fs = null;
        try {
            vs = new VertexShader(Game.WORKING_DIR + "\\Shaders\\Simple\\simple_shader.vtx");
            fs = new FragmentShader(Game.WORKING_DIR + "\\Shaders\\Simple\\simple_shader.frag");
        } catch (IOException ex) {
            Logger.getLogger(Renderer.class.getName()).log(Level.SEVERE, null, ex);
        }

        simpleShaderID = glCreateProgram();
        glAttachShader(simpleShaderID, vs.shader_id);
        glAttachShader(simpleShaderID, fs.shader_id);

        glBindAttribLocation(simpleShaderID, 0, "in_Position");
        glBindAttribLocation(simpleShaderID, 1, "in_Color");

        glLinkProgram(simpleShaderID);
        glValidateProgram(simpleShaderID);

        int errorCheckValue = GL11.glGetError();
        if (errorCheckValue != GL11.GL_NO_ERROR) {
            System.out.println("ERROR - Could not create the shaders");
            System.exit(-1);
        }
    }
    
    private void initTexturedRender(){
        
    }
    
    
    /**
     * Renders a shape given passed Vertices
      
     * Currently supported render types should be represented by {@link RenderMode} types
     
     * @param vertices an array of a {@link Vertex} subclass
     * @param vertOrder the order that OpenGL should render the Vertices
     * @param mode the render mode to use
     * @throws IllegalArgumentException 
     */
    public void render(Vertex[] vertices, byte[] vertOrder, RenderMode mode) throws IllegalArgumentException{
        // load data to render
        switch(mode){
            case SIMPLE:
                if(vertices instanceof ColoredVertex[]){
                    
                    for(ColoredVertex v : (ColoredVertex[])vertices){
                        simpleBuffer = BufferUtils.createFloatBuffer((ColoredVertex.num_elements + Vertex.num_elements) * vertices.length);
                        simpleBuffer.put(v.getCoords());
                        simpleBuffer.put(v.getRGBA());
                    }
                    simpleBuffer.flip();
                    
                    glBindVertexArray(simpleVAO);
                    GL20.glVertexAttribPointer(0, Vertex.num_elements, GL11.GL_FLOAT, false, 
                            Vertex.element_size, 0);
                    GL20.glVertexAttribPointer(1, ColoredVertex.num_elements, GL_FLOAT, false, 
                            Vertex.element_size, Vertex.getSize());
                    GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
                    
                    
                    glEnableVertexAttribArray(0);
                    glEnableVertexAttribArray(1);
                    glUseProgram(simpleShaderID);
                }
                
                else throw new IllegalArgumentException("Wrong Vertex Type Passed");
                break;
            case TEXTURED:
                
                break;
        }
        
        // load index data
        indexBuffer = BufferUtils.createByteBuffer(vertOrder.length);
        indexBuffer.put(vertOrder);
        indexBuffer.flip();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexVBO);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
        // render
        System.out.println("render");
        glDrawElements(GL_TRIANGLES, vertOrder.length, GL_UNSIGNED_BYTE, 0);
        indexBuffer.clear();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        
        // unload data
        switch(mode){
            case SIMPLE:
                glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
                glDisableVertexAttribArray(0);
                glDisableVertexAttribArray(1);
                glBindVertexArray(0);
                glUseProgram(0);
                break;
            case TEXTURED:
                
                break;
        }
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
