package centus;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class LoginDialog extends JDialog {
    private DBManager dbManager;
    private JPanel loginPanel;
    private PersonalManager personalManager;
    private JButton loginButton;
    private JButton newUserButton;
    private JComboBox comboUser;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton dropUserButton;
    private JButton finishButton;



    public LoginDialog(Frame parent , boolean modal, PersonalManager dataBase)
    throws IOException, SQLException {

        super(parent,modal);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        this.createLoginPanel();

        dbManager= new PersonalManager();

        this.dbManager = dataBase;
        add(loginPanel);
        setVisible(true);
    }

    public void createLoginPanel(){
        loginPanel = new JPanel();
        loginLabel = new JLabel("Login: ");
        passwordLabel = new JLabel("Hasło: ");
        comboUser = new JComboBox();
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Zaloguj się");
        newUserButton = new JButton("Załóż konto");
        dropUserButton = new JButton("Usuń");
        finishButton = new JButton("Zakończ");

        loginPanel.setBorder(BorderFactory.createTitledBorder("Panel logowania"));
        loginPanel.setLayout(new GridLayout(4,2,5,5));
        setBounds(200,150,260,165);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Logowanie");
        setResizable(true);
        loginButton.setEnabled(false);
        passwordField.setEnabled(false);


        loginPanel.add(loginLabel);
        loginPanel.add(comboUser);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(newUserButton);
        loginPanel.add(finishButton);
        loginPanel.add(dropUserButton);



    }

}
