package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int originalTileSize = 32; // 32x32 tile
    final int scale = 2;

    public final int tileSize = originalTileSize * scale;  // 64x64 tile
    public final int maxScreenCol = 20;  // ???
    public final int maxScreenRow = 12;  // ???
    public final int screenWidth = tileSize * maxScreenCol; // 1280
    public final int screenHeight = tileSize * maxScreenRow; // 768

    // WORLD SETTINGS
    public final int maxWorldCol = 59;
    public final int maxWorldRow = 37;

    // FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    // FPS
    int FPS = 60; //60

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this,keyH);
    public SuperObject[] obj = new SuperObject[10];

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int optionsState = 5;



    public  GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        // SingleMap Objects //
        // aSetter.setObject_Key(0, 9, 4);
        // aSetter.setObject_Door(1, 10, 10);
        // aSetter.setObject_Door(2, 11, 10);
        // aSetter.setObject_Chest(3, 12, 3);
        // aSetter.setObject_Bone_book(4, 12, 4);

        // WorldMap Objects //
        aSetter.setObject_Key(0, 9, 30);
        aSetter.setObject_Key(5, 49, 30);
        aSetter.setObject_Door(1, 9, 12);
        aSetter.setObject_Door(2, 19, 6);
        aSetter.setObject_Chest(3, 29, 6);
        aSetter.setObject_Bone_book(4, 41, 35);

        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        //setFullScreen();

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setFullScreen() {

        // GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        // GET FULL SCREEN WIDTH AND HEIGHT
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                // 1 UPDATE: update information such as character position
                update();
                // 2 DRAW: draw the screen with the updated information

                //repaint();
                drawToTempScreen();
                drawToScreen();

                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                //System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }


        }

    }
    public void update() {

        if (gameState == playState) {
            player.update();
        }
        if (gameState == pauseState) {
            // nothing
        }


    }

    public void drawToTempScreen() {

        // DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN
        if(gameState == titleState) {
            ui.draw(g2);
        }
        // OTHERS
        else {
            // TILE
            // tileM.drawSingleMap(g2);
            tileM.drawWorldMap(g2);

            // OBJECT
            for(int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }

            // PLAYER
            player.draw(g2);

            // UI
            ui.draw(g2);
        }

        if(keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }
    }

    public void drawToScreen() {

        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }
    public void paintComponent (Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN
        if(gameState == titleState) {
            ui.draw(g2);
        }
        // OTHERS
        else {
            // TILE
            // tileM.drawSingleMap(g2);
            tileM.drawWorldMap(g2);

            // OBJECT
            for(int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }

            // PLAYER
            player.draw(g2);

            // UI
            ui.draw(g2);
        }

        if(keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

        g2.dispose();
    }

    public void playMusic(int i) {

        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {

        music.stop();
    }
    public void playSE(int i) {

        se.setFile(i);
        se.play();
    }

}
