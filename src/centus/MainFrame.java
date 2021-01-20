package centus;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class MainFrame extends JFrame {

    private  PersonalManager personalManager;
    private  StartPanel startPanel;

    MainFrame()  throws IOException, SQLException {


        super("Centuś");
        personalManager= new PersonalManager();
        startPanel = new StartPanel();

        add(startPanel);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
