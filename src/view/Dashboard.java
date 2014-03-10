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

        SnapShoot snapShoot = new SnapShoot();
        try {
            //take a snap shoot
            BufferedImage image = snapShoot.takeSnapShoot();
            //save to server
            snapShoot.sendSnapShoot(image);
        } catch (Exception e) {
            e.printStackTrace();
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

        JButton takeSnapShootBtn = new JButton("Take Snap Shoot");
        takeSnapShootBtn.setBackground(Color.CYAN);
        takeSnapShootBtn.setBounds(20,80,180,35);
        takeSnapShootBtn.setVisible(true);

        takeSnapShootBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SnapShoot snap = new SnapShoot();
                    BufferedImage newSnapImage = snap.takeSnapShoot();
                    snap.sendSnapShoot(newSnapImage);
                } catch (Exception snapException) {
                    snapException.printStackTrace();
                }
            }
        });

        getContentPane().add(panel);

        panel.add(label);
        panel.add(logoutButton);
        panel.add(takeSnapShootBtn);

        setTitle("Home Page");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setAlwaysOnTop(false);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);
    }

}
