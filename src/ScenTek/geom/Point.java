
package ScenTek.geom;
import static java.lang.Math.*;
/**
 * Represents a point in float values
 * @author aaldrich
 */
public class Point {
    /**
     * x component of the Point
     */
    private float x;
    /**
     * y component of the Point
     */
    private float y;
    /**
     * Creates a point at the given x and y
     * @param x x component
     * @param y y component
     */
    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }
    /**
     * Returns the distance to the passed point
     * @param p The second point
     * @return a float representing the distance to the point
     */
    public float distTo(Point p){
        return (float) sqrt(distToSqrd(p));
    }
    /**
     * Returns the distance to the passed point squared. Useful for speedy operations
     * @param p the second point
     * @return the distance to the passed point squared
     */
    public float distToSqrd(Point p){
        return (float)(pow(p.x - x, 2)+pow(p.y - x, 2));
    }
    /**
     * Moves the point relative to its position
     * @param dx the change-in x
     * @param dy the change-in y
     */
    public void translate(float dx, float dy){
        x += dx;
        y += dy;
    }
    /**
     * Moves the point to the selected coords
     * @param x
     * @param y 
     */
    public void assign(float x, float y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Return x component
     * @return x component of the point
     */
    public float getX(){
        return x;
    }
    /**
     * Return y component
     * @return y component
     */
    public float getY(){
        return y;
    }
}
