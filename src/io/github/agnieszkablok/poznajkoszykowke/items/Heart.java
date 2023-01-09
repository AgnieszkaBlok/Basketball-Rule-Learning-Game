package io.github.agnieszkablok.poznajkoszykowke.items;

import io.github.agnieszkablok.poznajkoszykowke.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Heart extends Item {
    GamePanel gp;



        public Heart(int x, int y) {
            super(x, y);
            try {
                image = ImageIO.read(new FileInputStream("res/objects/heart_full.png"));
                image = ImageIO.read(new FileInputStream("res/objects/heart_blank.png"));

            } catch (IOException e) {
                System.out.println("Couldn't read the chest image!");
            }
        }
    }

