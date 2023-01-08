package io.github.agnieszkablok.poznajkoszykowke.main;

import io.github.agnieszkablok.poznajkoszykowke.items.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){

        this.gp = gp;
    }

    private void placeItem(int index, Item item, int x, int y){
        gp.obj[index] = item;
        gp.obj[index].worldX = x * gp.tileSize;
        gp.obj[index].worldY = y * gp.tileSize;
    }
    public void setObject(){
        placeItem(0, new Whistle(), 23, 7);
        placeItem(1, new Ball(), 23, 40);
        placeItem(2, new Door(), 10, 11);
        placeItem(3, new Door(), 12, 22);
        placeItem(4, new Door(), 8, 28);
        placeItem(5, new Chest(), 10, 7);
        placeItem(6, new Ball(), 38, 8);
    }
}
