package io.github.agnieszkablok.poznajkoszykowke.tile;

import io.github.agnieszkablok.poznajkoszykowke.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;

    public int[][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];//liczba naszych rodzajow bloczkow
        mapTileNum = new int[GamePanel.MAX_WORLD_COL][GamePanel.MAX_WORLD_ROW];
        getTileImage();
        loadMap("res/maps/world01.txt");
    }

    private Tile readTile(String path, boolean collision) throws IOException{
        Tile tile = new Tile();
        tile.image = ImageIO.read(new FileInputStream(path));
        tile.collision = collision;
        return tile;
    }

    public void getTileImage(){
        try{
            tile[0] = readTile("res/tiles/grass.png", false);
            tile[1] = readTile("res/tiles/wall.png", true);
            tile[2] = readTile("res/tiles/water.png", true);
            tile[3] = readTile("res/tiles/earth.png", false);
            tile[4] = readTile("res/tiles/tree.png", true);
            tile[5] = readTile("res/tiles/sand.png", false);
        } catch(FileNotFoundException e){
            System.out.println("Couldn't load some of the tiles image!");
        }
        catch(IOException e){
            System.out.println("IOException while loading tiles!");
        }
    }

    public void loadMap(String filePath){

        try{
            FileReader fileReader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fileReader);
            int col = 0;
            int row = 0;

            while(col < GamePanel.MAX_WORLD_COL && row < GamePanel.MAX_WORLD_ROW){

                String line = br.readLine();//czyta linie tekstu, tylko string przyjmuje

                while(col < GamePanel.MAX_WORLD_COL){
                    String[] numbers = line.split(" ") ;//podzial wyrazu stringa wokol dopasowan wyrazu
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == GamePanel.MAX_WORLD_COL){
                    col = 0;
                    row++;

                }
            }
            br.close();
        }catch (FileNotFoundException e) {
            System.out.println("Couldn't read map from " + filePath);
        } catch (IOException e) {
            System.out.println("Other IOException while reading map!");
        }

    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < GamePanel.MAX_WORLD_COL && worldRow < GamePanel.MAX_WORLD_ROW){

            int tileNum = mapTileNum [worldCol] [worldRow];
            /// epizot 5 15:30 tlumaczy
            int worldX = worldCol * GamePanel.TILE_SIZE;
            int worldY = worldRow * GamePanel.TILE_SIZE;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + GamePanel.TILE_SIZE > gp.player.worldX - gp.player.screenX &&  // zeby wyswietlaly sie tylko te z najblizszego otoczenia a nie cala mapa
                    worldX - GamePanel.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
                    worldY + GamePanel.TILE_SIZE >gp.player.worldY -gp.player.screenY &&
                    worldY - GamePanel.TILE_SIZE < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
            }
            worldCol++;


            if(worldCol == GamePanel.MAX_WORLD_COL){
                worldCol = 0;
                worldRow ++;

            }

        }
    }
}
