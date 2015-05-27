
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
    public ColoredVertex(Vertex v, Color c){
        this(v.getCoords(), c);
        
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
    
    @Override
    public float[] getElements(){
        float[] elements = new float[rgba.length + super.getCoords().length];
        int index = 0;
        float[] coords = super.getCoords();
        for(int i = 0; i<coords.length; i++){
            elements[index] = coords[i];
            index++;
        }
        for(int i = 0; i < rgba.length; i++){
            elements[index] = rgba[i];
            index++;
        }
        return elements;
    }
}
