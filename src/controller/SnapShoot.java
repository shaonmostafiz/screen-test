package controller;


import model.BaseModel;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SnapShoot {


    public BufferedImage takeSnapShoot() throws Exception {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);

        Robot robot = new Robot();

        BufferedImage image = robot.createScreenCapture(screenRectangle);

        return image;

    }

    public void sendSnapShoot(BufferedImage imageContent) throws Exception {

        BaseModel baseModel = new BaseModel();

        try {
            //immediately send it to server
            String imageString = null;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            try {

                ImageIO.write(imageContent, "png", bos);

                byte[] imageBytes = bos.toByteArray();

                BASE64Encoder encoder = new BASE64Encoder();

                imageString = encoder.encode(imageBytes);

                bos.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

            //System.out.println(imageString);
            Map<String,String> key_value_pair = new HashMap<String, String>();

            key_value_pair.put("action","uploadSnap");
            key_value_pair.put("imageString",imageString);

            String response = baseModel.webservice("http://localhost/screen-test/upload.php",BaseModel.REQUEST_POST,key_value_pair);
        } catch (Exception e) {

          }
    }
}
