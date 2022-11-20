package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int SCREEN_X;
    public final int SCREEN_Y;

    public int hasKey=0;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp=gp;
        this.keyH=keyH;
        SCREEN_X=(gp.SCREEN_WIDTH/2)-(gp.TILE_SIZE/2);
        SCREEN_Y=(gp.SCREEN_HEIGHT/2)-(gp.TILE_SIZE/2);
        solidArea=new Rectangle(8,10,20,20);
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){

        worldX=gp.TILE_SIZE*26;
        worldY=gp.TILE_SIZE*38;
        speed=6;
        direction="down";
    }
public void getPlayerImage(){

        try{
            up1= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Nova.png"));
            up2= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Nova.png"));
            down1= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Nova.png"));
            down2= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Nova.png"));
            left1= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Nova.png"));
            left2= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Nova.png"));
            right1= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Nova.png"));
            right2= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Player/Nova.png"));

        }catch(IOException e){
            e.printStackTrace();
        }

}
    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";

            } else if (keyH.downPressed) {
                direction = "down";

            } else if (keyH.leftPressed) {
                direction = "left";

            } else if (keyH.rightPressed) {
                direction = "right";

            }

            collisionOn=false;
            gp.cChecker.checkTile(this);
           int objIndex= gp.cChecker.checkObject(this,true);
           pickUpObject(objIndex);

            if (collisionOn==false){

                switch (direction){
                    case "up":
                        worldY-=speed;
                        break;
                    case "down":
                        worldY+=speed;
                        break;
                    case "left":
                        worldX-=speed;
                        break;
                    case "right":
                        worldX+=speed;
                        break;
                }
            }

            spriteCounter++;

            if (spriteCounter > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }
      public void pickUpObject(int index){
        if(index!=999){
            String objectName=gp.collectibles[index].name;

            switch (objectName){
                case "Key":
                    hasKey++;
                    gp.collectibles[index]=null;
                    gp.ui.showMessage("You obtained a key!");

                    break;
                case "Chest":
                    if(hasKey>0) {
                        gp.ui.gameFinished = true;
                    }
                    break;

                case "Bone":
                    speed+=4;
                    gp.collectibles[index]=null;
                    gp.ui.showMessage("Fuel!");
                    break;
            }

        }


      }

    public void Draw(Graphics2D g2){
        BufferedImage image=null;
        if(direction.equals("up")){
            image=up1;
            if (spriteNumber==1){
                image=up1;
            }
            if(spriteNumber==2){
                image=up2;
            }
        }
        if(direction.equals("down")){
            image=down1;
            if(spriteNumber==1){
                image=up1;
            }
            if(spriteNumber==2){
                image=down2;
            }
        }
        if(direction.equals("left")) {
            image=left1;
            if(spriteNumber==1){
                image=left1;
            }
            if(spriteNumber==2){
                image=left2;
            }
        }
        if(direction.equals("right")){
            image=right1;
            if(spriteNumber==1){
                image=right1;
            }
            if(spriteNumber==2){
                image=right2;
            }
        }
        g2.drawImage(image,SCREEN_X,SCREEN_Y,gp.TILE_SIZE,gp.TILE_SIZE,null);
    }

}
