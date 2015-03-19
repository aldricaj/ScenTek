
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
    public final float x;
    /**
     * y component of the Point
     */
    public final float y;
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
}
