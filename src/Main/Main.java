package Main;
import javax.swing.JFrame;

public class Main {
    public static void main (String[] args){

        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ESCE ALLA CHIUSURA
        window.setResizable(false);//RIDIMENSIONABILE
        window.setTitle("adventureGame2d");//TITOLO

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);//con null lo posiziona al centro dello schermo
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();


    }

}
