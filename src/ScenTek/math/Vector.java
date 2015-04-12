/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScenTek.math;

import ScenTek.geom.Point;
import static java.lang.Math.*;
/**
 *
 * @author aldrich_a
 */
public class Vector {
    
    private float magnitude;
    private float angle;
    private float x, y;
    
    /**
     * Creates a vector with the given magnitude and angle(in radians)
     * @param mag magnitude of the vector
     * @param rads angle of the vector
     */
    public Vector(float mag, float rads){
        magnitude = mag;
        angle = rads;
        x = magnitude * (float) cos(angle);
        y = magnitude * (float) sin(angle);
    }
    /**
     * Creates a Vector from the passed point 
     * @param p the dx and dy of the vector
     */
    public Vector(Point p){
        x = p.getX();
        y = p.getY();
        magnitude = (float) sqrt(x*x+y*y);
        angle = (float) tan(y/x);
    }
    /**
     * A 0 calculation constructor
     * @param x dx of Vector
     * @param y dy of Vectot
     * @param mag magnitude of vector
     * @param rads angle of Vector in radians
     */
    public Vector(float x, float y, float mag, float rads){
        this.x = x;
        this.y = y;
        magnitude = mag;
        angle = rads;
    }
    /**
     * Returns the dot product between the Vectors
     
     * Useful for figuring out the facing of an object 
     
     * @param v
     * @return 
     */
    public float getDotProduct(Vector v){
        return x * v.x + y * v.y;
    }
    /**
     * normailizes the Vector
     */
    public void normalize(){
        magnitude = 1;
        Point p = this.getAsPoint();
        x = p.getX();
        y = p.getY();
    }
    /**
     * Returns a normalized copy of the vector
     * @return a copy of the Vector that has been normalized
     */
    public Vector getNormalized(){
        Vector nVector = new Vector(x, y, magnitude, angle);
        nVector.normalize();
        return nVector;
    }
    
    public float getMagnitude(){
        return magnitude;
    }
    
    public float getAngle(){
        return angle;
    }
    
    /**
     * Inverts the vector
     * @return The instance of the class inverted
     */
    public Vector invert(){
        magnitude *= -1;
        return this;
    }
    
    /**
     * Returns the point values for the vector
     * @return the vector as (dx, dy)
     */
    public Point getAsPoint(){
        
        return new Point(x, y);
    }
}
