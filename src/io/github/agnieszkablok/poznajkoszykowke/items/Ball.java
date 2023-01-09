package io.github.agnieszkablok.poznajkoszykowke.items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * A class representing a Ball.
 */
public class Ball extends Item{
    /**
     * Loads the ball in the given coordinates
     * @param x x coordinate
     * @param y y coordinate
     */
    public Ball(int x, int y){
        super(x, y);
        try {
            image = ImageIO.read(new FileInputStream("res/objects/ball.png"));
        } catch (IOException e) {
            System.out.println("Couldn't read the ball image!");
        }
    }
}
