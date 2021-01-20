package centus;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class StartPanel extends JPanel {
    private TitledBorder titledBorder;
    private BufferedImage piggy;



    StartPanel()  {



        setLayout(new FlowLayout());

        titledBorder=BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Panel Stratowy", TitledBorder.CENTER, TitledBorder.BELOW_TOP);
        setBorder(titledBorder);


        try {
            piggy= ImageIO.read(new File("src/centus/Image/piggy.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(piggy, 0, 0, getWidth(),getHeight(),this);


    }

}


