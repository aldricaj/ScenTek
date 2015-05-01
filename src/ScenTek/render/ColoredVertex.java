
package ScenTek.render;

import java.awt.Color;

/**
 * Represents a colored vertex
 * @author aldrich_a
 */
public class ColoredVertex extends Vertex{
    /**The color of the vertex*/
    private Color color;
    
    public static final int num_elements = Vertex.num_elements + 4;
    private float[] rgba;
    public ColoredVertex(float[] coords, Color c){
        super(coords);
        rgba = new float[4];
        setColor(c);
    }
    
    public void setColor(Color c){
        color = c;
        rgba = color.getColorComponents(rgba);
    }
    
    public float[] getRGBA(){
        return rgba;
    }
    
    public static int getSize(){
        return num_elements * Vertex.element_size;
    }
}
