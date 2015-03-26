
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
    private StringBuilder src;
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
        
        
        // Build the GLSL src
        StringBuilder shaderSource = new StringBuilder();
        
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            shaderSource.append(line).append("\n");
        }
        reader.close();
        type = t;
        shader_id = glCreateShader(type);
        init(shaderSource);
    }
    /**
     * Creates a shader from the passed source
     * @param src the Shader source material
     * @param t type of shader
     */
    public Shader(StringBuilder src, int t){
        type = t;
        shader_id = glCreateShader(type);
        init(src);
        
    }
    /**
     * Returns the source text for the shader
     * @return StringBuilder wrapping the source text
     */
    public StringBuilder getSource(){
        return src;
    }
    
    /**
     * Initializes the shader because Java is stupid and won't allow code before the constructor, which prevents having a file reader constructor
     * @param src the source text(also the source of the need for this method)
     */
    private void init(StringBuilder src){
        this.src = src;
        // assigns source for and compiles the shader 
        glShaderSource(shader_id, src);
        glCompileShader(shader_id);
    }
}

