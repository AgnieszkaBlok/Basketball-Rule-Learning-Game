package io.github.agnieszkablok.poznajkoszykowke.items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Ball extends Item{
    public Ball(int x, int y){
        super(x, y);
        try {
            image = ImageIO.read(new FileInputStream("res/objects/ball.png"));
        } catch (IOException e) {
            System.out.println("Couldn't read the ball image!");
        }
    }
}
