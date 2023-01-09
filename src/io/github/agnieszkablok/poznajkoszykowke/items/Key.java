package io.github.agnieszkablok.poznajkoszykowke.items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * A class that loading key
 */

public class Key extends Item {
    /**
     * Loads the key in the given coordinates
     * @param x x coordinate
     * @param y y coordinate
     */
    public Key(int x, int y){
        super(x, y);
        try{
            image = ImageIO.read(new FileInputStream("res/objects/key.png"));
        }catch(IOException e){
            System.out.println("Couldn't read the chest image!");
        }
    }

}
