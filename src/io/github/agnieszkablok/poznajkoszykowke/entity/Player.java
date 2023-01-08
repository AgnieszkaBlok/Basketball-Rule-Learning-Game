package io.github.agnieszkablok.poznajkoszykowke.entity;

import io.github.agnieszkablok.poznajkoszykowke.main.GamePanel;
import io.github.agnieszkablok.poznajkoszykowke.main.KeyHandler;
import io.github.agnieszkablok.poznajkoszykowke.quiz.Question;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    Question questions;
   public int hasKey = 0;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX =gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(); // zrobienie strefy kolizyjnej, jako ze caly ma 48x48
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();

    }
    public void setDefaultValues(){ //player position on world map

        worldX= gp.tileSize * 23;
        worldY = gp.tileSize *  21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){

     try{

         up1 = ImageIO.read(new FileInputStream("res/player/boy_up_1.png"));
         up2 = ImageIO.read(new FileInputStream("res/player/boy_up_2.png"));
         down1 = ImageIO.read(new FileInputStream("res/player/boy_down_1.png"));
         down2 = ImageIO.read(new FileInputStream("res/player/boy_down_2.png"));
         left1 = ImageIO.read(new FileInputStream("res/player/boy_left_1.png"));
         left2 = ImageIO.read(new FileInputStream("res/player/boy_left_2.png"));
         right1 = ImageIO.read(new FileInputStream("res/player/boy_right_1.png"));
         right2 = ImageIO.read(new FileInputStream("res/player/boy_right_2.png"));


       }catch(IOException e){
           e.printStackTrace();
        }
   }

   private void swapSprite(){

   }
    public void update(){
        if(keyH.downPressed == true || keyH.upPressed == true || keyH.leftPressed == true || keyH.rightPressed == true ){
            if(keyH.upPressed == true){
                direction = "up";

            }
            else if(keyH.downPressed == true){
                direction = "down";

            }
            else if(keyH.leftPressed==true){
                direction = "left";

            } else if (keyH.rightPressed==true) {
                direction ="right";

            }
            //Check tile  colision

            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check object colision
            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);
            //if colision is false can move
            if(collisionOn == false){
                switch(direction){
                    case"up":
                        worldY -= speed;
                        break;
                    case"down":
                        worldY+= speed;
                        break;
                    case"left":
                        worldX -= speed;
                        break;
                    case"right":
                        worldX += speed;
                        break;

                }
            }

            spriteCounter ++;
            if(spriteCounter >12){// mamy 60 klatek na sekunde wiec po kazdej 12 klatce zmienia
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum ==2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;

            }

        }


    }

    public void pickUpObject( int i) {

        if (i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Key" + hasKey);
                   // questions. otwierac okno dialogowe

                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                        //gp.ui.showMessage("You opened the door");
                    }else{
                       gp.ui.showMessage("You need a key");
                   }
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;

                    break;


            }
        }
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }

                break;

            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }

                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }

                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;

        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize,null);
    }
}
