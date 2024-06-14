package main;

import javax.swing.*;


public class Main {

    public static JFrame window;

    public static void main(String[] args) {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Some Title");
        //window.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();


        //Display mainmenu = new Display();
        //mainmenu.MainMenu();
    }

}
/*
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Display {
    public static void main(String[] args) throws Exception {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Register!");
        JLabel label1, label2, label3;
        frame.setLayout(new GridLayout(3, 2));
        label1 = new JLabel("Id", SwingConstants.CENTER);
        label2 = new JLabel("Age", SwingConstants.CENTER);
        label3 = new JLabel("Password", SwingConstants.CENTER);
        JTextField emailId = new JTextField(20);
        JTextField age = new JTextField(20);
        JTextField age2 = new JTextField(20);
        JPasswordField passwd = new JPasswordField();
        passwd.setEchoChar('*');
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(emailId);
        frame.add(age);
        frame.add(age2);
        frame.add(passwd);
        int width = 500;
        int height = 400;
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((int) size.getWidth() - width, 0, width, height);
        frame.setVisible(true);
    }
}*/
