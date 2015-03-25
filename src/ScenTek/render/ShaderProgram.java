
package ScenTek.render;

import static org.lwjgl.opengl.GL20.*;

/**
 *
 * @author aldrich_a
 */
public class ShaderProgram {
    public final int program_id;
    public ShaderProgram(VertexShader vs, FragmentShader fs){
        program_id = glCreateProgram();
        glAttachShader(program_id, vs.shader_id);
        glAttachShader(program_id, fs.shader_id);
    }
}
