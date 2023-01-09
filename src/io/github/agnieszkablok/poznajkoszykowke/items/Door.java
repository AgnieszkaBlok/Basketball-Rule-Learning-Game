package io.github.agnieszkablok.poznajkoszykowke.items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * A class that loading door
 */

public class Door extends Item {

    /**
     * Loads the door in the given coordinates
     * @param x x coordinate
     * @param y y coordinate
     */
        public Door(int x, int y) {
            super(x, y);
            collision = true;

            try {
                image = ImageIO.read(new FileInputStream("res/objects/door.png"));
            } catch (IOException e) {
                System.out.println("Couldn't read the chest image!");
            }
        }
    }

