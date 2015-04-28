/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScenTek.render;

/**
 *
 * @author aldrich_a
 */
public class RenderException extends Exception {

    /**
     * Creates a new instance of <code>RenderException</code> without detail
     * message.
     */
    public RenderException() {
    }

    /**
     * Constructs an instance of <code>RenderException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public RenderException(String msg) {
        super(msg);
    }
}
