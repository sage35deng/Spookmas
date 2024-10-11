package src.tile;

import java.awt.*;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import src.GamePanel;

public class ControlDisplay {

    GamePanel gp;
    public Tile[] tile;
    public BufferedImage wKey;
    public BufferedImage aKey;
    public BufferedImage sKey;
    public BufferedImage dKey;
    public BufferedImage shiftKey;
    public BufferedImage eKey;

    public BufferedImage wKeyGrey;
    public BufferedImage aKeyGrey;
    public BufferedImage sKeyGrey;
    public BufferedImage dKeyGrey;
    public BufferedImage shiftKeyGrey;
    public BufferedImage firePlaceBar;

    public BufferedImage eKeyHighlight;
    public BufferedImage tutorial;
    public BufferedImage flashlight;

    public Tile num[] = new Tile[10];
    public Tile time[] = new Tile[7];

    public ControlDisplay(GamePanel gp) {
        this.gp = gp;
        loadImg();
    }

    public void presentCounter(Graphics2D g2) {
        if (gp.player.inv != 0) {
            if (gp.player.inv < 10) {
                g2.drawImage(num[gp.player.inv].image, 1 * 48, 2 * 48 - 12, gp.tileSize, gp.tileSize, null);
            } else if (gp.player.inv == 10) {
                g2.drawImage(num[1].image, 1 * 48, 2 * 48 - 12, 2 * gp.tileSize, gp.tileSize, null);
                g2.drawImage(num[0].image, 2 * 48, 2 * 48 - 12, 2 * gp.tileSize, gp.tileSize, null);
            } else if (gp.player.inv > 10 && gp.player.inv < 100) {
                for (int i = 0; i < 10; i++) {
                    if (gp.player.inv - (10 * i) < 10) {

                        int invRem = gp.player.inv - i * 10;

                        g2.drawImage(num[i].image, 1 * 48, 2 * 48 - 12, gp.tileSize, gp.tileSize, null);
                        g2.drawImage(num[invRem].image, 2 * 48, 2 * 48 - 12, gp.tileSize, gp.tileSize,
                                null);
                        break;
                    }
                }
            } else if (gp.player.inv > 100) {
                for (int i = 1; i < 10; i++) {
                    if (gp.player.inv - (100 * i) < 100) {
                        g2.drawImage(num[i].image, 1 * 48, 2 * 48 - 12, gp.tileSize, gp.tileSize,
                                null);
                        int invRem = gp.player.inv - 100 * i;
                        if (invRem <= 10) {
                            if (invRem == 10) {
                                g2.drawImage(num[1].image, 2 * 48, 2 * 48 - 12, gp.tileSize, gp.tileSize,
                                        null);
                                g2.drawImage(num[0].image, 3 * 48, 2 * 48 - 12, gp.tileSize, gp.tileSize,
                                        null);
                            } else {
                                g2.drawImage(num[0].image, 2 * 48, 2 * 48 - 12, gp.tileSize, gp.tileSize,
                                        null);
                                g2.drawImage(num[invRem].image, 3 * 48, 2 * 48 - 12, gp.tileSize,
                                        gp.tileSize, null);
                            }
                        } else {
                            for (int r = 1; i < 10; r++) {
                                if (gp.player.inv - (10 * i) < 10) {
                                    g2.drawImage(num[r].image, 2 * 48, 2 * 48 - 12, gp.tileSize, gp.tileSize,
                                            null);
                                    g2.drawImage(num[invRem - (10 * r)].image, 3 * 48, 2 * 48 - 12, gp.tileSize,
                                            gp.tileSize, null);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
            }

        } else {
            g2.drawImage(num[0].image, 1 * 48, 2 * 48 - 12, 2 * gp.tileSize, gp.tileSize, null);
        }
    }

    public void loadImg() {
        try {
            wKey = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/Keys/wKey.png"));
            aKey = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/Keys/aKey.png"));
            sKey = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/Keys/sKey.png"));
            dKey = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/Keys/dKey.png"));
            shiftKey = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/Keys/shiftKey.png"));
            eKey = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/Keys/eKey.png"));
            eKeyHighlight = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/Keys/eKeyPressed.png"));

            wKeyGrey = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/Keys/KeyP/wGreyKey.png"));
            aKeyGrey = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/Keys/KeyP/aGreyKey.png"));
            sKeyGrey = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/Keys/KeyP/sGreyKey.png"));
            dKeyGrey = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/Keys/KeyP/dGreyKey.png"));

            shiftKeyGrey = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/Keys/shiftKey.png"));

            tutorial = ImageIO.read(getClass().getResourceAsStream("/src/pictures/letterOp.png"));

            num[0] = new Tile();
            num[1] = new Tile();
            num[2] = new Tile();
            num[3] = new Tile();
            num[4] = new Tile();
            num[5] = new Tile();
            num[6] = new Tile();
            num[7] = new Tile();
            num[8] = new Tile();
            num[9] = new Tile();

            time[0] = new Tile();
            time[1] = new Tile();
            time[2] = new Tile();
            time[3] = new Tile();
            time[4] = new Tile();
            time[5] = new Tile();
            time[6] = new Tile();

            time[0].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/12am.png"));
            time[1].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/1am.png"));
            time[2].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/2am.png"));
            time[3].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/3am.png"));
            time[4].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/4am.png"));
            time[5].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/5am.png"));
            time[6].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/6am.png"));

            // Hackathon 2022 (download this For
            // code)\Hackathon-main\src\pictures\Tiles\Nums\num0.png
            num[0].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/num0.png"));
            num[1].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/num1.png"));
            num[2].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/num2.png"));
            num[3].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/num3.png"));
            num[4].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/num4.png"));
            num[5].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/num5.png"));
            num[6].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/num6.png"));
            num[7].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/num7.png"));
            num[8].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/num8.png"));
            num[9].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Nums/num9.png"));



            flashlight = ImageIO.read(getClass().getResourceAsStream("/src/pictures/FLASHLIGHT.png"));

            firePlaceBar = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/BlueTile.png"));
            firePlaceBar = ImageIO.read(getClass().getResourceAsStream("/src/pictures/firePlaceBar.png"));
            // pic1 =
            // ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/Keys/eKeyPressed.png"));

        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {
        g2.drawImage(flashlight, 0, 0, gp.tileSize * 24, gp.tileSize * 16, null);
        if (gp.player.keyH.downPressed) {
            g2.drawImage(sKeyGrey, 21 * 48, 14 * 48 - 12, gp.tileSize, gp.tileSize, null);
        } else
            g2.drawImage(sKey, 21 * 48, 14 * 48 - 12, gp.tileSize, gp.tileSize, null);
        if (gp.player.keyH.upPressed) {
            g2.drawImage(wKeyGrey, 21 * 48, 13 * 48 - 12, gp.tileSize, gp.tileSize, null);
        } else
            g2.drawImage(wKey, 21 * 48, 13 * 48 - 12, gp.tileSize, gp.tileSize, null);
        if (gp.player.keyH.leftPressed) {
            g2.drawImage(aKeyGrey, 20 * 48, 14 * 48 - 12, gp.tileSize, gp.tileSize, null);
        } else
            g2.drawImage(aKey, 20 * 48, 14 * 48 - 12, gp.tileSize, gp.tileSize, null);

        if (gp.player.keyH.rightPressed) {
            g2.drawImage(dKeyGrey, 22 * 48, 14 * 48 - 12, gp.tileSize, gp.tileSize, null);
        } else
            g2.drawImage(dKey, 22 * 48, 14 * 48 - 12, gp.tileSize, gp.tileSize, null);

        g2.drawImage(shiftKey, 1 * 48, 14 * 48 - 12, 2 * gp.tileSize, gp.tileSize, null);

        gp.tCheck.checkTileCloset(gp.player);
        if (gp.player.closetAv == true || gp.fPlace.infrontFire(gp.player)) {
            g2.drawImage(eKeyHighlight, 22 * 48, 13 * 48 - 12, gp.tileSize, gp.tileSize, null);
        } else
            g2.drawImage(eKey, 22 * 48, 13 * 48 - 12, gp.tileSize, gp.tileSize, null);

        presentCounter(g2);

        if (gp.gSpawn.spawns == 0) {
            g2.drawImage(time[0].image, 20 * 48, 3 * 48 - 12, 4 * gp.tileSize, gp.tileSize, null);
        }
        if (gp.gSpawn.spawns == 1) {
            g2.drawImage(time[1].image, 20 * 48, 3 * 48 - 12, 4 * gp.tileSize, gp.tileSize, null);
        }
        if (gp.gSpawn.spawns == 2) {
            g2.drawImage(time[2].image, 20 * 48, 3 * 48 - 12, 4 * gp.tileSize, gp.tileSize, null);
        }
        if (gp.gSpawn.spawns == 3) {
            g2.drawImage(time[3].image, 20 * 48, 3 * 48 - 12, 4 * gp.tileSize, gp.tileSize, null);
        }
        if (gp.gSpawn.spawns == 4) {
            g2.drawImage(time[4].image, 20 * 48, 3 * 48 - 12, 4 * gp.tileSize, gp.tileSize, null);
        }

        g2.drawImage(gp.player.staminaBar, gp.player.screenX - 9 * gp.tileSize, gp.player.screenY - 6 * gp.tileSize,
                900 * gp.player.stamina / gp.player.maxStam, 5, null);

        g2.drawImage(firePlaceBar, gp.player.screenX - 9 * gp.tileSize, gp.player.screenY - 4 * gp.tileSize,
                900 * gp.fPlace.fireplaceTimer / 120, 5, null);
    }

    public void drawTut(Graphics2D g2){
        g2.drawImage(tutorial, 0,
                    0,  218*2, 235*2, null);
    }

}
