package src;

//Provides all the visuals, buttons/controls, basically the actual game

//Imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import src.tile.*;
import java.io.IOException;

//If you don't know what a thread is it is basically

//JPanel allows us to get the methods to make the game

//Implements Runnable means we can multithread (basically means we can run multiple programs at once)
public class GamePanel extends JPanel implements Runnable {

    // Use the Thread class to set a game clock (FPS)
    // It allows us to start something and stop it if not stopped it will keep on
    // running until the entire program is stopped
    // We use a thread here because we want the fps run simultainiously run with the
    // program until it ends
    // We implement runnable to use the thread

    // Sage* Tile Size and Screen Size
    final int originalTileSize = 16; // 16x16 default tile size for characters
    public final int scale = 3; // Scale up the pixels

    public final int tileSize = originalTileSize * scale; /// 48x48 tiles that is scaled up for higher res
    public final int maxScreenRow = 24; // display is 24 tiles wide
    public final int maxScreenCol = 16; // display is 16 tiles high
    public final int screenWidth = tileSize * maxScreenRow; // screen displays 768 pixels
    public final int screenHeight = tileSize * maxScreenCol; // screen displays 576 pixels

    // world parameters (limits)

    public final int maxWorldCol = 61; // 59+2
    public final int maxWorldRow = 25; // 23+2

    public final int worldWidht = tileSize * maxWorldRow; // 59*16px
    public final int worldHeight = tileSize * maxWorldCol;// 23*16px
    public int minutesPassed = 0;
    public double timeEl = 0;
    public int sec = 0;
    int FPS = 60;

    public boolean tutorial = false;

    Thread gameThread;

    // Initialize KeyHandler function
    KeyHandler keyH = new KeyHandler();

    // create a new player
    public Player player = new Player(this, keyH);

    // Sanata
    // public Character npc[] = new Character[10];

    public TileManager tileM = new TileManager(this);
    public TileManager tileMtemp = new TileManager(this);

    public FurnitureManager FunM = new FurnitureManager(this);
    Sound sound = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);

    public TeleportCheck tCheck = new TeleportCheck(this);
    public Couch couch = new Couch(this);

    public GiftSpawner gSpawn = new GiftSpawner(this);
    public Fireplace fPlace = new Fireplace(this, keyH);
    public ControlDisplay controlDisplay = new ControlDisplay(this);
    public Sanata sanata = new Sanata(this);
    public GameOver gameOver = new GameOver(this);


    public GamePanel() {
        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH);

        this.setFocusable(true);
        requestFocus();
    }

    public void gameThreadStart() {

        // Instantiate a thread
        gameThread = new Thread(this);

        playMusic(7);

        // call the run function
        gameThread.start();
    }

    public void addNotify() {

        // call the superclass method addNotify and it connects the awt to the window so
        // like we can actually move stuff or somethign i think (idk)
        super.addNotify();

    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;

        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        


        while (gameThread != null && tutorial == false) {// code in this loop will always run repeat sorta like game
                                                         // ticks
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timeEl / 1000000000 >= 60) {
                if(read>0){
                    minutesPassed++;
                }
                System.out.println("minPassed");
                timeEl = 0;
            }

            if (timer >= 1000000000) {
                // System.out.println("FPS: " + drawCount);
                if(read>1){
                    sec++;
                }
                drawCount = 0;
                timeEl += timer;
                System.out.println(sec + "seconds");
                if (sec % 5 == 0) {
                    System.out.print((player.worldX + 24) / tileSize + " ");
                    System.out.println((player.worldY + 72) / tileSize);
                }
                timer = 0;
            }
        
        }
    }

    int spawn=0;
    boolean spawned = false;
    public void update() {
        player.update();
        if(read>0){
           gSpawn.update(); 
        }
        
        fPlace.update(player);
        if (fPlace.fireOut == true&&spawn<1) {
            sanata.defaultSanata();
            playSEffect(5);
            spawned=true;
            spawn++;
        }

        if(spawned==true){
            sanata.update();
        }
    }

    public BufferedImage background;
    public int death = 0;
    // built in method paintComponent
    // graphics class where

    int read =0;
    public void paintComponent(Graphics g) { // Graphics is a class that has many functions that allow us to draw
        // objects onto the screen
        super.paintComponent(g);// Super to make g our code

        Graphics2D g2 = (Graphics2D) g;

        // DRAW TILES BEFORE PLAYER
        tileM.draw(g2);
        gSpawn.draw(g2);
        
        fPlace.draw(g2);
        player.draw(g2);
        FunM.draw(g2);
        if(sanata.OnPath==true){
            sanata.draw(g2);
        }
        controlDisplay.draw(g2);
        if(tCheck.letterCheck(player)&&player.count==0){
            controlDisplay.drawTut(g2);
            read++;
        }
        if(sanata.death>0||gSpawn.win){
            gameOver.draw(g2);
        }
        // Get rid of the graphics and release any system resources that it is using
        g2.dispose();
        
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSEffect(int i) {
        sound.setFile(i);
        sound.play();
    }
}