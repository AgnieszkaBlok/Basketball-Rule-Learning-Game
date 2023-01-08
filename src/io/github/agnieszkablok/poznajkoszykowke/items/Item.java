package io.github.agnieszkablok.poznajkoszykowke.items;

import io.github.agnieszkablok.poznajkoszykowke.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    public BufferedImage image;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);//solid area dla kazdego obiektu
    public int solidAreaDefaultX =0;
    public int getSolidAreaDefaultY = 0;

    private final int x;
    private final int y;



    Item(int x, int y){
        this.x = x;
        this.y = y;

        this.worldX = x * GamePanel.TILE_SIZE;
        this.worldY = y * GamePanel.TILE_SIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX    //  wyswietlaly sie tylko te z najblizszego otoczenia a nie cala mapa
                && worldX -gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                worldY + gp.TILE_SIZE >gp.player.worldY -gp.player.screenY &&
                worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
        }



    }
}
