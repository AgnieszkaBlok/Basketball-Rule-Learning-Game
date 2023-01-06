package Main;

import Objects.ObjBall;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new ObjBall();
        gp.obj[0].worldX = 23* gp.tileSize;
        gp.obj[0].worldY = 7* gp.tileSize;



    }
}
