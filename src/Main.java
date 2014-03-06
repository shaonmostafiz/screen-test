import controller.LoginController;

import javax.swing.*;


public class Main extends JFrame{

    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginController loginController = new LoginController();
                loginController.setVisible(true);
            }
        });

    }
}
