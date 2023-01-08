package io.github.agnieszkablok.poznajkoszykowke.main;

import io.github.agnieszkablok.poznajkoszykowke.items.Chest;
import io.github.agnieszkablok.poznajkoszykowke.items.Door;
import io.github.agnieszkablok.poznajkoszykowke.items.Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){

        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new Key();
        gp.obj[0].worldX = 23* gp.tileSize;
        gp.obj[0].worldY = 7* gp.tileSize;

        gp.obj[1] = new Key();
        gp.obj[1].worldX = 23* gp.tileSize;
        gp.obj[1].worldY = 40* gp.tileSize;

        gp.obj[2] = new Door();
        gp.obj[2].worldX = 10* gp.tileSize;
        gp.obj[2].worldY = 11* gp.tileSize;

        gp.obj[3] = new Door();
        gp.obj[3].worldX = 12* gp.tileSize;
        gp.obj[3].worldY =22* gp.tileSize;

        gp.obj[4] = new Door();
        gp.obj[4].worldX = 8* gp.tileSize;
        gp.obj[4].worldY = 28* gp.tileSize;

        gp.obj[5] = new Chest();
        gp.obj[5].worldX = 10* gp.tileSize;
        gp.obj[5].worldY = 7* gp.tileSize;

        gp.obj[6] = new Key();
        gp.obj[6].worldX = 38* gp.tileSize;
        gp.obj[6].worldY = 8* gp.tileSize;







    }
}
