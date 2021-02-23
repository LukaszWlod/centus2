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


    private JPanel registrationPanel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel lastNameLabel;
    private JTextField lastNameField;
    private JLabel emailLabel;
    private JTextField emailFiled;
    private JPasswordField registrationPasswordField;
    private JLabel repeatPasswordLabel;
    private JPasswordField repeatPasswordField;
    private JButton safeButton;
    private JButton cancelButton;




    public LoginDialog(Frame parent , boolean modal, PersonalManager personalManager )
    throws  SQLException {

        super(parent,modal);
        this.personalManager = personalManager;

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        createRegisterPanel();
        this.createLoginPanel();
        this.loadUsersFromDatabase();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(200,150,260,220);
        setResizable(true);







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

                loginPanel.setVisible(false);

                add(registrationPanel);
                registrationPanel.setVisible(true);

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrationPanel.setVisible(false);
                loginPanel.setVisible(true);
            }
        });

        safeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String password = new String (registrationPasswordField.getPassword());
                String password2 = new String(repeatPasswordField.getPassword());





                if(password.equals(password2)) {
                    nameField.getText();
                    User newUser = new User(nameField.getText(),lastNameField.getText(),emailFiled.getText(),password);
                    nameField.setText("");
                    lastNameField.setText("");
                    emailFiled.setText("");
                    repeatPasswordField.setText("");
                    repeatPasswordField.setText("");
                    try {
                        personalManager.addNewUser(newUser);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(getParent(),
                            "Wpisane hasła nie sa takie same!",
                            "Bład hasła",
                            JOptionPane.ERROR_MESSAGE);
                }
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
    public void createRegisterPanel()  {
        registrationPanel = new JPanel();
        nameLabel = new JLabel("Imię: ");
        nameField = new JTextField(15);
        lastNameLabel = new JLabel("Nazwisko: ");
        lastNameField = new JTextField(15);
        emailLabel = new JLabel("Email: ");
        emailFiled = new JTextField(15);
        passwordLabel = new JLabel("Hasło: ");
        registrationPasswordField = new JPasswordField(15);
        repeatPasswordLabel = new JLabel("Powtórz hasło: ");
        repeatPasswordField = new JPasswordField(15);

        safeButton = new JButton("Zapisz");
        cancelButton = new JButton("Anuluj");

        registrationPanel.setBorder(BorderFactory.createTitledBorder("Panel rejestracji"));
        registrationPanel.setLayout(new GridLayout(6,2,5,5));


        registrationPanel.add(nameLabel);
        registrationPanel.add(nameField);
        registrationPanel.add(lastNameLabel);
        registrationPanel.add(lastNameField);
        registrationPanel.add(emailLabel);
        registrationPanel.add(emailFiled);
        registrationPanel.add(passwordLabel);
        registrationPanel.add(registrationPasswordField);
        registrationPanel.add(repeatPasswordLabel);
        registrationPanel.add(repeatPasswordField);
        registrationPanel.add(cancelButton);
        registrationPanel.add(safeButton);





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
