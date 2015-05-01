/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScenTek.render;

/**
 *
 * @author aldrich_a
 */
public class Vertex {
    private float[] coords;
    public static final int num_elements = 4;
    public static final int element_size = 4;
    public Vertex(float[] coords){
        if(coords.length == 4) this.coords = coords;
        else{
            this.coords = new float[]{coords[0],coords[1], coords[2], 1};
        }
    }
    public Vertex(float x, float y){
        this(new float[]{x, y, 0.0f, 1.0f});
    }
    public void setCoords(float x, float y, float z, float w){
        coords = new float[]{x,y,z,w};
    }
    
    public void setCoords(float x, float y, float z){
        setCoords(x,y,z,1);
    }
    
    public void setCoords(float[] coords){
        if(coords.length == 4) this.coords = coords;
        else{
            this.coords = new float[]{coords[0],coords[1], coords[2], 1};
        }
    }
    public void transform(float x, float y){
        coords[0] += x;
        coords[1] += y;
    }
    public float getX(){
        return coords[0];
    }
    
    public float getY(){
        return coords[1];
    }
    public float[] getCoords(){
        return coords;
    }
    
    public static int getSize(){
        return num_elements * element_size;
    }
}
