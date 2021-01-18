package centus;


import centus.MainFrame;

import javax.swing.*;

public class Service {
    public static void main(String [] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });

    }
}
