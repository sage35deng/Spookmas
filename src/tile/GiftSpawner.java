package src.tile;

import java.io.IOException;
import java.awt.*;

import java.util.Random;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import src.GamePanel;
import src.Player;

public class GiftSpawner {

    public Tile[] tile;
    public Gift gift;// to store var (useless)
    ArrayList<Gift> giftsKidsTemp = new ArrayList<Gift>();// array list to store possible gifts
    ArrayList<Gift> giftsParentTemp = new ArrayList<Gift>();// array list to store possible gifts
    ArrayList<Gift> giftsUpTemp = new ArrayList<Gift>();// array list to store possible gifts
    ArrayList<Gift> giftsDownTemp = new ArrayList<Gift>();// array list to store possible gifts

    ArrayList<Gift> giftsKids = new ArrayList<Gift>();// array list to store possible gifts
    ArrayList<Gift> giftsParent = new ArrayList<Gift>();// array list to store possible gifts
    ArrayList<Gift> giftsUp = new ArrayList<Gift>();// array list to store possible gifts
    ArrayList<Gift> giftsDown = new ArrayList<Gift>();// array list to store possible gifts
    public int mapTileNumber[][];
    public String currentMap;
    public GamePanel gp;

    public int idKids = 0;
    public int idParents = 0;
    public int idUp = 0;
    public int idDown = 0;

    int start = 0;
    public int spawns = 0;

    private static int rng(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public GiftSpawner(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[1];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/PRESENT.png"));// blue =
                                                                                                            // backtround

        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    // spawn 40 first hour
    // kids 1-5
    // parents 1-5
    // upStair 15
    // downStair rem
    // spawn 36 second hour
    // kids 1-2
    // parents 1-2
    // upStair 15
    // downStair rem
    // spawn 32 third hour
    // kids 1
    // parents 1
    // upStair 10
    // downStair rem
    // spawn 12 fourth hour
    // up 1-6
    // down rem
    // spawn 4 fifth hour
    // down 4

    // kids room gets

    public void LoadKidsRoomGifts(int amnt, int hour) {
        giftsKidsTemp = new ArrayList<Gift>();
        idKids = 0;
        for (int i = 4; i <= 7; i++) {
            giftsKidsTemp.add(gift = new Gift(i * 48, 6 * 48));
            idKids++;
        }
        for (int i = 3; i <= 7; i++) {
            giftsKidsTemp.add(gift = new Gift(i * 48, 7 * 48));
            idKids++;
        }
        for (int i = 3; i <= 8; i++) {
            giftsKidsTemp.add(gift = new Gift(i * 48, 8 * 48));
            idKids++;
        }
        for (int i = 3; i <= 9; i++) {
            giftsKidsTemp.add(gift = new Gift(i * 48, 9 * 48));
            idKids++;
        }
        for (int r = 10; r <= 12; r++) {
            giftsKidsTemp.add(gift = new Gift(4 * 48, r * 48));
            idKids++;
        }

        for (int r = 10; r <= 12; r++) {
            giftsKidsTemp.add(gift = new Gift(5 * 48, r * 48));
            idKids++;
        }

        for (int r = 10; r <= 12; r++) {
            giftsKidsTemp.add(gift = new Gift(6 * 48, r * 48));
            idKids++;
        }

        for (int x = 0; x <= amnt; x++) {
            int rand = rng(start, idKids);
            giftsKids.add(giftsKidsTemp.get(rand));
            giftsKidsTemp.remove(rand);
            idKids--;
        }
    }

    public void LoadParentsRoomGifts(int amnt, int hour) {
        giftsParentTemp = new ArrayList<Gift>();
        idParents = 0;
        for (int i = 1; i <= 11; i++) {
            giftsParentTemp.add(gift = new Gift(i * 48, 8 * 48));
            idParents++;
        }
        for (int i = 1; i <= 4; i++) {
            giftsParentTemp.add(gift = new Gift(i * 48, 7 * 48));
            idParents++;
        }
        for (int i = 8; i <= 11; i++) {
            giftsParentTemp.add(gift = new Gift(i * 48, 7 * 48));
            idParents++;
        }
        for (int i = 8; i <= 11; i++) {
            giftsParentTemp.add(gift = new Gift(i * 48, 6 * 48));
            idParents++;
        }
        for (int i = 0; i <= amnt; i++) {
            int rand = rng(0, idParents);
            giftsParent.add(giftsParentTemp.get(rand));
            giftsParentTemp.remove(rand);
            idParents--;
        }
    }

    public void LoadUpStairsGifts(int amnt, int hour) {
        giftsUpTemp = new ArrayList<Gift>();
        idUp = 0;
        for (int i = 1; i <= 31; i++) {
            giftsUpTemp.add(gift = new Gift(i * 48, 9 * 48));
            idUp++;
        }
        for (int i = 1; i <= 31; i++) {
            giftsUpTemp.add(gift = new Gift(i * 48, 10 * 48));
            idUp++;
        }
        for (int i = 1; i <= 31; i++) {
            giftsUpTemp.add(gift = new Gift(i * 48, 11 * 48));
            idUp++;
        }
        for (int i = 29; i <= 34; i++) {
            giftsUpTemp.add(gift = new Gift(i * 48, 5 * 48));
            idUp++;
        }
        for (int i = 25; i <= 34; i++) {
            giftsUpTemp.add(gift = new Gift(i * 48, 6 * 48));
            idUp++;
        }
        for (int i = 25; i <= 34; i++) {
            giftsUpTemp.add(gift = new Gift(i * 48, 7 * 48));
            idUp++;
        }
        for (int i = 0; i <= amnt; i++) {
            int rand = rng(0, idUp);
            giftsUp.add(giftsUpTemp.get(rand));
            giftsUpTemp.remove(rand);
            idUp--;
        }
    }

    public void LoadDownStairsGifts(int amnt, int hour) {
        for (int i = 33; i <= 47; i++) {
            giftsDownTemp.add(gift = new Gift(i * 48, 15 * 48));
            idDown++;
        }
        for (int i = 43; i <= 46; i++) {
            giftsDownTemp.add(gift = new Gift(i * 48, 11 * 48));
            idDown++;
        }
        for (int i = 43; i <= 46; i++) {
            giftsDownTemp.add(gift = new Gift(i * 48, 12 * 48));
            idDown++;
        }
        for (int i = 43; i <= 46; i++) {
            giftsDownTemp.add(gift = new Gift(i * 48, 13 * 48));
            idDown++;
        }
        for (int i = 43; i <= 46; i++) {
            giftsDownTemp.add(gift = new Gift(i * 48, 14 * 48));
            idDown++;
        }

        for (int i = 54; i <= 59; i++) {
            giftsDownTemp.add(gift = new Gift(i * 48, 13 * 48));
            idDown++;
        }
        for (int i = 54; i <= 59; i++) {
            giftsDownTemp.add(gift = new Gift(i * 48, 14 * 48));
            idDown++;
        }
        for (int i = 54; i <= 58; i++) {
            giftsDownTemp.add(gift = new Gift(i * 48, 15 * 48));
            idDown++;
        }
        for (int i = 45; i <= 58; i++) {
            giftsDownTemp.add(gift = new Gift(i * 48, 16 * 48));
            idDown++;
        }
        for (int i = 45; i <= 58; i++) {
            giftsDownTemp.add(gift = new Gift(i * 48, 17 * 48));
            idDown++;
        }

        for (int i = 16; i <= 18; i++) {
            for (int r = 12; r <= 15; r++) {
                giftsDownTemp.add(gift = new Gift(i * 48, r * 48));
                idDown++;
            }
        }

        for (int i = 2; i <= 14; i++) {
            giftsDownTemp.add(gift = new Gift(i * 48, 7 * 48));
            idDown++;
        }
        for (int i = 2; i <= 14; i++) {
            giftsDownTemp.add(gift = new Gift(i * 48, 8 * 48));
            idDown++;
        }

        for (int i = 1; i <= 14; i++) {
            giftsDownTemp.add(gift = new Gift(i * 48, 11 * 48));
            idDown++;
        }
        for (int i = 11; i <= 17; i++) {
            giftsDownTemp.add(gift = new Gift(1 * 48, i * 48));
            idDown++;
        }
        for (int i = 11; i <= 17; i++) {
            giftsDownTemp.add(gift = new Gift(2 * 48, i * 48));
            idDown++;
        }
        for (int i = 4; i <= 13; i++) {
            giftsDownTemp.add(gift = new Gift(i * 48, 18 * 48));
            idDown++;
        }
        for (int i = 1; i <= 30; i++) {
            giftsDownTemp.add(gift = new Gift(i * 48, 16 * 48));
            giftsDownTemp.add(gift = new Gift(i * 48, 17 * 48));
            idDown++;
        }
        for (int i = 4; i <= 13; i++) {
            giftsDownTemp.add(gift = new Gift(i * 48, 23 * 48));
            idDown++;
        }

        for (int i = 0; i <= amnt; i++) {
            int rand = rng(0, idDown);
            giftsDown.add(giftsDownTemp.get(rand));
            giftsDownTemp.remove(rand);
            idDown--;
        }
    }
    public boolean win = false;
    public void update() {
        giftCheck(gp.player);
        if (gp.minutesPassed >= 0 && spawns < 1) {
            spawns++;
            System.out.println("SPAWN1");
            int gifts = 40;
            int kidsAmnt = rng(1, 5);
            int parentsAmnt = rng(1, 5);
            int upStairsAmnt = 15;
            int downStairsAmnt = 40 - 15 - kidsAmnt - parentsAmnt;
            LoadKidsRoomGifts(kidsAmnt, gp.minutesPassed);
            LoadParentsRoomGifts(parentsAmnt, gp.minutesPassed);
            LoadUpStairsGifts(upStairsAmnt, gp.minutesPassed);
            LoadDownStairsGifts(downStairsAmnt, gp.minutesPassed);
            gp.playSEffect(8);
        }
        if (gp.minutesPassed >= 1 && spawns < 2) {
            spawns++;
            System.out.println("SPAWN2");
            int gifts = 36;
            int kidsAmnt = rng(1, 2);
            int parentsAmnt = rng(1, 2);
            int upStairsAmnt = 15;
            int downStairsAmnt = 36 - 15 - kidsAmnt - parentsAmnt;
            LoadKidsRoomGifts(kidsAmnt, gp.minutesPassed);
            LoadParentsRoomGifts(parentsAmnt, gp.minutesPassed);
            LoadUpStairsGifts(upStairsAmnt, gp.minutesPassed);
            LoadDownStairsGifts(downStairsAmnt, gp.minutesPassed);

            gp.playSEffect(8);
        }
        if (gp.minutesPassed >= 2 && spawns < 3) {
            spawns++;
            System.out.println("SPAWN3");
            int gifts = 32;
            int kidsAmnt = 1;
            int parentsAmnt = 1;
            int upStairsAmnt = 10;
            int downStairsAmnt = 40 - 15 - kidsAmnt - parentsAmnt;
            LoadKidsRoomGifts(kidsAmnt, gp.minutesPassed);
            LoadParentsRoomGifts(parentsAmnt, gp.minutesPassed);
            LoadUpStairsGifts(upStairsAmnt, gp.minutesPassed);
            LoadDownStairsGifts(downStairsAmnt, gp.minutesPassed);

            gp.playSEffect(8);
        }
        if (gp.minutesPassed >= 3 && spawns < 4) {
            spawns++;
            System.out.println("SPAWN4");
            int gifts = 12;
            int upStairsAmnt = rng(1, 6);
            int downStairsAmnt = 12 - upStairsAmnt;
            LoadUpStairsGifts(upStairsAmnt, gp.minutesPassed);
            LoadDownStairsGifts(downStairsAmnt, gp.minutesPassed);

            gp.playSEffect(8);
        }
        if (gp.minutesPassed >= 4 && spawns < 5) {
            spawns++;
            System.out.println("SPAWN5");
            int gifts = 5;
            int downStairsAmnt = 5;
            LoadDownStairsGifts(downStairsAmnt, gp.minutesPassed);

            gp.playSEffect(8);
        }

        if(giftsKids.size()==0 && giftsDown.size()==0&& giftsUp.size()==0&&giftsParent.size()==0&&spawns>=4){
            win=true;
        }

    }

    public void draw(Graphics2D g2) {
        // Automation for ground below
        if (spawns > 0) {
            if (gp.tileM.currentMap.equals("downStairs")) {
                for (int i = 0; i < giftsDown.size(); i++) {
                    int x = giftsDown.get(i).giftX;
                    int y = giftsDown.get(i).giftY;
                    g2.drawImage(tile[0].image, x - gp.player.worldX + gp.player.screenX,
                            y - gp.player.worldY + gp.player.screenY, gp.tileSize, gp.tileSize, null);
                }
            }

            else if (gp.tileM.currentMap.equals("upStairs")) {
                for (int i = 0; i < giftsUp.size(); i++) {
                    int x = giftsUp.get(i).giftX;
                    int y = giftsUp.get(i).giftY;
                    g2.drawImage(tile[0].image, x - gp.player.worldX + gp.player.screenX,
                            y - gp.player.worldY + gp.player.screenY, gp.tileSize, gp.tileSize, null);
                }
            }

            else if (gp.tileM.currentMap.equals("kidsRoom")) {
                for (int i = 0; i < giftsKids.size(); i++) {
                    int x = giftsKids.get(i).giftX;
                    int y = giftsKids.get(i).giftY;
                    g2.drawImage(tile[0].image, x - gp.player.worldX + gp.player.screenX,
                            y - gp.player.worldY + gp.player.screenY, gp.tileSize, gp.tileSize, null);
                }

            }

            else if (gp.tileM.currentMap.equals("parentsRoom")) {
                for (int i = 0; i < giftsParent.size(); i++) {
                    int x = giftsParent.get(i).giftX;
                    int y = giftsParent.get(i).giftY;
                    g2.drawImage(tile[0].image, x - gp.player.worldX + gp.player.screenX,
                            y - gp.player.worldY + gp.player.screenY, gp.tileSize, gp.tileSize, null);
                }
            }
        }
    }

    public void giftCheck(Player entity) {
        if (spawns > 0) {
            if (gp.tileM.currentMap.equals("downStairs")) {
                for (int i = 0; i < giftsDown.size(); i++) {
                    int x = giftsDown.get(i).giftX / 48;
                    int y = giftsDown.get(i).giftY / 48;
                    // System.out.println(x+ " "+y);
                    if ((entity.worldX + 24) / 48 == x && (entity.worldY + 72) / 48 == y) {
                        giftsDown.remove(i);
                        entity.inv++;
                    }
                }
            } else if (gp.tileM.currentMap.equals("upStairs")) {
                for (int i = 0; i < giftsUp.size(); i++) {
                    int x = giftsUp.get(i).giftX / 48;
                    int y = giftsUp.get(i).giftY / 48;
                    // System.out.println(x+ " "+y);
                    if ((entity.worldX + 24) / 48 == x && (entity.worldY + 72) / 48 == y) {
                        giftsUp.remove(i);
                        entity.inv++;
                    }
                }
            } else if (gp.tileM.currentMap.equals("kidsRoom")) {

                for (int i = 0; i < giftsKids.size(); i++) {
                    int x = giftsKids.get(i).giftX / 48;
                    int y = giftsKids.get(i).giftY / 48;
                    // System.out.print(("Player " + (entity.worldX+24)/gp.tileSize+"
                    // "+(entity.worldY+72)/48));
                    // System.out.println(" present "+x+ " "+y);
                    if ((entity.worldX + 24) / gp.tileSize == x && (entity.worldY + 72) / 48 == y) {
                        giftsKids.remove(i);
                        entity.inv++;
                    }
                }
            } else if (gp.tileM.currentMap.equals("parentsRoom")) {

                for (int i = 0; i < giftsParent.size(); i++) {
                    int x = giftsParent.get(i).giftX / 48;
                    int y = giftsParent.get(i).giftY / 48;

                    if ((entity.worldX + 24) / 48 == x && (entity.worldY + 72) / 48 == y) {
                        giftsParent.remove(i);
                        entity.inv++;
                    }
                }
            }
        }
    }

}
