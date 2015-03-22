
package ScenTek.render;

import java.io.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * Wraps a shader. Should either be a @see VertexShader or a @see FragmentShader
 * @author Andrew
 */
public class Shader {
    public static enum ShaderType{
        VERTEX(GL_VERTEX_SHADER), 
        FRAGMENT(GL_FRAGMENT_SHADER);
        
        private final int type;
        ShaderType(int n){
            type =n;
        }
        int getType(){
            return type;
        }
    }
    
    public final int type;
    public final int shader_id;
    
    /**
     * Calls the (String, int) constructor
     * @param fileName directory of the shader text
     * @param t type of shader 
     * @throws IOException Thrown if @see StringBuilder throws a FileNotFound or an IOException
     */
    public Shader(String fileName, ShaderType t) throws IOException{
        this(fileName, t.getType());
    }
    /**
     * Creates the Shader and initializes it in OpenGL. It still needs to be bound after the constructor call
     * @param fileName directory of the shader
     * @param t type of shader
     * @throws IOException Thrown if @see StringBuilder throws a FileNotFound or an IOException
     */
    public Shader(String fileName, int t) throws IOException{
        type = t;
        
        // Build the GLSL src
        StringBuilder shaderSource = new StringBuilder();
        
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            shaderSource.append(line).append("\n");
        }
        reader.close();
        
        shader_id = glCreateShader(type);
        // assigns source for and compiles the shader 
        glShaderSource(shader_id, shaderSource);
        glCompileShader(shader_id);
    }
}

