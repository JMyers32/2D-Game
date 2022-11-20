package Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Bone extends Items{


    public Bone(){
        name="Bone";


        try{


            image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Bone.png"));

        }catch(IOException e){
            System.out.println("The item could not load");
        }
    }






}


