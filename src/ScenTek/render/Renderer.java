
package ScenTek;

/**
 * This interface will handle rendering
 * @author aaldrich
 */
public interface Renderer {
    /**
     * Renders the passed points using Triangles made from the passed indices
     * @param pts the points to be rendered. In order in clockwise order
     * @param indices indices in the pts array that creates triangles
     */
    public void render(float[][] pts, int[] indices);
    
}
