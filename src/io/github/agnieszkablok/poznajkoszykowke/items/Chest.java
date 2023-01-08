package io.github.agnieszkablok.poznajkoszykowke.items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Chest extends Item {

    public Chest(){

        name = "Chest";
        try {
            image = ImageIO.read(new FileInputStream("res/objects/chest.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
