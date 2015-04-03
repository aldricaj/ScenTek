
package ScenTek.render;

import static org.lwjgl.opengl.GL11.*;

/**
 * This interface will handle rendering
 * @author aaldrich
 */
public abstract class Renderer {
    protected int vao = -1;// allows subclass to determine whether or not it has a created vao
    protected float[] points;
    protected byte[] indices;
    protected Renderer(){
        
    }
    
    /**
     * Load the necessary settings for the render type
     
     * Should contain all initialization for whatever rendering process is needed by the subclass. It'll set up VAO mainly, using the id assigned
     */
    protected abstract void loadSettings();
    /**
     * Set everything modified to it's default value
     */
    protected abstract void unloadSettings();
    
    /**
     * Render at the points and the indices passed
     * @param pts points to be rendered at
     * @param indices indices for the triangles to be used
     */
    public void render(float[] pts, byte[] indices){
        points = pts;
        this.indices = indices;
        loadSettings();
        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_BYTE, 0);
        unloadSettings();
    }
    
    
}
