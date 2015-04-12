
package ScenTek.geom;

import java.awt.geom.Path2D;
import javax.naming.OperationNotSupportedException;

/**
 * Represents an n-gon in float values
 * @author aaldrich
 */
public class Poly {
    
    private Point[] points;
    /**
     * the average of all the points
     */
    private Point center;
    public Poly(Point[] points){
        this.points = points;
        
        int length = points.length;
        int sumX = 0, sumY = 0;
        for(Point p : points){
            sumX += p.getX();
            sumY += p.getY();
        }
        center = new Point(sumX/length, sumY/length);
    }
    
    public Point getCenter(){
        return center;
    }
    
    public Point[] getPoints(){
        return points;
    }
    
    public float[] getAsArray(){
        float[] pts = new float[points.length*2];
        int index = 0;
        
        for(int i = 0; i < pts.length; i += 2){
            pts[i] = points[index].getX();
            pts[i+1] = points[index].getY();
            index++;
        }
        return pts;
    }
    
    public boolean contains(Point p){
        // TODO create this method
        // figure out whether it is better to have intensive programming in constructor, or if its better to have it in-method(leaning towards constructor
        return false;
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
