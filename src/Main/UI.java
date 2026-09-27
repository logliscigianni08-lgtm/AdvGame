package Main;

import object.OBJ_key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;

public class UI {

    //variabili e dichiarazioni
    GamePanel gamePanel;
    Font font1,font2;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");


    //COSTRUTTORE DELLA CLASSE
    public UI(GamePanel gamePanel){
        this.gamePanel= gamePanel;
        font1 = new Font("Arial", Font.PLAIN, 40);
        font2 = new Font("Arial", Font.BOLD, 80);
        OBJ_key key = new OBJ_key();
        keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw (Graphics2D g2){

        if (gameFinished == true){


            g2.setFont(font1);
            g2.setColor(Color.white);
            String text;
            int textLenght;
            int x;
            int y;






            //first string
            text = "you found the treasure!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
             x = gamePanel.screenWidth/2 - textLenght/2;
             y = gamePanel.screenHeight/2 - (gamePanel.tileSize*3);
             g2.drawString(text,x,y);



             //second string
            text = "your time is: " + dFormat.format(playTime);
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gamePanel.screenWidth/2 - textLenght/2;
            y = gamePanel.screenHeight/2 + (gamePanel.tileSize*4);
            g2.drawString(text,x,y);


            //third string
            g2.setFont(font2);
            g2.setColor(Color.yellow);
            text = "Congratulations!!!";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gamePanel.screenWidth/2 - textLenght/2;
            y = gamePanel.screenHeight/2 + (gamePanel.tileSize*2);
            g2.drawString(text,x,y);

            gamePanel.gameThread = null;




        }else{
            g2.setFont(font1);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gamePanel.tileSize/2,gamePanel.tileSize/2, gamePanel.tileSize,gamePanel.tileSize,null);
            g2.drawString("x "+ gamePanel.player.hasKey , 74, 65);


            //time
            playTime += (double)1/60;
            g2.drawString("time: " + dFormat.format(playTime), gamePanel.tileSize*11, 65);

            //message
            if (messageOn == true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gamePanel.tileSize/2, gamePanel.tileSize*5);

                messageCounter++;
                if (messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }

        }




    }
}
