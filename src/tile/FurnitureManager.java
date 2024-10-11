package src.tile;

import src.GamePanel;

import java.awt.*;

import java.io.IOException;

import javax.imageio.ImageIO;

public class FurnitureManager {

    GamePanel gp;
    public Tile[] tile;
    public Rectangle solidArea;

    public FurnitureManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Furniture/COUCHES.png"));
            tile[1] = new Tile();
            tile [1].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Furniture/COUNTER.png"));
            tile[3] = new Tile();
            tile [3].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Furniture/DINING.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Furniture/KITCHEN_COUNTER_1.png"));
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {

        int screenX = 289* gp.tileSize - gp.player.worldX + gp.player.screenX;
        int screenY = 189* gp.tileSize - gp.player.worldY + gp.player.screenY;

        if(gp.tileM.currentMap.equals("downStairs")){
            if (gp.player.worldY <= 615 - gp.tileSize) {
                g2.drawImage(tile[0].image, 289 * gp.scale + gp.tileSize - gp.player.worldX + gp.player.screenX,
                        189 * gp.scale + gp.tileSize - gp.player.worldY + gp.player.screenY,
                        gp.scale * 97,
                        gp.scale * 52,
                        null);
            }
            if(gp.player.worldY<600 - gp.tileSize){
                g2.drawImage(tile[1].image, 24 * gp.scale + gp.tileSize - gp.player.worldX + gp.player.screenX,
                        179 * gp.scale + gp.tileSize - gp.player.worldY + gp.player.screenY,
                        gp.scale * (136-24),
                        gp.scale * (231-179),
                        null);
            }
            if(gp.player.worldY<1000 - gp.tileSize){
                g2.drawImage(tile[3].image, 80 * gp.scale + gp.tileSize - gp.player.worldX + gp.player.screenX,
                        294 * gp.scale + gp.tileSize - gp.player.worldY + gp.player.screenY,
                        gp.scale * (176-80),
                        gp.scale * (346-288),
                        null);
            }
            if(gp.player.worldY<432 - gp.tileSize){
                g2.drawImage(tile[4].image, 0 + gp.tileSize - gp.player.worldX + gp.player.screenX,
                        120 * gp.scale + gp.tileSize - gp.player.worldY + gp.player.screenY,
                        gp.scale * (128),
                        gp.scale * (159-119),
                        null);
            }
        }
            

        //}
    }
}
