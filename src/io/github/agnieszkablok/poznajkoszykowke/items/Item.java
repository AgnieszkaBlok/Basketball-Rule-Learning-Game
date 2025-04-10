package io.github.agnieszkablok.poznajkoszykowke.items;

import io.github.agnieszkablok.poznajkoszykowke.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A class that is managing items
 */
public class Item {

    public BufferedImage image;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);//solid area dla kazdego obiektu
    public int solidAreaDefaultX =0;
    public int getSolidAreaDefaultY = 0;

    public Item(int x, int y){

        this.worldX = x * GamePanel.TILE_SIZE;
        this.worldY = y * GamePanel.TILE_SIZE;
    }

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + GamePanel.TILE_SIZE > gp.player.worldX - gp.player.screenX    //  wyswietlaly sie tylko te z najblizszego otoczenia a nie cala mapa
                && worldX - GamePanel.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                worldY + GamePanel.TILE_SIZE >gp.player.worldY -gp.player.screenY &&
                worldY - GamePanel.TILE_SIZE < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        }



    }
}
