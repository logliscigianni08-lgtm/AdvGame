package Entity;

import Main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    //COSTRUTTORE DELLA CLASSE
    public Player (GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        //solid area settings
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.height = 28;
        solidArea.width = 28;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 23;//coordinata x del player (deafault = 23)
        worldY = gamePanel.tileSize * 21;//coordinara y del player (deafault = 21)
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));





        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyHandler.upPressed == true || keyHandler.downPressed == true ||
                keyHandler.leftPressed == true ||keyHandler.rightPressed == true ) {

            if (keyHandler.upPressed == true){
                direction = "up";
            }
            if (keyHandler.downPressed == true){
                direction = "down";
            }
            if (keyHandler.leftPressed == true){
                direction = "left";
            }
            if (keyHandler.rightPressed == true){
                direction = "right";
            }


            //check tile collision
            collisionOn = false;
            gamePanel.colChecker.checkTile(this);

            //check object collision
            int objIndex = gamePanel.colChecker.checkObject(this, true);
            pickUpObjects(objIndex);


            //if collision is false player can move
            if (collisionOn == false) {
                switch (direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }


            spriteCounter++;
            if (spriteCounter > 15){
                if (spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }else {
           spriteNum = 1;
        }


    }

    public void pickUpObjects(int i) {
        if (i != 999){
            String objectName = gamePanel.obj[i].name;

            switch (objectName){

                case "key":
                    gamePanel.playSE(1);
                    hasKey++;
                    gamePanel.obj[i] = null;
                    gamePanel.ui.showMessage("you got a key!");
                    break;

                case "door":
                    if(hasKey > 0) {
                        gamePanel.playSE(3);
                        gamePanel.obj[i] = null;
                        hasKey--;
                        gamePanel.ui.showMessage("you opened the door!");
                    }else{
                        gamePanel.ui.showMessage("you need a key!");
                    }
                    System.out.println("key: " + hasKey);

                    break;


                case "greenBoots":
                        gamePanel.playSE(2);
                        speed += 2;
                        gamePanel.obj[i] = null;
                        gamePanel.ui.showMessage("speed upppp!");
                    break;

                case "chest":
                    gamePanel.ui.gameFinished = true;
                    gamePanel.stopMusic();
                    gamePanel.playSE(4);
                    break;
            }
        }
    }



    public void draw(Graphics2D g2) {
    /*g2.setColor(Color.white);
    g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);*/

        BufferedImage image = null;
        switch (direction){
            case "up":

                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                image = up2;
                }

                break;

            case "down":

                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }

                break;

            case "left":

                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }

                break;

            case "right":

                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }

                break;
        }

        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
