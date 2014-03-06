package controller;

import model.LoginModel;
import view.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController extends JFrame {

    private final Dashboard dashboardView = new Dashboard();

    public LoginController(){
        this.initLogin();
    }

    String UITitle = "Login Box";

    private boolean loginProcess(String username,String password) {

        LoginModel loginModel = new LoginModel();

        loginModel.username = username;

        loginModel.password = password;

        boolean flag = loginModel.login();

        if(flag == true) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Login Failed");
        }
        return flag;
    }

    private void initLogin () {

        JPanel panel = new JPanel();

        final JTextField userTextField = new JTextField();
        userTextField.setName("userName");
        userTextField.setText("User Name");
        userTextField.setBounds(10,10,280,30);

        final JPasswordField userPasswordField = new JPasswordField();
        userPasswordField.setName("userPassword");
        userPasswordField.setText("Password");
        userPasswordField.setBounds(10,110,280,30);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(80,210,140, 30);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean loggedIn = loginProcess(userTextField.getText(), userPasswordField.getText());
                if(loggedIn == true) {
                    dashboardView.homeView();
                }
            }
        });

        getContentPane().add(panel);
        panel.setLayout(null); //new GridLayout(4,2,10,10));

        panel.add(userTextField);
        panel.add(userPasswordField);
        panel.add(submitButton);

        setTitle(this.UITitle);
        setSize(300,400);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
