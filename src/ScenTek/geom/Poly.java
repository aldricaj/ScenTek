
package ScenTek.geom;

import ScenTek.render.Vertex;
import java.awt.geom.Path2D;
import java.util.Arrays;
import javax.naming.OperationNotSupportedException;

/**
 * Represents an n-gon in float values
 * @author aaldrich
 */
public class Poly {
    /**The list of all the points in the shape*/
    private Vertex[] points;
    /**The average of all the points*/
    private Vertex center;
    /**
     * Creates the Poly with the given points
     * @param points the points to use for vertices
     */
    public Poly(Vertex[] points){
        this.points = points;
        
        int length = points.length;
        int sumX = 0, sumY = 0;
        for(Vertex p : points){
            sumX += p.getX();
            sumY += p.getY();
        }
        center = new Vertex(sumX/length, sumY/length);
    }
    /**
     * Returns the center of the shape
     * @return the average of all the points in the shaper
     */
    public Vertex getCenter(){
        return center;
    }
    /**
     * @return An array of all the points in the shape
     */
    public Vertex[] getPoints(){
        return points;
    }
    /**
     * Puts the points into a 4-var format [x, y, z, w] and returns them as a single array
     * @return all the points in the 4 var format
     */
    public float[] getAsArray(){
        float[] fPts = new float[points.length*4];
        int index = 0;
        while(index < fPts.length){
            fPts[index] = points[index/4].getX();
            index++;
            fPts[index] = points[index/4].getY();
            index++;
            fPts[index] = 0;
            index++;
            fPts[index] = 1.0f;
            index++;
        }
        return fPts;
    }
    
    public void translate(float dx, float dy) throws OperationNotSupportedException{
        throw new OperationNotSupportedException("Awaiting matrix stuff");
    }
    
    public void rotate(float dRadians) throws OperationNotSupportedException{
        throw new OperationNotSupportedException("Awaiting matrix stuff");
    }
    
    public void scale(float magnitude) throws OperationNotSupportedException{
        throw new OperationNotSupportedException("Awaiting matrix stuff");
    
    }
    
}
