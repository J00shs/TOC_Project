import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/*
Class containing the content of the application.
 */
public class Menu extends JPanel {

    public Menu() {
        setBorder(new EmptyBorder(10, 10, 10, 10)); //Creates an empty border that takes up space.
        setLayout(new GridBagLayout());//Sets the layout manager our JPanel object will use.

        GridBagConstraints gbc = new GridBagConstraints();//Creates an object that will allow us to manipulate JPanel
        //components using our own constraints.
        gbc.gridwidth = GridBagConstraints.REMAINDER; //"Use REMAINDER to specify that the component's
        // display area will be from gridx to the last cell in the row. "

        gbc.anchor = GridBagConstraints.NORTH; //"used when the component is smaller than its display area.
        // It determines where, within the display area, to place the component.

        add(new JLabel("<html><h1>Welcome To Snake</h1></html>"),gbc);
        add(new JLabel("<html><h3><u>Developed by Kelly, Terry, and Joshua</u></h3></html>"),gbc);

        gbc.anchor = GridBagConstraints.CENTER; //Will center the component when 'gbc' is used
        gbc.fill = GridBagConstraints.HORIZONTAL; //Resizes the component horizontally
        gbc.insets = new Insets(15,0,0,0);

        JPanel deButtons = new JPanel(new GridBagLayout()); //Creates buttons using a GridBagLayout Manager
        //The following code creates the buttons for the main menu
        deButtons.add(new JButton("Start"),gbc);
        deButtons.add(new JButton("How To Play"),gbc);


        gbc.weighty = 1;
        add(deButtons,gbc); //Add the buttons to the window

    }
}
