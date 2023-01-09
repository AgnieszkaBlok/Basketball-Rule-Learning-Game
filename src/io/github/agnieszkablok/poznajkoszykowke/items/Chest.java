package io.github.agnieszkablok.poznajkoszykowke.items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * A class that loading chest
 */

public class Chest extends Item {

    /**
     * Loads the chest in the given coordinates
     * @param x x coordinate
     * @param y y coordinate
     */
    public Chest(int x, int y){
        super(x, y);
        try {
            image = ImageIO.read(new FileInputStream("res/objects/chest.png"));
        } catch (IOException e) {
            System.out.println("Couldn't read the chest image!");
        }
    }
}
