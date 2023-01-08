package io.github.agnieszkablok.poznajkoszykowke.main;

import io.github.agnieszkablok.poznajkoszykowke.items.Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ui {
    GamePanel gp;
    Font arial_40, arialb_40,arialb_80;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;


    public Ui (GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arialb_40 = new Font("Arial",Font.BOLD,40);
        arialb_80 = new Font("Arial",Font.BOLD,80);
        Key key = new Key(-1, -1);
       keyImage = key.image;

    }
    public void showMessage(String text){

        message = text;
        messageOn = true;
    }
    public void draw (Graphics2D g2) {

        if (gameFinished == true){

            g2.setFont(arialb_40);
            g2.setColor(Color.WHITE);

            String text;
            int textLenght;
            int x;
            int y;

            text = "Znalazłeś księgę";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
             x = gp.SCREEN_WIDTH /2 - textLenght/2 ;
             y =gp.SCREEN_HEIGHT /2 - (gp.TILE_SIZE *3);

             g2.drawString(text,x,y);

            g2.setFont(arialb_80);
            g2.setColor(Color.ORANGE);
            text = "Gratulacje";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.SCREEN_WIDTH /2 - textLenght/2 ;
            y =gp.SCREEN_HEIGHT /2 + (gp.TILE_SIZE *2);
            g2.drawString(text,x,y);


            gp.gameThread = null;





        }else{

            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.TILE_SIZE /2,gp.TILE_SIZE /2,gp.TILE_SIZE,gp.TILE_SIZE,null);
            g2.drawString("x" + gp.player.hasKey,70,60);

            //MESSAGE
            if(messageOn  == true){

                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.TILE_SIZE /2, gp.TILE_SIZE *5);

                messageCounter++;

                if(messageCounter >120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }


        }



    }


}
