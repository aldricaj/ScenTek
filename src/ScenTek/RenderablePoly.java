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
    }
    
    public RenderablePoly(Point[] points) {
        this(Color.RED, points);
        
    }

    @Override
    public void render() {
        renderer.setColor(color);
        renderer.render(super.getAsArray(), tris);
        
    }
}
