package centus;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

public class MainFrame extends JFrame {

    private PersonalManager personalManager;
    private  User user;
    private StartPanel startPanel;
    private LoginDialog loginDialog;
    private  ExpensesPanel expensesPanel;


    MainFrame() throws IOException, SQLException {


        super("Centuś");

        personalManager = new PersonalManager();
        startPanel = new StartPanel();
        loginDialog = new LoginDialog(this, true,personalManager);
        loginDialog.setLocationRelativeTo(this);
        expensesPanel = new ExpensesPanel();


        add(expensesPanel);
      //  add(startPanel);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        loginDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });


    }
}
