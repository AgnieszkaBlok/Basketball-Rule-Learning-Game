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

        switch(entity.direction){
            case"up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow]; //lewy róg kwadratu
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];  //prawy rog kwadratu
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case"down":
                break;
            case"left":
                break;
            case "right":
                break;



        }


    }
}
