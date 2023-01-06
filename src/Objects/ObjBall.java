package Objects;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class ObjBall extends SuperObjects{

    public ObjBall(){

        name ="Ball";
        try{
            image = ImageIO.read(new FileInputStream("res/objects/ball.png"));



        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
