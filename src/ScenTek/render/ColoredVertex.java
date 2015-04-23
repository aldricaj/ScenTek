
package ScenTek.render;

import java.awt.Color;

/**
 * Represents a colored vertex
 * @author aldrich_a
 */
public class ColoredVertex extends Vertex{
    /**The color of the vertex*/
    private Color color;
    /**The size of the vertex when passed to a shader*/
    private int componentSize = 8;
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
    
    @Override
    public int getSize(){
        return componentSize;
    }
}
