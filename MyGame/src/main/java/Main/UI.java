package Main;

import Items.KeyItem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;

    BufferedImage keyImage;

    public boolean messageOn;
    public String message="";

    int messageCounter=0;

    public boolean gameFinished;

    double playTime;
    DecimalFormat dFormat=new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp=gp;
        arial_40=new Font("Arial",Font.PLAIN,40);
        arial_80B=new Font("Arial",Font.BOLD,80);
        KeyItem key=new KeyItem();
        keyImage=key.image;
    }

    public void showMessage(String text){
        message=text;
        messageOn=true;
    }

    public void draw(Graphics2D g2){


        if(gameFinished==true){
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text="You found the treasure!";
            textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
             x=gp.SCREEN_WIDTH/2-textLength/2;
             y=gp.SCREEN_HEIGHT/2-(gp.TILE_SIZE*3);
             g2.drawString(text,x,y);

            g2.setColor(Color.BLUE);
             text="Your play time is: "+dFormat.format(playTime)+"!";
            textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x=gp.SCREEN_WIDTH/2-textLength/2;
            y=gp.SCREEN_HEIGHT/2+(gp.TILE_SIZE*4);
            g2.drawString(text,x,y);

            g2.setFont(arial_80B);
            g2.setColor(Color.GREEN);
            text="Congratulations!";
            textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x=gp.SCREEN_WIDTH/2-textLength/2;
            y=gp.SCREEN_HEIGHT/2+(gp.TILE_SIZE*2);
            g2.drawString(text,x,y);

            gp.gameThread=null;


        }else {

            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.TILE_SIZE / 2, gp.TILE_SIZE / 2, gp.TILE_SIZE, gp.TILE_SIZE, null);
            g2.drawString("x " + gp.player.hasKey, 74, 65);

            playTime+=(double)1/60;
            g2.drawString("Time: "+dFormat.format(playTime),gp.TILE_SIZE*11,65);

            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.TILE_SIZE / 2, gp.TILE_SIZE * 5);
                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

    }
}
