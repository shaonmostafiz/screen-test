package view;


import javax.swing.*;

public class Dashboard extends JFrame {

    public void homeView() {

        JPanel panel = new JPanel();

        setTitle("Home Page");
        setSize(300,200);
        setLocationRelativeTo(null);
        setAlwaysOnTop(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
