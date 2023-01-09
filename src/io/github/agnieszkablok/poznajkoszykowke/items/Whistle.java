package io.github.agnieszkablok.poznajkoszykowke.items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * A class that loading whistle
 */

public class Whistle extends Item{
    /**
     * Loads the whistle in the given coordinates
     * @param x x coordinate
     * @param y y coordinate
     */
    public Whistle(int x, int y){
        super(x, y);
        try {
            image = ImageIO.read(new FileInputStream("res/objects/whistle.png"));
        } catch (IOException e) {
            System.out.println("Couldn't read the ball image!");
        }
    }
}
