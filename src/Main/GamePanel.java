package Main;

import Entity.Player;
import object.SuperObjects;
import tile.TileManeger;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    int gamePlayed;

    //SCREEN SETTINGS
    final int originalTiles = 16; //16x16 pixels
    final int scale = 3;// set scale to 3 ( 48x48 pixels)

    public final int tileSize = originalTiles * scale;// 48x48 pixels
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;//768 pixels
    public final int screenHeight = tileSize * maxScreenRow;//576 pixels


    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;


    //FPS
    int FPS = 60;

    //istanze delle classi del sistema
    TileManeger tileManeger = new TileManeger(this);
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker colChecker = new CollisionChecker(this);
    public AssetsSetter aSetter = new AssetsSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;


    //istanze delle classi del player e oggetti
    public Player player = new Player(this,keyH);
    public SuperObjects obj [] = new SuperObjects[10];


    //COSTRUTTORE DELLA CLASSE
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setObject();

        playMusic(0);

    }


    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
        gamePlayed++;
    }



    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            long currentTime = System.nanoTime();

            update();

            repaint();


            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                Thread.sleep((long)remainingTime);

                if (remainingTime < 0){
                    remainingTime = 0;
                }

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }



    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //TILE
        tileManeger.draw(g2);


        //OBJECT
        for(int i = 0; i < obj.length; i++){
            if (obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);

        //UI
        ui.draw(g2);

        g2.dispose();

    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        se.setFile(i);
        se.play();
    }

}
