
package ScenTek.render;

import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Wraps an opengl shader program
 * @author Andrew
 */
public class ShaderProgram {
    
    public final int program_id;
    public ShaderProgram(VertexShader vs, FragmentShader fs){
        program_id = glCreateProgram();
        glAttachShader(program_id, vs.shader_id);
        glAttachShader(program_id, fs.shader_id);
        
        // link and validate program
        glLinkProgram(program_id);
        glValidateProgram(program_id);
        int errorCheckValue = GL11.glGetError();
        if (errorCheckValue != GL11.GL_NO_ERROR) {
            System.out.println("ERROR - Could not create the shaders");
            System.exit(-1);
        }
    }
}
