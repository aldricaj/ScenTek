

package ScenTek;

import ScenTek.render.Renderer;

/**
 * A Renderable will be able to render itself onscreen
 * @author Andrew
 */
public interface Renderable {
    /**
     * instructs the Renderable to render itself on screen
     */
    public void render(Renderer r);
    
}
