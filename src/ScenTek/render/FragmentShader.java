
package ScenTek.render;

import java.io.IOException;

/**
 *
 * @author Andrew
 */
public class FragmentShader extends Shader {
    /**
     * Creates the Fragment shader by calling the super
     * @param filename directory of the source for the shader
     * @throws IOException @see Shader
     */
    public FragmentShader(String filename) throws IOException{
        super(filename, Shader.ShaderType.FRAGMENT);
    } 
    /**
     * Creates the Fragment shader by calling the super
     * @param src Source text for the shader
     */
    public FragmentShader(StringBuilder src){
        super(src, Shader.ShaderType.FRAGMENT.getType());
    }
}
