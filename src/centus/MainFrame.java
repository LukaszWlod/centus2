package centus;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class MainFrame extends JFrame {

    private  PersonalManager personalManager;
    private  StartPanel startPanel;
    private  LoginDialog loginDialog;

    MainFrame()  throws IOException, SQLException {


        super("Centuś");
        personalManager= new PersonalManager();
        startPanel = new StartPanel();
        loginDialog = new LoginDialog(this,true,personalManager);
        loginDialog.setLocationRelativeTo(this);

        add(startPanel);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
