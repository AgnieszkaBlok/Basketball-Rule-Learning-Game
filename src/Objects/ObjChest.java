package Objects;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class ObjChest extends SuperObjects{

    public ObjChest (){

        name = "Chest";
        try {
            image = ImageIO.read(new FileInputStream("res/objects/chest.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
