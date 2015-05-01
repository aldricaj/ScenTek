
package ScenTek;

import ScenTek.render.Vertex;
import javax.naming.OperationNotSupportedException;

/**
 * Contains 3 {@link Vertex} for the purpose of rendering
 * @author Andrew
 */
class Tri {
    private Vertex[] points;
    /**
     * Creates the Tri from the 3 passed points
     * @param points 
     */
    public Tri(Vertex[] points){
        if(points.length > 3){
            throw new IllegalArgumentException("More than 3 Points passed");
        }
        if(points.length < 3){
            throw new IllegalArgumentException("Less than 3 Points passed");
        }
        this.points = points;
    }
    /**
     * Takes 3 points as parameters and calls the array constructor
     * @param p1
     * @param p2
     * @param p3 
     */
    public Tri(Vertex p1, Vertex p2, Vertex p3){
        this(new Vertex[]{p1, p2, p3});
    }
    /**
     * Creates a Tri from an array in a {x1,y1,x2,y2,x3,y3} format 
     * @param points coordinates stored as {x1,y1,x2,y2,x3,y3} 
     */
    public Tri(float[] points){
        this(new Vertex[]{
            new Vertex(points[0], points[1]),
            new Vertex(points[2], points[3]),
            new Vertex(points[4], points[5])});
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
