package Objects;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class ObjDoor extends SuperObjects {


        public ObjDoor() {

            name = "Door";
            try {
                image = ImageIO.read(new FileInputStream("res/objects/door.png"));


            } catch (IOException e) {
                e.printStackTrace();
            }
            collision = true;


        }
    }

