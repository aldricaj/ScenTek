
package ScenTek.render;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Wraps an opengl shader program
 * @author Andrew
 */
public class ShaderProgram {
    
    public final int program_id;
    private int nVar;
    public ShaderProgram(VertexShader vs, FragmentShader fs){
        program_id = glCreateProgram();
        glAttachShader(program_id, vs.shader_id);
        glAttachShader(program_id, fs.shader_id);
        
        // find the in-var locations and bind them in mem
        int index = 0, lastIndex = 0;
        nVar = 0;
        StringBuilder vsSrc = vs.getSource();
        final int size_of_search_param = 3;
        while(index >= 0){
            index = vsSrc.indexOf("in ", lastIndex);
            if(index > 0){
                int endVarLoc = index + size_of_search_param;
                String varName = vsSrc.substring(endVarLoc, vsSrc.indexOf(" ", endVarLoc));
                glBindAttribLocation(program_id, nVar, varName);
                nVar++;
                lastIndex = endVarLoc;
            }
        }
        nVar++;// sets nVar so that there is not an off by one error
        // link and validate program
        glLinkProgram(program_id);
        glValidateProgram(program_id);
    }
    /**
     * Prepares the passed Vertex Array Object to be used by opengl
     * @param vaoId the VAO to be prepared
     */
    public void prepareInputs(int vaoId){
        for(int i = 0; i < nVar; i++) glBindVertexArray(vaoId);
    }
    
    /**
     * Returns the number of input variables for the script
     * @return 
     */
    public int getNumVar(){
        return nVar;
    }
}
