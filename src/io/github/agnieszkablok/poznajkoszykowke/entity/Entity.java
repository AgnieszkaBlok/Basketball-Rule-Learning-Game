package io.github.agnieszkablok.poznajkoszykowke.entity;

import io.github.agnieszkablok.poznajkoszykowke.location.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;

//pleyer,monter,npc classes
public class Entity {

    public int worldX,worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // pokazuje obraz z dostepnym buforem, uzywamy do przechowywania obrzow plikow
    public Direction direction;
    public  int spriteCounter = 0;
    public  int spriteNum = 1;
    public Rectangle solidArea;
    public  int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;



}
