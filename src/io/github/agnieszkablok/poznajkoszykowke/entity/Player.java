package io.github.agnieszkablok.poznajkoszykowke.entity;

import io.github.agnieszkablok.poznajkoszykowke.items.*;
import io.github.agnieszkablok.poznajkoszykowke.location.Direction;
import io.github.agnieszkablok.poznajkoszykowke.main.GamePanel;
import io.github.agnieszkablok.poznajkoszykowke.main.KeyHandler;
import io.github.agnieszkablok.poznajkoszykowke.quiz.QuestionManager;
import io.github.agnieszkablok.poznajkoszykowke.quiz.QuestionWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Entity {
    private static final String PLAYER_QUESTIONS_DIRECTORY = "res/questions/player";
    private static final String REFEREE_QUESTIONS_DIRECTORY = "res/questions/referee";
    private static final int INITIAL_HEART_CNT = 3;
    private static final int INITIAL_SPEED = 4;
    GamePanel gp;
    KeyHandler keyH;
    public int hasKey = 0;
    public final int screenX;
    public final int screenY;

    private QuestionManager questionManager;

    private int heartCnt;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        screenX = GamePanel.SCREEN_WIDTH / 2 - (GamePanel.TILE_SIZE / 2);
        screenY = GamePanel.SCREEN_HEIGHT / 2 - (GamePanel.TILE_SIZE / 2);

        solidArea = new Rectangle(); // zrobienie strefy kolizyjnej, jako ze caly ma 48x48
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        this.heartCnt = INITIAL_HEART_CNT;

        try{
            this.questionManager = new QuestionManager(PLAYER_QUESTIONS_DIRECTORY, REFEREE_QUESTIONS_DIRECTORY);
        } catch(IOException e){
            System.out.println("Exception while initializing QuestionManager");
            e.printStackTrace();
        }

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() { //player position on world map

        worldX = GamePanel.TILE_SIZE * 23;
        worldY = GamePanel.TILE_SIZE * 21;
        speed = INITIAL_SPEED;
        direction = Direction.DOWN;
    }

    public void getPlayerImage() {

        try {

            up1 = ImageIO.read(new FileInputStream("res/player/boy_up_1.png"));
            up2 = ImageIO.read(new FileInputStream("res/player/boy_up_2.png"));
            down1 = ImageIO.read(new FileInputStream("res/player/boy_down_1.png"));
            down2 = ImageIO.read(new FileInputStream("res/player/boy_down_2.png"));
            left1 = ImageIO.read(new FileInputStream("res/player/boy_left_1.png"));
            left2 = ImageIO.read(new FileInputStream("res/player/boy_left_2.png"));
            right1 = ImageIO.read(new FileInputStream("res/player/boy_right_1.png"));
            right2 = ImageIO.read(new FileInputStream("res/player/boy_right_2.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.downPressed || keyH.upPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = Direction.UP;

            } else if (keyH.downPressed) {
                direction = Direction.DOWN;

            } else if (keyH.leftPressed) {
                direction = Direction.LEFT;

            } else {
                direction = Direction.RIGHT;

            }
            //Check tile  colision

            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check object colision
            int objIndex = gp.cChecker.checkItem(this, true);
            pickUpObject(objIndex);
            //if colision is false can move
            if (!collisionOn) {
                switch (direction) {
                    case UP -> worldY -= speed;
                    case DOWN -> worldY += speed;
                    case LEFT -> worldX -= speed;
                    case RIGHT -> worldX += speed;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {// mamy 60 klatek na sekunde wiec po kazdej 12 klatce zmienia
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;

            }

        }


    }

    private void handleResponseToQuestion(boolean response){
        if(response){
            hasKey++;
            gp.ui.showMessage("Zdobyłeś klucz");
        }
        else {
            heartCnt--;
            gp.ui.showMessage("Zła odpowiedź");
        }

    }

    private void handlePlayerQuestion(){
        keyH.clearPressedButtons();
        handleResponseToQuestion(QuestionWindow.showPlayerQuestion(gp, questionManager.getPlayerQuestion()));
    }

    private void handleRefereeQuestion(){
        keyH.clearPressedButtons();
        handleResponseToQuestion(QuestionWindow.showRefereeQuestion(gp, questionManager.getRefereeQuestion()));
    }

    public void pickUpObject(int i) {
        if (i == 999) return;

        Item item = gp.getItemAt(i);

        if (item instanceof Chest) {
            gp.ui.gameFinished = true;
        } else if (item instanceof Door) {
            if (hasKey == 0) {
                gp.ui.showMessage("Potrzebujesz klucza");
                return;
            }
            hasKey--;
            gp.deleteItemAt(i);
        } else if (item instanceof Key) {
            hasKey++;
            gp.deleteItemAt(i);

        } else if (item instanceof Ball) {
            handlePlayerQuestion();
            gp.deleteItemAt(i);
        } else if (item instanceof Whistle){
            handleRefereeQuestion();
            gp.deleteItemAt(i);
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case UP -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case DOWN -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case LEFT -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case RIGHT -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
