package src;

import src.tile.GiftSpawner;

public class TeleportCheck {
    GamePanel gp;

    public TeleportCheck(GamePanel gp) {
        this.gp = gp;
    }
    public int tileNum1, tileNum2;
    
    public void checkTile(Characters entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;


        int tileNum1 = gp.tileM.mapTileNumber[entityLeftWorldX/48][entityTopWorldY/48];
        int tileNum2 = gp.tileM.mapTileNumber[entityLeftWorldX/48][entityBottomWorldY/48];
        int tileNum3 = gp.tileM.mapTileNumber[entityRightWorldX/48][entityTopWorldY/48];
        int tileNum4 = gp.tileM.mapTileNumber[entityRightWorldX/48][entityBottomWorldY/48];
        
        if (gp.tileM.tile[tileNum1].teleport == true || gp.tileM.tile[tileNum2].teleport == true|| gp.tileM.tile[tileNum3].teleport == true|| gp.tileM.tile[tileNum4].teleport == true) {
            entity.teleportOn = true;
        }     
    }

    public void checkTileCloset(Player entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int tileNum1 = gp.tileM.mapTileNumber[entityLeftWorldX/48][entityTopWorldY/48];
        int tileNum2 = gp.tileM.mapTileNumber[entityRightWorldX/48][entityTopWorldY/48];

        if (gp.tileM.tile[tileNum1].closet == true || gp.tileM.tile[tileNum2].closet == true) {
            entity.closetAv = true;
            System.out.println("TRUE");
        }    
    }

    public boolean letterCheck(Player entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int tileNum1 = gp.tileM.mapTileNumber[entityLeftWorldX/48][entityTopWorldY/48];
        int tileNum2 = gp.tileM.mapTileNumber[entityRightWorldX/48][entityTopWorldY/48];

        if (gp.tileM.tile[tileNum1].letter == true || gp.tileM.tile[tileNum2].letter == true) {
            return true;
        }   
        else return false; 
    }
}


