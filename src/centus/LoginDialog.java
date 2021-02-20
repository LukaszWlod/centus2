package centus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDialog extends JDialog {
    private JPanel loginPanel;
    private PersonalManager personalManager;
    private JButton loginButton;
    private JButton newUserButton;
    private JComboBox comboUser;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton dropUserButton;
    private JButton exitButton;
    private  RegistrationDialog registrationDialog;



    public LoginDialog(Frame parent , boolean modal, PersonalManager personalManager ,RegistrationDialog registrationDialog)
    throws  SQLException {

        super(parent,modal);
        this.personalManager = personalManager;
        this.registrationDialog = registrationDialog;
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);


        this.createLoginPanel();
        this.loadUsersFromDatabase();





        comboUser.addItemListener(event -> {

            Object item = event.getItem();

            if(!item.toString().equals("")){
                loginButton.setEnabled(true);
                passwordField.setEnabled(true);
            }
        });


        loginButton.addActionListener(e -> {
            String selectedItem = comboUser.getSelectedItem().toString();
            String password= null;

            String passwordFromField = new String( passwordField.getPassword());
            try {
                ResultSet resultSet = personalManager.loadPasswordFromDatabase(selectedItem);
                if (resultSet.next()){
                    password = resultSet.getString("password");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if (passwordFromField.equals(password)){
                setVisible(false);
            }
            else{
                JOptionPane.showMessageDialog(getParent(),
                        "Błedne hasło!",
                        "Bład hasła",
                        JOptionPane.ERROR_MESSAGE);
            }
        });


        exitButton.addActionListener(event ->{
            System.exit(0);
        });

        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                registrationDialog.setVisible(true);

            }
        });


        add(loginPanel);





        setVisible(true);


    }



    public void createLoginPanel()  {
        loginPanel = new JPanel();
        loginLabel = new JLabel("Login: ");
        passwordLabel = new JLabel("Hasło: ");
        comboUser = new JComboBox();
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Zaloguj się");
        newUserButton = new JButton("Załóż konto");
        dropUserButton = new JButton("Usuń");
        exitButton = new JButton("Zakończ");

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
        loginPanel.add(exitButton);
        loginPanel.add(dropUserButton);

    }




    public void loadUsersFromDatabase() throws SQLException{
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
