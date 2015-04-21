
package ScenTek.render;

import java.util.Hashtable;

/**
 * Manages opengl textures in singleton form 
 * @author aldrich_a
 */
public class TextureManager{
    
    private final static int INIT_SIZE = 16;
    private static Hashtable<String, Integer> table;
    private static TextureManager instance;
    public static TextureManager getInstance(){
        return (instance == null) ? (instance = new TextureManager()) : instance;
    }
    
    private TextureManager(){
        table = new Hashtable(INIT_SIZE);
    }
    
    public void addTexture(String name, String path){
        int textureID = 0;
        // load image into opengl and assign textureID to not 0
        table.put(name, textureID);
    }
    
    public int getTexture(String name){
        return table.get(name).intValue();
    }
    
    public void removeTexture(String name){
        table.remove(name);
    }
}
