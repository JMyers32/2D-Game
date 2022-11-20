package Main;

import Entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp=gp;
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX=entity.worldX+entity.solidArea.x;
        int entityRightWorldX=entity.worldX+entity.solidArea.x+entity.solidArea.width;
        int entityTopWorldY=entity.worldY+entity.solidArea.y;
        int entityBottomWorldY=entity.worldY+entity.solidArea.y+entity.solidArea.height;

        int entityLeftCol=entityLeftWorldX/gp.TILE_SIZE;
        int entityRightCol=entityRightWorldX/gp.TILE_SIZE;
        int entityTopRow=entityTopWorldY/gp.TILE_SIZE;
        int entityBottomRow=entityBottomWorldY/gp.TILE_SIZE;

        int tileNum1, tileNum2;

        switch (entity.direction){

            case "up":
                entityTopRow=(entityTopWorldY-entity.speed)/gp.TILE_SIZE;
                tileNum1=gp.tileM.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNumber[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision||gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn=true;
                }
                break;
            case "down":
                entityBottomRow=(entityBottomWorldY-entity.speed)/gp.TILE_SIZE;
                tileNum1=gp.tileM.mapTileNumber[entityLeftCol][entityBottomRow];
                tileNum2=gp.tileM.mapTileNumber[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision||gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn=true;
                }
                break;
            case "left":
                entityLeftCol=(entityLeftWorldX-entity.speed)/gp.TILE_SIZE;
                tileNum1=gp.tileM.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNumber[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision||gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn=true;
                }
                break;
            case "right":
                entityRightCol=(entityRightWorldX-entity.speed)/gp.TILE_SIZE;
                tileNum1=gp.tileM.mapTileNumber[entityRightCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNumber[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision||gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn=true;
                }
                break;

        }
    }

    public int checkObject(Entity entity, boolean player){

        int index=999;

        for(int i=0;i<gp.collectibles.length;i++){
              if(gp.collectibles[i]!=null){
                  entity.solidArea.x= entity.worldX+entity.solidArea.x;
                  entity.solidArea.y= entity.worldY+entity.solidArea.y;

                  gp.collectibles[i].solidArea.x=gp.collectibles[i].worldX+gp.collectibles[i].solidArea.x;
                  gp.collectibles[i].solidArea.y=gp.collectibles[i].worldY+gp.collectibles[i].solidArea.y;

                  switch(entity.direction){

                      case "up":
                          entity.solidArea.y-=entity.speed;
                          if(entity.solidArea.intersects(gp.collectibles[i].solidArea)){
                                if(gp.collectibles[i].collision==true){
                                    entity.collisionOn=true;
                                }
                                if(player==true){
                                    index=i;
                                }
                          }

                          break;
                      case "down":
                          entity.solidArea.y+=entity.speed;
                          if(entity.solidArea.intersects(gp.collectibles[i].solidArea)){
                              if(gp.collectibles[i].collision==true){
                                  entity.collisionOn=true;
                              }
                              if(player==true){
                                  index=i;
                              }
                          }
                          break;
                      case "left":
                          entity.solidArea.x-=entity.speed;
                          if(entity.solidArea.intersects(gp.collectibles[i].solidArea)){
                              if(gp.collectibles[i].collision==true){
                                  entity.collisionOn=true;
                              }
                              if(player==true){
                                  index=i;
                              }
                          }
                          break;
                      case "right":
                          entity.solidArea.x+=entity.speed;
                          if(entity.solidArea.intersects(gp.collectibles[i].solidArea)){
                              if(gp.collectibles[i].collision==true){
                                  entity.collisionOn=true;
                              }
                              if(player==true){
                                  index=i;
                              }
                          }
                          break;
                  }
                  entity.solidArea.x=entity.solidAreaDefaultX;
                  entity.solidArea.y=entity.solidAreaDefaultY;
                  gp.collectibles[i].solidArea.x=gp.collectibles[i].solidAreaDefaultX;
                  gp.collectibles[i].solidArea.y=gp.collectibles[i].getSolidAreaDefaultY;
              }

        }

        return index;


    }


}
