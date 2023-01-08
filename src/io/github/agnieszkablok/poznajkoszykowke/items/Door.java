package io.github.agnieszkablok.poznajkoszykowke.items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Door extends Item {
        public Door() {
            collision = true;

            try {
                image = ImageIO.read(new FileInputStream("res/objects/door.png"));
            } catch (IOException e) {
                System.out.println("Couldn't read the chest image!");
            }
        }
    }

