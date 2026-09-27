package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_greenBoots extends SuperObjects{

    // COSTRUTTORE DELLA CLASSE
    public OBJ_greenBoots(){
        name = "greenBoots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/greenBoots.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
