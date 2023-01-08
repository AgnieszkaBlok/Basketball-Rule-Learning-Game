package io.github.agnieszkablok.poznajkoszykowke.items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class ObjDoor extends Item {


        public ObjDoor() {

            name = "Door";
            try {
                image = ImageIO.read(new FileInputStream("res/objects/door.png"));


            } catch (IOException e) {
                e.printStackTrace();
            }
            collision = true;


        }
    }

