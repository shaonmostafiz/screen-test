package controller;


import java.awt.*;
import java.awt.image.BufferedImage;

public class SnapShoot {


    public BufferedImage takeSnapShoot() throws Exception {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);

        Robot robot = new Robot();

        BufferedImage image = robot.createScreenCapture(screenRectangle);

        return image;

    }

}
