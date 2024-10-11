package src.tile;

import java.awt.Rectangle;
import java.io.IOException;


import javax.imageio.ImageIO;

import src.GamePanel;

public class Couch {

    GamePanel gp;

    //public int couchX = 289 * 3;

    //Multiply by 3 to get fit scale or gp.scale
    public int couchX = 292 * 3;
    public int couchY = 189 * 3;

    public int couchWidth = 97 * 3;
    public int couchHeight = 52 * 3;
    public Tile[] tile;
    Rectangle solidArea;

    public Couch(GamePanel gp) {
        solidArea = new Rectangle(couchX, couchY, couchWidth,couchHeight);
    }

    public void getFurnitureImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Furniture/COUCHES.png"));// couch
            tile[0].collision = true;

        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
