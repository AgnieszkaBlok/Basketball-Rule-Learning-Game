package io.github.agnieszkablok.poznajkoszykowke.main;

import io.github.agnieszkablok.poznajkoszykowke.entity.Entity;
import io.github.agnieszkablok.poznajkoszykowke.items.Item;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp){

        this.gp = gp;
    }

    public void checkTile( Entity entity){ // wszystkie kolizje bloki, potowry
      // wymiary tego kwadratu na mapie
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;// +8
        int entityRightWorldX = entity.worldX +entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.TILE_SIZE;
        int entityRightCol = entityRightWorldX/gp.TILE_SIZE;
        int entityTopRow = entityTopWorldY/gp.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY/gp.TILE_SIZE;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case"up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow]; //lewy róg kwadratu
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];  //prawy rog kwadratu
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case"down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow]; //lewy róg kwadratu
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];  //prawy rog kwadratu
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case"left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow]; //lewy róg kwadratu
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];  //prawy rog kwadratu
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.TILE_SIZE;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow]; //lewy róg kwadratu
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];  //prawy rog kwadratu
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;

        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.getItemsCnt(); i++) {
            Item item = gp.getItemAt(i);

            if (item != null) {
                // get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // get the objects solid area position
                item.solidArea.x = item.worldX + item.solidArea.x;
                item.solidArea.y = item.worldY + item.solidArea.y;

                switch (entity.direction) { //simulating entitys movment and check when it will be after it moved
                    //ep 8 10:25
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects((item.solidArea))) {
                            if (item.collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {// to jest pod npc
                                index = i;
                            }
                        }

                            break;

                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects((item.solidArea))) {
                            if (item.collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {// to jest pod npc
                                index = i;
                            }

                        }
                            break;

                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects((item.solidArea))) {
                            if (item.collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {// to jest pod npc
                                index = i;
                            }
                        }
                            break;

                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects((item.solidArea))) {
                            if (item.collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {// to jest pod npc
                                index = i;
                            }
                        }

                            break;

                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                item.solidArea.x = item.solidAreaDefaultX;
                item.solidArea.y= item.getSolidAreaDefaultY;
            }
        }
        return index;
    }
}
