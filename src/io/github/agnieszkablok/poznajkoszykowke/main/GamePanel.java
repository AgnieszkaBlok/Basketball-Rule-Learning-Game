package io.github.agnieszkablok.poznajkoszykowke.main;

import io.github.agnieszkablok.poznajkoszykowke.entity.Player;
import io.github.agnieszkablok.poznajkoszykowke.items.Item;
import io.github.agnieszkablok.poznajkoszykowke.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;

public class GamePanel extends JPanel implements  Runnable{

    // Screen Settings
    static final int ORIGINAL_TILE_SIZE = 16; //16x16 tile
    static final int SCALE = 3;

    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; //48x48
    public static final int MAX_SCREEN_COL =16;  //kolumny
    public static final int MAX_SCREEN_ROW =12; //wiersze
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; //768
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; //576

    // WORLD SETTINGS
    public static final int MAX_WORLD_COL = 50;
    public static final int MAX_WORLD_ROW = 50;

    //FPS
    public static final int FPS =60;

    public static final double DRAW_INTERVAL = 1000000000.0 / FPS;




    TileManager tileM = new TileManager(this);

    KeyHandler keyH =new KeyHandler();
    public Ui ui = new Ui(this);
    Thread gameThread; //program zaczyna dopoki nie zatrzymama

    public CollisionChecker cChecker = new CollisionChecker(this);

     public Player player = new Player(this, keyH);

     public List<Item> items;


    public Item getItemAt(int index){
        return items.get(index);
    }

    public void deleteItemAt(int index){
        items.set(index, null);
    }

    public int getItemsCnt(){
        return items.size();
    }


    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); //panel size of the game panel
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);// better rendering
        this.addKeyListener(keyH);
        items = new ArrayList<>();
        this.setFocusable(true);
    }

    public void setupGame(){
        try{
            items = AssetSetter.getItemsFromFile("res/items_locations/locations.loc");
        } catch (FileNotFoundException e){
            System.out.println("Could not find file with items locations!");
        }
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();//wywolujw metode run
    }

    @Override

    public void run(){
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/ DRAW_INTERVAL;

            lastTime =currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta --;
            }
        }
    }

    public void update(){
        player.update();

    }
    public void paintComponent(Graphics g){ //standard method to draw on jpanel
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        //tile
        tileM.draw(g2);

        //object
        for(int i =0; i<getItemsCnt(); i++){
            if(getItemAt(i) != null){
                getItemAt(i).draw(g2, this);
            }
        }
        //player

        player.draw(g2);

        // UI
        ui.draw(g2);

        g2.dispose();//??? 8.05 film 2



    }
}
