package View;

import Controller.CollisionsChecker;
import Controller.KeyHandler;
import Model.LandDraw;
import Model.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen Settings
    final int originalTileSize = 16; //16 bit Characters
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;//48x48 tiles
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol; //768 pixels
    public int screenHeight = tileSize * maxScreenRow; //576 pixels

    //World Map Settings
    public final int maxWorldCol = 48;
    public final int maxWorldRow = 48;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    public int FPS = 60;

    public LandDraw landDraw = new LandDraw(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this,keyH);
    public CollisionsChecker collisionsChecker = new CollisionsChecker(this);


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.startGameThread();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null){
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if (remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        player.update();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        landDraw.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}

