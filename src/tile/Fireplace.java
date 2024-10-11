package src.tile;

import src.Player;
import src.GamePanel;
import src.KeyHandler;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;

public class Fireplace {
    GamePanel gp;
    public int fireplaceTimer;
    public KeyHandler key;
    public BufferedImage fire1, fire2;
    public boolean fireOut = false;

    public Fireplace(GamePanel gp, KeyHandler key) {
        this.gp = gp;
        this.key = key;
        fireplaceTimer = 60;
        getFireImage();
    }

    public int sec = 0;

    public void update(Player player) {
        if (fireplaceTimer <= 0) {
            fireOut = true;
        }
        if (gp.gSpawn.spawns > 0) {
            if (infrontFire(player) == true) {
                if (key.interactPressed == true) {
                    System.out.println("HERE");
                    System.out.println("PRESSED");
                    if (fireOut == false) {
                        fireplaceTimer += player.inv * 2;
                        if (player.inv > 0)
                            gp.playSEffect(3);// play light noise

                    }

                    System.out.println("amnt" + player.inv);
                    player.inv = 0;
                }
            }

            if (sec < gp.sec && gp.tutorial == false && fireOut == false) {
                fireplaceTimer -= 1;
                System.out.println("FIREPLACE: " + fireplaceTimer);
            }

            sec = gp.sec;
        }
    }

    void getFireImage() {
        // Load the player images
        try {
            // Load images
            fire1 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/fire1.png"));
            fire2 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/fire2.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            // CATCH ERRORS
            e.printStackTrace();
        }
    }

    public boolean infrontFire(Player entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        if ((entityLeftWorldX / 48 == 21 && entityTopWorldY / 48 == 13)
                || (entityRightWorldX / 48 == 21 && entityTopWorldY / 48 == 13)
                || (entityLeftWorldX / 48 == 21 && entityTopWorldY / 48 == 12)
                || (entityLeftWorldX / 48 == 23 && entityTopWorldY / 48 == 12)
                || (entityLeftWorldX / 48 == 22 && entityTopWorldY / 48 == 12)) {
            return true;
        } else
            return false;
    }

    public void draw(Graphics2D g2) {
        if (gp.tileM.currentMap.equals("downStairs") && fireplaceTimer > 0) {
            if (gp.sec % 2 == 0) {
                g2.drawImage(fire1, 22 * 48 - gp.player.worldX + gp.player.screenX - 5,
                        11 * 48 - gp.player.worldY + gp.player.screenY - 24, gp.tileSize + 12, gp.tileSize + 24, null);
                g2.drawImage(fire2, 21 * 48 - gp.player.worldX + gp.player.screenX + 5,
                        11 * 48 - gp.player.worldY + gp.player.screenY - 24, gp.tileSize + 12, gp.tileSize + 24, null);
            } else {
                g2.drawImage(fire2, 22 * 48 - gp.player.worldX + gp.player.screenX - 5,
                        11 * 48 - gp.player.worldY + gp.player.screenY - 24, gp.tileSize + 12, gp.tileSize + 24, null);
                g2.drawImage(fire1, 21 * 48 - gp.player.worldX + gp.player.screenX + 5,
                        11 * 48 - gp.player.worldY + gp.player.screenY - 24, gp.tileSize + 12, gp.tileSize + 24, null);
            }

        }

    }

}
