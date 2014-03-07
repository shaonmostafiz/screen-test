package view;


import controller.LoginController;
import controller.SnapShoot;
import model.BaseModel;
import model.UserModel;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Dashboard extends JFrame {

    protected UserModel user = UserModel.getUserModel();

    public void homeView() {

        final JPanel panel = new JPanel();


        //add greeting text
        JLabel label = new JLabel("Hello " + user.getUsername());
        label.setBackground(Color.BLACK);
        label.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        label.setBounds(20,20,200,35);
        label.setVisible(true);

        //take a snap shoot
        SnapShoot snapShoot = new SnapShoot();

        try {
            Random random = new Random();

            int i = random.nextInt();

            BufferedImage imageContent = snapShoot.takeSnapShoot();

            //ImageIO.write(imageContent,"png",new File("Image__"+i+".png"));

            //get content from image
            //BufferedImage imageContentAgain = ImageIO.read(new File(i+".png"));

            //immediately send it to server
            String imageString = null;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            try {

                ImageIO.write(imageContent,"png",bos);

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

            BaseModel baseModel = new BaseModel();
            String response = baseModel.webservice("http://localhost/screen-test/upload.php",BaseModel.REQUEST_POST,key_value_pair);

            System.out.println(response);

        } catch (Exception e) {
            //
        }

        JButton logoutButton = new JButton("Log out");
        logoutButton.setBounds(300,20,180,35);
        logoutButton.setDoubleBuffered(true);
        logoutButton.setVisible(true);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        LoginController loginController = new LoginController();
                        loginController.setVisible(true);
                        setVisible(false);
                        dispose();
                    }
                });
            }
        });

        getContentPane().add(panel);

        panel.add(label);
        panel.add(logoutButton);

        setTitle("Home Page");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setAlwaysOnTop(false);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);
    }

}
