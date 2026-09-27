package Main;

import object.*;

public class AssetsSetter {

    GamePanel gamePanel;

    //COSTRUTTORE DELLA CLASSE
    public AssetsSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;

    }

    public void setObject () {

        gamePanel.obj[0] = new OBJ_key();
        gamePanel.obj[0].worldX = 23 * gamePanel.tileSize;
        gamePanel.obj[0].worldY = 7 * gamePanel.tileSize;

        gamePanel.obj[1] = new OBJ_key();
        gamePanel.obj[1].worldX = 23 * gamePanel.tileSize;
        gamePanel.obj[1].worldY = 40 * gamePanel.tileSize;

        gamePanel.obj[2] = new OBJ_key();
        gamePanel.obj[2].worldX = 37 * gamePanel.tileSize;
        gamePanel.obj[2].worldY = 7 * gamePanel.tileSize;

        gamePanel.obj[3] = new OBJ_door();
        gamePanel.obj[3].worldX = 10 * gamePanel.tileSize;
        gamePanel.obj[3].worldY = 11 * gamePanel.tileSize;

        gamePanel.obj[4] = new OBJ_door();
        gamePanel.obj[4].worldX = 8 * gamePanel.tileSize;
        gamePanel.obj[4].worldY = 28 * gamePanel.tileSize;

        gamePanel.obj[5] = new OBJ_door();
        gamePanel.obj[5].worldX = 12 * gamePanel.tileSize;
        gamePanel.obj[5].worldY = 22 * gamePanel.tileSize;

        gamePanel.obj[6] = new OBJ_chest();
        gamePanel.obj[6].worldX = 10 * gamePanel.tileSize;
        gamePanel.obj[6].worldY = 7 * gamePanel.tileSize;

        gamePanel.obj[7] = new OBJ_greenBoots();
        gamePanel.obj[7].worldX = 37 * gamePanel.tileSize;
        gamePanel.obj[7].worldY = 42 * gamePanel.tileSize;


    }
}
