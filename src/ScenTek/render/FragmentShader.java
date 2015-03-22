
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
     * @throws IOException 
     */
    public FragmentShader(String filename) throws IOException{
        super(filename, Shader.ShaderType.FRAGMENT);
    } 
}
