package centus;


import centus.MainFrame;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class Service {
    public static void main(String [] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MainFrame();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }
}
