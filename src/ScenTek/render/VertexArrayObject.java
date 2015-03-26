
package ScenTek.render;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Wraps an opengl VAO
 * @author Andrew
 */
public class VertexArrayObject {
    
    public final int vao_id;
    private int numAttribs;
    public VertexArrayObject(){
        vao_id = glGenVertexArrays();
    }
    
    public void bind(){
        glBindVertexArray(vao_id);
    }
    
    public void unbind(){
        glBindVertexArray(0);
    }
    
    public void addAttribute(VertexBufferObject vbo){
        this.bind();
        glBindBuffer(GL_ARRAY_BUFFER, vbo.vbo_id);
        glVertexAttribPointer(numAttribs, vbo.size(), GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        this.unbind();
    }
}
