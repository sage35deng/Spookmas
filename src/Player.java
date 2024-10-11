package src;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

//Extends to the character file and we import the info from there
public class Player extends Characters {

    // Call the other classes and set them as variable??
    public KeyHandler keyH;

    final public int screenX;
    final public int screenY;

    public boolean closetTrue;

    public int maxStam = 250;
    public int stamina = maxStam;
    public BufferedImage staminaBar;
    public boolean closetAv = false;
    public boolean hiding = false;
    public int inv = 0;
    public int closetCD = 50;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize);

        // call the function setDefaultValues which calls the variables
        setDefaultValues();
        getPlayerImage();
        solidArea.x = 3 * gp.scale;
        solidArea.y = gp.tileSize + 6 * gp.scale;
        solidArea.width = gp.tileSize - (6 * gp.scale);
        solidArea.height = gp.tileSize - (6 * gp.scale);

    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 3;
        worldY = gp.tileSize * 7;
        speed = 2;
        setDirection = "down";
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize * 2);
    }
    int count = 0;
    public void getPlayerImage() {
        // Load the player images
        try {
            // Load images
            up1 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Sprite_Back_View/game_spriteback_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Sprite_Back_View/game_spriteback_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Sprite_Back_View/game_spriteback_3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Sprite_Back_View/game_spriteback_4.png"));
            staminaBar = ImageIO.read(getClass().getResourceAsStream("/src/pictures/Tiles/RedTile.png"));
            down1 = ImageIO
                    .read(getClass().getResourceAsStream("/src/pictures/Sprite_Front_View/game_spritefront_1.png"));
            down2 = ImageIO
                    .read(getClass().getResourceAsStream("/src/pictures/Sprite_Front_View/game_spritefront_2.png"));
            down3 = ImageIO
                    .read(getClass().getResourceAsStream("/src/pictures/Sprite_Front_View/game_spritefront_3.png"));
            down4 = ImageIO
                    .read(getClass().getResourceAsStream("/src/pictures/Sprite_Front_View/game_spritefront_4.png"));

            left1 = ImageIO
                    .read(getClass().getResourceAsStream("/src/pictures/Sprite_Left_View/game_spriteleft_1.png"));
            left2 = ImageIO
                    .read(getClass().getResourceAsStream("/src/pictures/Sprite_Left_View/game_spriteleft_2.png"));
            left3 = ImageIO
                    .read(getClass().getResourceAsStream("/src/pictures/Sprite_Left_View/game_spriteleft_3.png"));
            left4 = ImageIO
                    .read(getClass().getResourceAsStream("/src/pictures/Sprite_Left_View/game_spriteleft_4.png"));

            right1 = ImageIO
                    .read(getClass().getResourceAsStream("/src/pictures/Sprite_Right_View/game_spriteright_1.png"));
            right2 = ImageIO
                    .read(getClass().getResourceAsStream("/src/pictures/Sprite_Right_View/game_spriteright_2.png"));
            right3 = ImageIO
                    .read(getClass().getResourceAsStream("/src/pictures/Sprite_Right_View/game_spriteright_3.png"));
            right4 = ImageIO
                    .read(getClass().getResourceAsStream("/src/pictures/Sprite_Right_View/game_spriteright_4.png"));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            // CATCH ERRORS
            e.printStackTrace();
        }
    }

    // This is where we draw the character
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        if (hiding == false) {
            if (spriteNumber == 1) {
                switch (setDirection) {
                    case ("up"):
                        image = up1;
                        break;

                    case ("down"):

                        image = down1;
                        break;

                    case ("left"):
                        image = left1;
                        break;

                    case ("right"):
                        image = right1;
                        break;
                }
            }
            if (spriteNumber == 2) {
                switch (setDirection) {
                    case ("up"):
                        image = up2;
                        break;

                    case ("down"):
                        image = down2;
                        break;

                    case ("left"):
                        image = left2;
                        break;

                    case ("right"):
                        image = right2;
                        break;
                }
            }
            if (spriteNumber == 3) {
                switch (setDirection) {
                    case ("up"):
                        image = up3;
                        break;

                    case ("down"):
                        image = down3;
                        break;

                    case ("left"):
                        image = left3;
                        break;

                    case ("right"):
                        image = right3;
                        break;
                }
            }
            if (spriteNumber == 4) {
                switch (setDirection) {
                    case ("up"):
                        image = up4;
                        break;

                    case ("down"):
                        image = down4;
                        break;

                    case ("left"):
                        image = left4;
                        break;
                    case ("right"):
                        image = right4;
                        break;
                }
            }
            // Draws the pictures
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize * 2, null);
        }
        // draw stamina bar
        g2.drawImage(staminaBar, screenX - 9 * gp.tileSize, screenY - 6 * gp.tileSize, 900 * stamina / maxStam, 5,
                null);
    }

    // Updates the characters position
    public void update() {
        if (closetCD > 0)
            closetCD--;

        if (keyH.interactPressed) {
            gp.tCheck.checkTileCloset(this);
            if (closetAv == true && closetCD == 0 && hiding == false) {
                System.out.println("HIDE");
                // closet effect
                gp.playSEffect(0);
                closetAv = false;
                closetCD = 50;
                hiding = true;
                speed = 0;
            } else if (hiding == true && closetCD == 0) {
                // closet effect here
                gp.playSEffect(0);
                closetCD = 50;
                closetAv = false;
                hiding = false;
                speed = 2;
                setDirection = "down";
                if (keyH.shiftPressed && stamina > 0) {
                    stamina -= 1;
                    speed = 4;
                } else
                    speed = 2;
            }
        }
        closetAv = false;
        if (hiding == false) {
            if (keyH.shiftPressed && stamina > 0) {
                stamina -= 1;
                speed = 4;
            } else if (stamina < maxStam) {
                stamina += 1;
                speed = 2;
            } else
                speed = 2;

            if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
                // sprint check
                if (keyH.upPressed) {
                    setDirection = "up";

                }
                if (keyH.downPressed) {
                    setDirection = "down";

                }

                if (keyH.leftPressed) {
                    setDirection = "left";

                }

                if (keyH.rightPressed) {
                    setDirection = "right";

                }
                // check tile collision
                collisionOn = false;
                teleportOn = false;

                // gp.tCheck(this);
                gp.cChecker.checkTile(this, gp.couch);

                // if collision is false, player can move
                if (collisionOn == false) {
                    switch (setDirection) {
                        case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                        case "right":
                            worldX += speed;
                            break;
                    }
                }

                gp.tCheck.checkTile(this);

                if (teleportOn) {
                    if ((worldX + solidArea.x) / gp.tileSize == 47 && (worldY + solidArea.y) / gp.tileSize == 9) {
                        if (gp.tileM.currentMap.equals("downStairs")) {
                            if (gp.tileM.currentMap.equals("downStairs")) {
                                gp.tileM.currentMap = "upStairs";
                                gp.tileM.loadMapUpStairs();
                                worldX = 33 * gp.tileSize;
                                worldY = 7 * gp.tileSize;
                            }
                        }

                    } else if ((worldX + solidArea.x) / gp.tileSize == 48
                            && (worldY + solidArea.y) / gp.tileSize == 9) {
                        if (gp.tileM.currentMap.equals("downStairs")) {
                            if (gp.tileM.currentMap.equals("downStairs")) {
                                gp.tileM.currentMap = "upStairs";
                                gp.tileM.loadMapUpStairs();
                                worldX = 34 * gp.tileSize;
                                worldY = 7 * gp.tileSize;
                            }
                        }
                    }

                    // upstairs tpcheck
                    if ((worldX + solidArea.x) / gp.tileSize == 33 && (worldY + solidArea.y) / gp.tileSize == 9) {
                        if (gp.tileM.currentMap.equals("upStairs")) {
                            gp.tileM.currentMap = "downStairs";
                            gp.tileM.loadMapDownStairs();
                            worldX = 47 * gp.tileSize;
                            worldY = 10 * gp.tileSize;
                        }
                    } else if ((worldX + solidArea.x) / gp.tileSize == 34
                            && (worldY + solidArea.y) / gp.tileSize == 9) {
                        if (gp.tileM.currentMap.equals("upStairs")) {
                            gp.tileM.currentMap = "downStairs";
                            gp.tileM.loadMapDownStairs();
                            worldX = 48 * gp.tileSize;
                            worldY = 10 * gp.tileSize;
                        }
                    }

                    // check Left Room Parents room
                    if ((worldX + solidArea.x) / gp.tileSize == 5 && (worldY + solidArea.y) / gp.tileSize == 7) {
                        if (gp.tileM.currentMap.equals("upStairs")) {
                            gp.tileM.currentMap = "parentsRoom";
                            gp.tileM.loadMapParentsRoom();
                            worldX = 6 * gp.tileSize;
                            worldY = 9 * gp.tileSize;
                        }
                    } else if ((worldX + solidArea.x) / gp.tileSize == 6 && (worldY + solidArea.y) / gp.tileSize == 7) {
                        if (gp.tileM.currentMap.equals("upStairs")) {
                            gp.tileM.currentMap = "parentsRoom";
                            gp.tileM.loadMapParentsRoom();
                            worldX = 6 * gp.tileSize;
                            worldY = 9 * gp.tileSize;
                        }
                    }

                    // CHECK KIDS ROOM
                    if ((worldX + solidArea.x) / gp.tileSize == 12 && (worldY + solidArea.y) / gp.tileSize == 7) {
                        if (gp.tileM.currentMap.equals("upStairs")) {
                            gp.tileM.currentMap = ("kidsRoom");
                            gp.tileM.loadMapKidsRoom();
                            worldX = 5 * gp.tileSize;
                            worldY = 14 * gp.tileSize;
                        }
                    }

                    if ((worldX + solidArea.x) / gp.tileSize == 13 && (worldY + solidArea.y) / gp.tileSize == 7) {
                        // System.out.println("teleporting");
                        if (gp.tileM.currentMap.equals("upStairs")) {
                            gp.tileM.currentMap = ("kidsRoom");
                            gp.tileM.loadMapKidsRoom();
                            worldX = 5 * gp.tileSize;
                            worldY = 14 * gp.tileSize;
                        }
                    }

                    // Leave kids room and go back to upstairs (RIGHT DOOR)
                    if ((worldX + solidArea.x) / gp.tileSize == 5 && (worldY + solidArea.y) / gp.tileSize == 16) {
                        if (gp.tileM.currentMap.equals("kidsRoom")) {
                            gp.tileM.currentMap = "upStairs";
                            gp.tileM.loadMapUpStairs();
                            worldX = 13 * gp.tileSize;
                            worldY = 7 * gp.tileSize;
                        }
                    }

                    if ((worldX + solidArea.x) / gp.tileSize == 4 && (worldY + solidArea.y) / gp.tileSize == 16) {
                        System.out.println("Teleporting");
                        if (gp.tileM.currentMap.equals("kidsRoom")) {
                            gp.tileM.currentMap = "upStairs";
                            gp.tileM.loadMapUpStairs();
                            worldX = 13 * gp.tileSize;
                            worldY = 7 * gp.tileSize;
                        }
                    }
                    if ((worldX + solidArea.x) / gp.tileSize == 6 && (worldY + solidArea.y) / gp.tileSize == 16) {
                        if (gp.tileM.currentMap.equals("kidsRoom")) {
                            gp.tileM.currentMap = "upStairs";
                            gp.tileM.loadMapUpStairs();
                            worldX = 13 * gp.tileSize;
                            worldY = 8 * gp.tileSize;
                        }
                    }

                    // leave Parents Room
                    if ((worldX + solidArea.x) / gp.tileSize == 5 && (worldY + solidArea.y) / gp.tileSize == 11) {
                        if (gp.tileM.currentMap.equals("parentsRoom")) {
                            gp.tileM.currentMap = "upStairs";
                            gp.tileM.loadMapUpStairs();
                            worldX = 5 * gp.tileSize;
                            worldY = 7 * gp.tileSize;
                        }
                    }
                    if ((worldX + solidArea.x) / gp.tileSize == 6 && (worldY + solidArea.y) / gp.tileSize == 11) {
                        if (gp.tileM.currentMap.equals("parentsRoom")) {
                            gp.tileM.currentMap = "upStairs";
                            gp.tileM.loadMapUpStairs();
                            worldX = 5 * gp.tileSize;
                            worldY = 7 * gp.tileSize;
                        }
                    }
                    if ((worldX + solidArea.x) / gp.tileSize == 7 && (worldY + solidArea.y) / gp.tileSize == 11) {
                        if (gp.tileM.currentMap.equals("parentsRoom")) {
                            gp.tileM.currentMap = "upStairs";
                            gp.tileM.loadMapUpStairs();
                            worldX = 5 * gp.tileSize;
                            worldY = 7 * gp.tileSize;
                        }
                    }

                }

                spriteCounter++;

                if (spriteCounter > 10) {
                    if (spriteNumber == 1) {
                        spriteNumber = 2;
                    } else if (spriteNumber == 2) {
                        spriteNumber = 3;
                    } else if (spriteNumber == 3) {
                        spriteNumber = 4;
                    } else if (spriteNumber == 4) {
                        spriteNumber = 1;
                    }
                    spriteCounter = 0;
                }
            } else {
                spriteNumber = 1;
            }
        } else if (stamina < maxStam) {
            stamina += 1;
            speed = 2;
        }

    }
}
