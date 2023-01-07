package Main;

import Entity.Player;
import Objects.SuperObjects;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements  Runnable{

    // Screen Settings
    final int originalTileSize = 16; //16x16 tile
    final  int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48
    public final int maxScreenCol =16;  //kolumny
    public final int maxScreenRow =12; //wiersze
    public final int screenWidth = tileSize * maxScreenCol; //768
    public final int screenHeight = tileSize * maxScreenRow; //576

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    //FPS
    int FPS =60;

    TileManager tileM = new TileManager(this);

    KeyHandler keyH =new KeyHandler();
    Thread gameThread; //program zaczyna dopoki nie zatrzymama

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
     public Player player = new Player(this, keyH);
     public SuperObjects obj[] = new SuperObjects[10]; //przygotowanie na maks 10 obietow naraz



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight)); //panel size of the game panel
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);// better rendering
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject(); //

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();//wywolujw metode run
    }

    @Override

    public void run(){

        double drawInterval = 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/ drawInterval;

            lastTime =currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta --;
            }
        }
    }
   /* public void run() {
        double drawInterval = 1000000000/FPS; //0.0166 secounds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){ //dopoki game thread istnieje powtrzaj proces

            // update information such as character postitions
            update();
            // draw the screen with the update information
            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; //milli sekundy musza byc

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
                //e.printStackTrace();
            }

        }

    }*/
    public void update(){
    player.update();

    }
    public void paintComponent(Graphics g){ //standard method to draw on jpanel
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        //tile
        tileM.draw(g2);

        //object
        for(int i =0; i<obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        player.draw(g2);
        g2.dispose();//??? 8.05 film 2



    }
}
