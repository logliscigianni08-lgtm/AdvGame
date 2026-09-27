package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_door extends SuperObjects{

    // COSTRUTTORE DELLA CLASSE
    public OBJ_door(){
        name = "door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
