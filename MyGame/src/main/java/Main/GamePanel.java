package Main;

import Entity.Player;
import Items.Items;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {

        // Screen Settings

        final int ORIGINAL_TILE_SIZE=16;  //16 by 16 tile
        final int SCALE=3;

        public final int TILE_SIZE=ORIGINAL_TILE_SIZE*SCALE; //48x48 tile

        public final int MAX_SCREEN_COL=16;
        public final int MAX_SCREEN_ROW=12;
        public final int SCREEN_WIDTH=TILE_SIZE*MAX_SCREEN_COL; //768 pixels
        public final int SCREEN_HEIGHT=TILE_SIZE*MAX_SCREEN_ROW; //576 pixels

      //World Settings

    public final int MAX_WORLD_COL=50;
    public final int MAX_WORLD_ROW=50;


    int fps=60;

    TileManager tileM=new TileManager(this);

    KeyHandler keyH=new KeyHandler();



    Thread gameThread;

    public CollisionChecker cChecker=new CollisionChecker(this);

    public ItemSetter setter=new ItemSetter(this);

    public Player player=new Player(this,keyH);

    public Items collectibles[]=new Items[10];

    public UI ui=new UI(this);




        public GamePanel(){
            this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
            this.setBackground(Color.black);
            this.setDoubleBuffered(true);
            this.addKeyListener(keyH);
            this.setFocusable(true);


        }


        public void setupGame(){

            setter.setItem();

        }

        public void startGameThread(){
            gameThread=new Thread(this);
            gameThread.start();
        }

        @Override
    public void run() {

           double drawInterval=1000000000/fps;
           double delta=0;
           long lastTime=System.nanoTime();
           long currentTime;

           while(gameThread!=null){
                currentTime=System.nanoTime();

                delta+=(currentTime-lastTime)/drawInterval;

                lastTime=currentTime;

                if(delta>=1) {
                    update();
                    repaint();
                    delta--;
                }
           }

    }
    public void update(){
           player.update();

    }
    public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2=(Graphics2D)g;
            tileM.draw(g2);
            for(int i=0;i<collectibles.length;i++){
                if(collectibles[i]!=null){
                    collectibles[i].draw(g2,this);
                }
            }

           player.Draw(g2);
            ui.draw(g2);
            g2.dispose();

    }
}

