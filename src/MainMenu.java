

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/*
Class containing the content of the application.
 */
public class MainMenu extends JPanel {


    public MainMenu() {
        setBorder(new EmptyBorder(10, 10, 10, 10)); //Creates an empty border that takes up space.
        setLayout(new GridBagLayout());//Sets the layout manager our JPanel object will use.
        this.setBackground(Color.orange);

        GridBagConstraints gbc = new GridBagConstraints();//Creates an object that will allow us to manipulate JPanel
        //components using our own constraints.
        gbc.gridwidth = GridBagConstraints.REMAINDER; //"Use REMAINDER to specify that the component's
        // display area will be from gridx to the last cell in the row. "

        gbc.anchor = GridBagConstraints.NORTH; //"used when the component is smaller than its display area.


        // It determines where, within the display area, to place the component.

        add(new JLabel("<html><h1>Welcome To Snake</h1></html>"),gbc);
        add(new JLabel("<html><h3><u>Developed by Kelly, Terry, and Joshua</u></h3></html>"),gbc);
        //        add(new JLabel("<html><img src='src/resources1/apple.png'></html>"),gbc);

        gbc.gridwidth = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5,5,5,5);

        JLabel image = new JLabel();
        ImageIcon icon = new ImageIcon("src/resources1/micheal.gif");
        image.setIcon(icon);
        add(image,gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER; //Will center the component when 'gbc' is used
        gbc.fill = GridBagConstraints.HORIZONTAL; //Resizes the component horizontally
        gbc.insets = new Insets(15,10,15,10);

        JPanel deButtons = new JPanel(new GridBagLayout()); //Creates buttons using a GridBagLayout Manager
        //The following code creates the buttons for the main menu
        JButton start = new JButton("Start");
        start.setOpaque(true);
        start.setBackground(Color.white); //Changes background of button
        start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JButton howTo = new JButton("How To Play");
        howTo.setBackground(Color.white); //Changes background of button
        howTo.setOpaque(true);
        howTo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Changes cursor when hovering over button

        deButtons.setBackground(Color.orange);
        deButtons.add(start,gbc);
        deButtons.add(howTo,gbc);


        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGameButton button  = new  startGameButton();
                button.setTitle("Start Game");
                button.setSize(300,300);
                button.setLocationRelativeTo(null);
                button.setResizable(false);

                button.setVisible(true);
                button.add(new MainGame());

            }
        });

        howTo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                howToPlayButton button = new howToPlayButton();
                button.setTitle("How to Play");
                button.setSize(700, 500);
                button.setLocationRelativeTo(null);
                button.setResizable(false);

                button.setVisible(true);
                button.add(new howToPlayMenu());
            }
        });

        gbc.weighty = 1;
        add(deButtons,gbc); //Add the buttons to the window

    }


}



//An empty class that used to hold the window for the actual game
//DO NOT DELETE!
class startGameButton extends JFrame {

}

class howToPlayButton extends JFrame {

}