package src;

//import swing
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();

        //When the user clicks the ("X") button it closes the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Make a fixed size of a window meaning we can not change the size of the window
        window.setResizable(false);

        //Set title of the window
        window.setTitle("2D HORROR GAME");

        //Call the class
        GamePanel gamePanel = new GamePanel();

        //add it to the content pane
        window.add(gamePanel);
        window.pack();

        //Make window in the middle of the screen
        window.setLocationRelativeTo(null);

        //Lets us see the screen
        window.setVisible(true);

        gamePanel.gameThreadStart();
    }
}