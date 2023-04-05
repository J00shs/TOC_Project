import javax.swing.*;
/*
GameFrame defines the WINDOW of the game.
It uses the JFrame Library.
 */
public class GameWindow extends JFrame {
    /*
    Constructor
     */
    GameWindow(){
       this.add(new GamePanel());
       this.setTitle("Snake"); //Sets the title of the Window
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //When the exit button is clicked, the application will end.
       this.setResizable(true); //Allows the window to be resized by the user. If false, the window will not be resizable.
       this.setVisible(true); //Makes the window visible to the user.
       this.setLocationRelativeTo(null);//Centers the window to the middle of the screen.
    }
}
