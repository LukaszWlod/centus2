package centus;

import javax.swing.*;
import java.awt.*;

public class RegistrationDialog extends JDialog {
    private JPanel registrationPanel;
    private JLabel nameLabel;
    private JTextField  nameField;
    private JLabel lastNameLabel;
    private JTextField lastNameField;
    private JLabel emailLabel;
    private JTextField emailFiled;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel repeatPasswordLabel;
    private JPasswordField repeatPasswordField;
    private JButton safeButton;
    private JButton exitButton ;


    public RegistrationDialog( Frame parent , boolean modal, PersonalManager personalManager){
        super(parent,modal);
        createRegisterPanel();
        add(registrationPanel);
        setVisible(false);



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
        passwordField = new JPasswordField(15);
        repeatPasswordLabel = new JLabel("Powtórz hasło: ");
        repeatPasswordField = new JPasswordField(15);

        safeButton = new JButton("Zapisz");
        exitButton = new JButton("Anuluj");

        registrationPanel.setBorder(BorderFactory.createTitledBorder("Panel logowania"));
        registrationPanel.setLayout(new GridLayout(6,2,5,5));
        setBounds(200,150,300,250);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rejestracja");
        setResizable(true);




        registrationPanel.add(nameLabel);
        registrationPanel.add(nameField);
        registrationPanel.add(lastNameLabel);
        registrationPanel.add(lastNameField);
        registrationPanel.add(emailLabel);
        registrationPanel.add(emailFiled);
        registrationPanel.add(passwordLabel);
        registrationPanel.add(passwordField);
        registrationPanel.add(repeatPasswordLabel);
        registrationPanel.add(repeatPasswordField);
        registrationPanel.add(exitButton);
        registrationPanel.add(safeButton);




    }



}
