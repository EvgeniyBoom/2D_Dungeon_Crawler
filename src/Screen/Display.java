/*
package Screen;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Display {
    public void MainMenu() {
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        File jsonFile = new File("settings.json");

        Screen.Size.ScreenResolution resolution = new Screen.Size.ScreenResolution();
        int resolution_statement = 2;
        int width = resolution.getWidth(resolution_statement);
        int height = resolution.getHeight(resolution_statement);

        frame.setSize(width,height);
        frame.setBounds(0,0,width, height);
        frame.setUndecorated(true);
        frame.setResizable(false);

        JLabel title = new JLabel("<html><span style='color: teal;'>DotDotIO</span></html>");
        title.setFont (title.getFont().deriveFont(64.0f));
        frame.add(title);

        ImageIcon icon_sample = new ImageIcon("Graphic/button_new.png"); // Load the image
        Image image_sample = icon_sample.getImage(); // Get the image
        Image newImage_sample = image_sample.getScaledInstance(400, 100, Image.SCALE_SMOOTH); // Scale the image to the desired size
        ImageIcon newIcon_sample = new ImageIcon(newImage_sample);

        ImageIcon icon_sample_hover = new ImageIcon("Graphic/button_hover_new.png"); // Load the image
        Image image_sample_hover = icon_sample_hover.getImage(); // Get the image
        Image newImage_sample_hover = image_sample_hover.getScaledInstance(400, 100, Image.SCALE_SMOOTH); // Scale the image to the desired size
        ImageIcon newIcon_sample_hover = new ImageIcon(newImage_sample_hover);

        ImageIcon icon_sample_push = new ImageIcon("Graphic/button_push_new.png"); // Load the image
        Image image_sample_push = icon_sample_push.getImage(); // Get the image
        Image newImage_sample_push = image_sample_push.getScaledInstance(400, 100, Image.SCALE_SMOOTH); // Scale the image to the desired size
        ImageIcon newIcon_sample_push = new ImageIcon(newImage_sample_push);

        Font maruMonica;
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Font font = new Font("Arial", Font.BOLD, 24); // create a Font object

        JButton test = new JButton();
        test.setText("Test");
        test.setHorizontalTextPosition(JButton.CENTER);
        test.setVerticalTextPosition(JButton.CENTER);
        test.setIcon(newIcon_sample);
        test.setContentAreaFilled(false);
        test.setBorderPainted(false);
        test.setBounds((width/2) - 200,(height/4) - 50,400,100);
        test.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                test.setIcon(newIcon_sample_push);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                test.setIcon(newIcon_sample);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                test.setIcon(newIcon_sample_hover);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                test.setIcon(newIcon_sample);
            }
        });


        Screen.Button.MenuButton Exit = new Screen.Button.MenuButton();//newIcon);
        Exit.setRadius(100);
        Exit.setText("Exit");

        Exit.setFont(maruMonica);
        Exit.setFont(Exit.getFont().deriveFont(Font.PLAIN, 32F));

        Exit.setBounds((width/2) - 100,(height - height/4) - 25,200,50);
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        Screen.Button.MenuButton Play = new Screen.Button.MenuButton();//newIcon);
        Play.setText("Help");
        Play.setRadius(400);
        Play.setFont(font);
        Play.setBounds((width/2) - 100,(height/2) - 25,200,50);


        frame.add(test);
        frame.add(Play);
        frame.add(Exit);

        frame.setLayout(null);
        //frame.add(new Addition(frame));
        frame.setVisible(true);
    }
}
*/
