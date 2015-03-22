
package ScenTek.render;

import java.io.IOException;

/**
 * Represents a vertex shader(really just wraps the Shader class for readability).
 * @author Andrew
 */
public class VertexShader extends Shader{
    /**
     * Creates the vertex shader by calling the super
     * @param filename directory of the source for the shader
     * @throws IOException @see Shader
     */
    public VertexShader(String filename) throws IOException{
        super(filename, ShaderType.VERTEX);
    } 
    /**
     * Creates the vertex shader by calling the super
     * @param src the source text for the shader
     */
    public VertexShader(StringBuilder src){
        super(src, ShaderType.VERTEX.getType());
    }
}
