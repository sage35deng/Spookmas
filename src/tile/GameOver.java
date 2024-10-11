package src.tile;

import src.Player;
import src.GamePanel;
import src.KeyHandler;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;

public class GameOver {
    BufferedImage gameOver;
    GamePanel gp;

    public GameOver(GamePanel gp) {
        this.gp = gp;
        getImage();
    }

    public void getImage() {
        try {
            // Load images
            gameOver = ImageIO.read(getClass().getResourceAsStream("/src/pictures/GAME_OVER.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(gameOver, 0 ,
                0 , 24 * gp.tileSize, 16 * gp.tileSize, null);
    }

}
