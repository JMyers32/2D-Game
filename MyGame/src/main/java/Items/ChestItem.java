package Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ChestItem extends Items{

    public ChestItem(){
        name="Chest";


        try{


            image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Chest.png"));

        }catch(IOException e){
            System.out.println("The item could not load");
        }
        collision=true;
    }


}
