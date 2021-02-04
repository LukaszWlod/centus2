package centus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.ResultSet;
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
        setTitle("Logowanie");

        this.createLoginPanel();
        try {
            this.loadUsersFromDatabase();
        } catch (SQLException throwables) {
            throwables.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dbManager= new PersonalManager();

        this.dbManager = dataBase;
        add(loginPanel);
        setVisible(true);
    }

    public void createLoginPanel() throws IOException, SQLException {
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

        comboUser.addItemListener(new ItemListener() {
            // Listening if a new items of the combo box has been selected.
            public void itemStateChanged(ItemEvent event) {


                // The item affected by the event.
                Object item = event.getItem();

               if( item.toString() !=""){
                   loginButton.setEnabled(true);
                   passwordField.setEnabled(true);
               }

                System.out.println("Affected items: " + item.toString());

                if (event.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println(item.toString() + " selected.");
                }

                if (event.getStateChange() == ItemEvent.DESELECTED) {
                    System.out.println(item.toString() + " deselected.");
                }
            }
        });




        }




    public void loadUsersFromDatabase() throws SQLException,IOException{
        personalManager = new PersonalManager();
        comboUser.removeAll();
        ResultSet resultSet = personalManager.loadLoginFromDatabase();
        String login = "";
        comboUser.addItem(login);
        while (resultSet.next()){
            login = resultSet.getString("email");
            comboUser.addItem(login);
        }
        passwordField.setText("");
    }
}
