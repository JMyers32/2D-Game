package tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public tile[] tile;
    public int mapTileNumber[] [];

    public TileManager(GamePanel gp){
        this.gp=gp;
        tile=new tile[15];
        mapTileNumber=new int[gp.MAX_WORLD_COL][gp.MAX_WORLD_ROW];
        getTileImage();
        loadMap("maps/BigWorldMap.txt");

    }

    public void getTileImage(){

        try{

            tile[0]=new tile();
            tile[0].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/GrassBackground.png"));
            tile[1]=new tile();
            tile[1].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/BrickHouse.png"));
            tile[1].collision=true;
            tile[2]=new tile();
            tile[2].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Fence.png"));
            tile[2].collision=true;

            tile[3]=new tile();
            tile[3].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/WoodFloor.png"));

            tile[4]=new tile();
            tile[4].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/IndoorWall.png"));
            tile[4].collision=true;

            tile[5]=new tile();
            tile[5].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Toilet.png"));
            tile[5].collision=true;

            tile[6]=new tile();
            tile[6].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/TopBed.png"));
            tile[6].collision=true;

            tile[7]=new tile();
            tile[7].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/BottomBed.png"));
            tile[7].collision=true;

            tile[8]=new tile();
            tile[8].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/PC.png"));
            tile[8].collision=true;

            tile[9]=new tile();
            tile[9].image= ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/CouchGrey.png"));
            tile[9].collision=true;




        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        try {

            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;

            while(col<gp.MAX_WORLD_COL&&row<gp.MAX_WORLD_ROW){
                String line=br.readLine();

                while(col<gp.MAX_WORLD_COL){

                    String numbers[]=line.split(" ");

                    int num=Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row]=num;
                    col++;
                }
                if (col==gp.MAX_WORLD_COL){
                    col=0;
                    row++;
                }

            }
            br.close();

        } catch (Exception e) {
        }
    }
    public void draw(Graphics2D g2){

        int worldCol=0;
        int worldRow=0;


        while(worldCol<gp.MAX_WORLD_COL&&worldRow<gp.MAX_WORLD_ROW){
            int tileNum=mapTileNumber[worldCol][worldRow];

            int worldX=worldCol*gp.TILE_SIZE;
            int worldY=worldRow*gp.TILE_SIZE;
            int screenX=worldX-gp.player.worldX+gp.player.SCREEN_X;
            int screenY=worldY-gp.player.worldY+gp.player.SCREEN_Y;

            if (worldX+gp.TILE_SIZE>gp.player.worldX-gp.player.SCREEN_X&&worldX-gp.TILE_SIZE<gp.player.worldX+gp.player.SCREEN_X&&worldY+gp.TILE_SIZE>gp.player.worldY-gp.player.SCREEN_Y&&worldY-gp.TILE_SIZE<gp.player.worldY+gp.player.SCREEN_Y) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);

            }
            worldCol++;
            if(worldCol==gp.MAX_WORLD_COL) {
                worldCol = 0;

                worldRow++;
            }


        }

    }
}
