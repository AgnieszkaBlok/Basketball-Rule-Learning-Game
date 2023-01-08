package io.github.agnieszkablok.poznajkoszykowke.items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Key extends Item {
    public Key(int x, int y){
        super(x, y);
        try{
            image = ImageIO.read(new FileInputStream("res/objects/key.png"));
        }catch(IOException e){
            System.out.println("Couldn't read the chest image!");
        }
    }

}
