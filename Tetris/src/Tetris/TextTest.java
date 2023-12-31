package Tetris;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TextTest {

    public static void main(String[] args) {
        new TextTest();
    }

//    public TextTest() {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//                }
//
//                JFrame frame = new JFrame("Testing");
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setLayout(new BorderLayout());
//                frame.add(new TestPane());
//                frame.pack();
//                frame.setLocationRelativeTo(null);
//                frame.setVisible(true);
//            }
//        });
//    }

//AQUI FOI FEITO A EXTRAÇÃO DO MÉTODO E A MODULARIZAÇÃO DO MESMO
public TextTest() {
    EventQueue.invokeLater(this::initializeUI);
}

    private void initializeUI() {
        setSystemLookAndFeel();
        createAndShowFrame();
    }

    private void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
    }

    private void createAndShowFrame() {
        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new TestPane());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    public class TestPane extends JPanel {

        private BufferedImage img;

        public TestPane() {
            try {
                img = ImageIO.read(getClass().getResource("/font.png"));
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }

        public List<BufferedImage> convert(String text) {

            List<BufferedImage> images = new ArrayList<>(25);

            for (char c : text.toCharArray()) {
                c = Character.toUpperCase(c);
                int smudge = 1;
                int offset = -1;
                if (c >= 48 && c <= 57) {
                    offset = c - 48;
                } else if (c >= 65 && c <= 90) {
                    offset = c - 65 + 10;
                } else if (c == 32) {
                    offset = 48;
                    smudge = 2;
                }

                if (offset >= 0) {
                    BufferedImage sub = img.getSubimage((offset * 8) + smudge, 0, 8 - smudge, 8);
                    images.add(sub);
                }
            }

            return images;

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            List<BufferedImage> text = convert("Hi mom");
            int x = (getWidth() - (8 * text.size())) / 2;
            int y = (getHeight() - 8) / 2;
            for (BufferedImage bufImg : text) {
                g2d.drawImage(bufImg, x, y, this);
                x += bufImg.getWidth();
            }
            g2d.dispose();
        }
    }

}
