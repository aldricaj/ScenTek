
package ScenTek.render;
import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Wraps an opengl VAO
 * @author Andrew
 */
public class VertexArrayObject {
    public enum VBOType{
        POINT, COLOR
    };
    public final int vao_id;
    private int numAttribs;
    private ArrayList<VBOType> vboTypes;
    private ArrayList<VertexBufferObject> vbos;
    private int typeIndex;// the current vbo type being returned by getNextType;
    public VertexArrayObject(){
        vao_id = glGenVertexArrays();
        vboTypes = new ArrayList(16);// init the ArrayList to the default max number of aspects
    }
    
    public void bind(){
        glBindVertexArray(vao_id);
    }
    
    public void unbind(){
        glBindVertexArray(0);
    }
    
    public void addAttribute(VertexBufferObject vbo, VBOType type){
        this.bind();
        glBindBuffer(GL_ARRAY_BUFFER, vbo.vbo_id);
        glVertexAttribPointer(numAttribs, vbo.size(), GL_FLOAT, false, 0, 0);
        vboTypes.add(type);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        this.unbind();
    }
    
    public ArrayList<VBOType> getAttributeTypes(){
        return vboTypes;
    } 
    /**
     * Returns the next type. Should be used for rendering
     * @return a VBOType object, unless there is no types left, then it returns a null
     */
    public VBOType getNextType(){
        return (typeIndex < vboTypes.size()) ? (vboTypes.get(typeIndex++)) : null;
    }
}
