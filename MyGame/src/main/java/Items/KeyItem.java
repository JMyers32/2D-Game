package Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class KeyItem extends Items {


    public KeyItem(){
        name="Key";


        try{


            image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Key.png"));

        }catch(IOException e){
            System.out.println("The item could not load");
        }
    }






}
