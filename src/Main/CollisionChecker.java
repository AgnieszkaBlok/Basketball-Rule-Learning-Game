package Main;

import Entity.Entity;

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

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){// EPISOD 6 24:00 tlumaczy
            case"up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow]; //lewy róg kwadratu
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];  //prawy rog kwadratu
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case"down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow]; //lewy róg kwadratu
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];  //prawy rog kwadratu
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case"left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow]; //lewy róg kwadratu
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];  //prawy rog kwadratu
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow]; //lewy róg kwadratu
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];  //prawy rog kwadratu
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;

        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {

            if (gp.obj[i] != null) {
                // get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // get the objects solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) { //simulating entitys movment and check when it will be after it moved
                    //ep 8 10:25
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects((gp.obj[i].solidArea))) {
                            if (gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {// to jest pod npc
                                index = i;
                            }
                        }

                            break;

                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects((gp.obj[i].solidArea))) {
                            if (gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {// to jest pod npc
                                index = i;
                            }

                        }
                            break;

                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects((gp.obj[i].solidArea))) {
                            if (gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {// to jest pod npc
                                index = i;
                            }
                        }
                            break;

                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects((gp.obj[i].solidArea))) {
                            if (gp.obj[i].collision == true) {
                                entity.collisionOn = true;
                            }
                            if (player == true) {// to jest pod npc
                                index = i;
                            }
                        }

                            break;

                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y= gp.obj[i].getSolidAreaDefaultY;
            }
        }
        return index;
    }
}
