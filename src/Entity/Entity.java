package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

//pleyer,monter,npc classes
public class Entity {

    public int worldX,worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // pokazuje obraz z dostepnym buforem, uzywamy do przechowywania obrzow plikow
    public String direction;
    public  int spriteCounter = 0;
    public  int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;



}
