
package ScenTek.render;

import de.matthiasmann.twl.utils.PNGDecoder;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Hashtable;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Manages opengl textures in singleton form 
 * @author aldrich_a
 */
public class TextureManager{
    /**The intial size of the texture table*/
    private final static int INIT_SIZE = 16;
    /**The table that stores the textures*/
    private static Hashtable<String, Integer> table;
    /**The instance of the TextureManager*/
    private static TextureManager instance;
    
    /**
     * 
     * @return the sole instance of the TextureManager
     */
    public static TextureManager getInstance(){
        return (instance == null) ? (instance = new TextureManager()) : instance;
    }
    /**
     * Private constructor
     */
    private TextureManager(){
        table = new Hashtable(INIT_SIZE);
    }
    /**
     * Initializes a PNG texture and adds it to the hashtable using TEXTURE_UNIT0 as the texture unit
     * @param name the name of the texture in the hash table
     * @param path the path of the PNG image from the working directory
     */
    public void addTexture(String name, String path){
        addTexture(name, path, GL_TEXTURE0);
    }
    /**
     * Initialized a PNG texture and adds it to the hashtable using the passed texture unit
     * @param name
     * @param path
     * @param textureUnit 
     */
    public void addTexture(String name, String path, int textureUnit){
        int textureID = glGenTextures();
        ByteBuffer b = null;
        int texWidth = 0, texHeight = 0;
        try{
            InputStream in = new FileInputStream(path);
            
            PNGDecoder decoder = new PNGDecoder(in);
            
            texWidth = decoder.getWidth();
            texHeight = decoder.getHeight();
            
            b = ByteBuffer.allocate(4*decoder.getWidth()*4*decoder.getHeight());
            decoder.decode(b, texWidth*4, PNGDecoder.Format.RGBA);
            
            b.flip();
            in.close();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
        glActiveTexture(textureUnit);
        glBindTexture(GL_TEXTURE_2D, textureID);
        
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, texWidth, texHeight, 0, GL_RGBA, GL_UNSIGNED_BYTE, b);
        glGenerateMipmap(GL_TEXTURE_2D);
        table.put(name, textureID);
        glBindTexture(GL_TEXTURE_2D, 0);
    }
    
    public int getTexture(String name){
        return table.get(name).intValue();
    }
    
    public void removeTexture(String name){
        table.remove(name);
    }
}
