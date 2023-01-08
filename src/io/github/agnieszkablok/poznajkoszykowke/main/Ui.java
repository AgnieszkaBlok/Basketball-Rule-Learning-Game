package io.github.agnieszkablok.poznajkoszykowke.main;

import io.github.agnieszkablok.poznajkoszykowke.objects.ObjKey;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ui {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arialb_40,arialb_80,title_60;
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
        title_60 = new Font("Century",Font.BOLD,60);
        ObjKey key = new ObjKey();
       keyImage = key.image;

    }
    public void showMessage(String text){

        message = text;
        messageOn = true;
    }
    public void draw (Graphics2D g2) {
        this.g2 = g2;

        //Title state
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        if (gameFinished == true){

            g2.setFont(arialb_40);
            g2.setColor(Color.WHITE);

            String text;
            int textLenght;
            int x;
            int y;

            text = "Znalazłeś księgę";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
             x = gp.screenWidth/2 - textLenght/2 ;
             y =gp.screenHeight/2 - (gp.tileSize*3);

             g2.drawString(text,x,y);

            g2.setFont(arialb_80);
            g2.setColor(Color.ORANGE);
            text = "Gratulacje";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth/2 - textLenght/2 ;
            y =gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text,x,y);


            gp.gameThread = null;

        }else{

            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
            g2.drawString("x" + gp.player.hasKey,70,60);

            //MESSAGE
            if(messageOn  == true){

                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);

                messageCounter++;

                if(messageCounter >120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

    public void drawTitleScreen(){
        //Title name
        g2.setColor(new Color(98,157,90));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        g2.setFont(title_60);
        String text = "Poznaj koszykówkę";
        int x = gp.screenWidth/10;
        int y = gp.tileSize*3;
        //Shadow
        g2.setColor(Color.black);
        g2.drawString(text, x+3, y+5);
        //Main color
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);
        //Player image
        x = gp.screenWidth/2;
        y += gp.tileSize*2;


    }


}
