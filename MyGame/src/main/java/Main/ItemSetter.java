package Main;

import Items.Bone;
import Items.ChestItem;
import Items.KeyItem;

import java.security.Key;

public class ItemSetter {


    GamePanel gp;

    public ItemSetter(GamePanel gp){
        this.gp=gp;
    }


    public void setItem(){
        gp.collectibles[0]=new KeyItem();
        gp.collectibles[0].worldX=10*gp.TILE_SIZE;
        gp.collectibles[0].worldY=47*gp.TILE_SIZE;

        gp.collectibles[1]=new ChestItem();
        gp.collectibles[1].worldX=18*gp.TILE_SIZE;
        gp.collectibles[1].worldY=4*gp.TILE_SIZE;

        gp.collectibles[2]=new Bone();
        gp.collectibles[2].worldX=40*gp.TILE_SIZE;
        gp.collectibles[2].worldY=4*gp.TILE_SIZE;








    }

}
