/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScenTek;

import ScenTek.geom.Point;
import ScenTek.geom.Poly;
import ScenTek.render.SimpleRenderer;
import java.awt.Color;
import java.util.Arrays;

/**
 *
 * @author aldrich_a
 */
public class RenderablePoly extends Poly implements Renderable{
    private Color color;
    private static SimpleRenderer renderer = SimpleRenderer.getInstance();
    private byte[] tris;
    private boolean visible;
    /**
     * Creates a renderable Polygon with the passed color and coordinates
     * @param c color that the poly will appear as
     * @param p vertices for the polygon
     */
    public RenderablePoly(Color c, Point[] p){
        super(p);
        color = c;
        // generate render indexes
        Point[] pts = super.getPoints();
        byte[][] indices = new byte[pts.length - 2][3];
        
        byte lastIndex = 1;
        for(int i = 0; i < indices.length; i++){
            indices[i] = new byte[]{0, lastIndex, ++lastIndex};
        }
        
        tris = new byte[indices.length * 3];
        
        for(int index = 0; index < tris.length; index++){
            tris[index] = indices[index/3][index%3];
        }
        visible = true;
    }
    /**
     * Creates a renderable poly by copying the points from the passed Poly
     * @param c color to be used
     * @param p poly to be copied
     */
    public RenderablePoly(Color c, Poly p){
        this(c, p.getPoints());
    }
    /**
     * Creates a Renderable Poly with a default red color
     * @param points points to use as vertices
     */
    public RenderablePoly(Point[] points) {
        this(Color.RED, points);
        
    }
    
    /**
     * Sets the color to the passed Color
     * @param c color to be used
     */
    public void setColor(Color c){
        color = c;
    }
    /**
     * Returns the currently used color
     * @return the current color
     */
    public Color getColor(){
        return color;
    }
    /**
     * States whether or not the Poly will be rendered
     * @return a boolean that will be true if the Poly renders
     */
    public boolean isVisible(){
        return visible;
    }
    /**
     * Sets the visibilty to the boolean passed
     * @param b if b is true, then the shape will be rendered
     */
    public void setVisibilty(boolean b){
        visible = b;
    }
    /**
     * Toggles the visibility
     */
    public void toggleVisibility(){
        visible = !visible;
    }
    
    @Override
    /**
     * Renders the polygon if the visibilty is set to true
     */
    public void render() {
        if(!visible) return;
        renderer.setColor(color);
        renderer.render(super.getAsArray(), tris);
        
    }
}
