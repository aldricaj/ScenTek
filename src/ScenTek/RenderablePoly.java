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

/**
 *
 * @author aldrich_a
 */
public class RenderablePoly extends Poly implements Renderable{
    private Color color;
    private static SimpleRenderer renderer = SimpleRenderer.getInstance();
    public RenderablePoly(Color c, Point[] p){
        super(p);
        color = c;
    }
    
    public RenderablePoly(Point[] points) {
        this(Color.RED, points);
        
    }

    @Override
    public void render() {
        renderer.setColor(color);
        renderer.render(super.getAsArray(), getTriIndices());
        
    }
    
    public byte[] getTriIndices(){
        Point[] pts = super.getPoints();
        byte[] indices = new byte[0];
        return new byte[0];
    }
}
