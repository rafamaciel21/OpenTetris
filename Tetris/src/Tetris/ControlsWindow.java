package Tetris;

import java.awt.*;

import javax.swing.*;

public class ControlsWindow extends JFrame {
    public static Image icon;
    public AbstractButton btnClose;
    public AbstractButton btnApply;
    public AbstractButton btnDefaults;
    private JFrame frame;

    public ControlsWindow() {
        initialize();
    }
    private void initialize() {
        ImageIcon icon = new ImageIcon("graphics/pieces/6.png");
        setIconImage(icon.getImage());
        setTitle("Controls");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 2));
        getContentPane().add(panel);

        String[] labels = {"Move Left", "Move Right", "Rotate Left", "Rotate Right", "Drop Piece", "Move Down"};

        for (String s : labels) {
            JLabel label = new JLabel(s);
            panel.add(label);
        }
    }

}
