/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScenTek.render;

import java.awt.Color;

/**
 *
 * @author aldrich_a
 */
public class TexturedVertex extends ColoredVertex {

    private float[] st;

    public TexturedVertex(float[] coords, Color c, float[] stCoords) {
        super(coords, c);
        st = stCoords;
    }

    public float[] getSTCoords() {
        return st;
    }

    public void setSTCoords(float s, float t) {
        setSTCoords(new float[]{s, t});
    }

    public void setSTCoords(float[] st) {
        this.st = st;
    }

    @Override
    public float[] getElements() {
        float[] arr = super.getElements();
        float[] elements = new float[arr.length + st.length];
        int index = 0;
        while (index < arr.length) {
            elements[index] = arr[index];
            index++;
        }
        for (int i = 0; i < st.length; i++) {
            elements[index] = st[i];
        }
        return elements;
    }

}
