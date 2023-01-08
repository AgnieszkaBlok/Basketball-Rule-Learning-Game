package io.github.agnieszkablok.poznajkoszykowke.items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Key extends Item {

    public Key(){

        name ="Key";
        try{
            image = ImageIO.read(new FileInputStream("res/objects/key.png"));



        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
