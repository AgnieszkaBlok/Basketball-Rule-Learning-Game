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
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
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

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();//czyta linie tekstu, tylko string przyjmuje

                while(col < gp.maxWorldCol){
                    String[] numbers = line.split(" ") ;//podzial wyrazu stringa wokol dopasowan wyrazu
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == gp.maxWorldCol){
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


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum [worldCol] [worldRow];
            /// epizot 5 15:30 tlumaczy
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&  // zeby wyswietlaly sie tylko te z najblizszego otoczenia a nie cala mapa
                    worldX -gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize>gp.player.worldY -gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;


            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow ++;

            }

        }
    }
}
