package Controller;

import Model.Entity;
import View.GamePanel;

public class CollisionsChecker {
    GamePanel gp;

    public CollisionsChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile (Entity entity){
        int entityLeftX = entity.worldX + entity.collisionArea.x;
        int entityRightX = entity.worldX + entity.collisionArea.x + entity.collisionArea.width;
        int entityTopY = entity.worldY + entity.collisionArea.y;
        int entityBottomY = entity.worldY + entity.collisionArea.y + entity.collisionArea.height;

        int entityLeftCol = entityLeftX/gp.tileSize;
        int entityRightCol = entityRightX/gp.tileSize;
        int entityTopRow = entityTopY/gp.tileSize;
        int entityBottomRow = entityBottomY/gp.tileSize;

        int checkTile1, checkTile2;

        switch(entity.getDirection()){
            case "up":
                entityTopRow = (entityTopY-entity.speed)/gp.tileSize;
                checkTile1 = gp.landDraw.landTileNumber[entityLeftCol][entityTopRow];
                checkTile2 = gp.landDraw.landTileNumber[entityRightCol][entityTopRow];
                if (gp.landDraw.land[checkTile1].collision == true || gp.landDraw.land[checkTile2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY+entity.speed)/gp.tileSize;
                checkTile1 = gp.landDraw.landTileNumber[entityLeftCol][entityBottomRow];
                checkTile2 = gp.landDraw.landTileNumber[entityRightCol][entityBottomRow];
                if (gp.landDraw.land[checkTile1].collision == true || gp.landDraw.land[checkTile2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX+entity.speed)/gp.tileSize;
                checkTile1 = gp.landDraw.landTileNumber[entityRightCol][entityTopRow];
                checkTile2 = gp.landDraw.landTileNumber[entityRightCol][entityBottomRow];
                if (gp.landDraw.land[checkTile1].collision == true || gp.landDraw.land[checkTile2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX-entity.speed)/gp.tileSize;
                checkTile1 = gp.landDraw.landTileNumber[entityLeftCol][entityTopRow];
                checkTile2 = gp.landDraw.landTileNumber[entityLeftCol][entityBottomRow];
                if (gp.landDraw.land[checkTile1].collision == true || gp.landDraw.land[checkTile2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
