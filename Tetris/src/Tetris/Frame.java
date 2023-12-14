package Tetris;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

import javax.swing.*;
import kuusisto.tinysound.TinySound;

public class Frame extends JFrame {

    private static final long serialVersionUID = 1L;
    public static TetrisPanel panel = new TetrisPanel();
    public static Image icon;
    public static Board board = new Board();

    public Frame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
        }

        icon = new ImageIcon("graphics/pieces/6.png").getImage();
        final ControlsWindow cont = new ControlsWindow();
        JMenuBar menuBar;
        JMenu menu, sounds, music, volume, about;
        JMenuItem menuItem, pauseItem, exitItem, controls, aboutItem, restart;
        final JRadioButtonMenuItem aTheme;
        final JRadioButtonMenuItem bTheme;
        final JRadioButtonMenuItem cTheme;

        JCheckBoxMenuItem snd, msc;

        menuBar = new JMenuBar();
        menu = new JMenu("Game");
        sounds = new JMenu("Options");
        music = new JMenu("Music");
        volume = new JMenu("Volume");
        about = new JMenu("About");
        menuBar.add(menu);
        menuBar.add(sounds);
        menuBar.add(about);
        sounds.add(music);
        sounds.add(volume);

        aTheme = new JRadioButtonMenuItem("A Theme");
        aTheme.setSelected(true);

        bTheme = new JRadioButtonMenuItem("B Theme");
        cTheme = new JRadioButtonMenuItem("C Theme");

        music.add(aTheme);
        music.add(bTheme);
        music.add(cTheme);


        snd = new JCheckBoxMenuItem("Sounds");
        snd.setSelected(true);
        msc = new JCheckBoxMenuItem("Music");
        msc.setSelected(true);
        snd.setEnabled(false);
        msc.setEnabled(false);

        sounds.add(snd);
        sounds.add(msc);
        menuItem = new JMenuItem("New Game",
                KeyEvent.VK_T);
        pauseItem = new JMenuItem("Pause Game",
                KeyEvent.VK_T);

        // Implementado o botão de restart no menu
        restart = new JMenuItem("Restart",
                KeyEvent.VK_T);

        exitItem = new JMenuItem("Exit Game",
                KeyEvent.VK_T);
        controls = new JMenuItem("Controls...",
                KeyEvent.VK_T);
        sounds.addSeparator();

        aboutItem = new JMenuItem("About...");

        controls.addActionListener((ActionEvent e) -> {
            TetrisPanel.turn.play();
            cont.setVisible(true);
        });

        aTheme.addActionListener((ActionEvent e) -> {
            bTheme.setSelected(false);
            cTheme.setSelected(false);
            aTheme.setSelected(true);
            TetrisPanel.bTheme.stop();
            TetrisPanel.cTheme.stop();
            TetrisPanel.aTheme.play(true);
            TetrisPanel.turn.play();
        });

        bTheme.addActionListener((ActionEvent e) -> {
            aTheme.setSelected(false);
            cTheme.setSelected(false);
            bTheme.setSelected(true);
            TetrisPanel.aTheme.stop();
            TetrisPanel.cTheme.stop();
            TetrisPanel.bTheme.play(true);
            TetrisPanel.turn.play();
        });

        cTheme.addActionListener((ActionEvent e) -> {
            aTheme.setSelected(false);
            bTheme.setSelected(false);
            cTheme.setSelected(true);
            TetrisPanel.aTheme.stop();
            TetrisPanel.bTheme.stop();
            TetrisPanel.cTheme.play(true);
            TetrisPanel.turn.play();
        });
        exitItem.addActionListener((ActionEvent e) -> {
            TetrisPanel.turn.play();
            System.exit(1);
        });

        //Funcionalidade que determina o volume do jogo
        String[] volumeOptions = {"0%", "20%", "40%", "60%", "80%", "100%"};
        JMenuItem[] volumeMenuItems = new JMenuItem[volumeOptions.length];

        for (int i = 0; i < volumeOptions.length; i++) {
            volumeMenuItems[i] = new JMenuItem(volumeOptions[i]);
            int finalI = i;
            volumeMenuItems[i].addActionListener(e -> {
                double volumeLevel = Double.parseDouble(e.getActionCommand().replace("%", "")) / 100.0;
                setVolumeFromMenu(volumeLevel);
                volumeMenuItems[finalI].setSelected(true);
            });

            volume.add(volumeMenuItems[i]);
        }

        pauseItem.addActionListener((ActionEvent e) -> {
            TetrisPanel.turn.play();
            if (!panel.lose) {
                if (!TetrisPanel.pause) {
                    TetrisPanel.pause = true;
                    board.timer.stop();
                } else {
                    TetrisPanel.pause = false;
                    board.timer.start();
                }
            }
        });

        menuItem.addActionListener((ActionEvent e) -> {
            TetrisPanel.turn.play();

            if (aTheme.isSelected()) {
                TetrisPanel.aTheme.play(true);
            }
            if (bTheme.isSelected()) {
                TetrisPanel.bTheme.play(true);
            }
            if (cTheme.isSelected()) {
                TetrisPanel.cTheme.play(true);
            }

            panel.lose = false; //// ############
            board.clearBoard();
            board.start();
        });

        restart.addActionListener((ActionEvent e) -> {
            if(panel.lose){
                board.restartGame();
            }
        });

        sounds.add(controls);
        menu.add(menuItem);
        menu.add(pauseItem);
        menu.add(restart); // adicionado o botão no menu
        menu.addSeparator();
        menu.add(exitItem);
        about.add(aboutItem);

        setJMenuBar(menuBar);
        setLayout(null);
        setIconImage(icon);
        setFocusable(true);

        //DIMENSOES DA TELA
        setSize(new Dimension(800, 770));
        setTitle("Tetris");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //PANEL PARTE VISUAL QUE FICA DENTRO DA TELA
        panel.init(this);
        add(panel);
        panel.setBounds(0, 0, 800, 770);

        //BOARD É O MENU DE OPCOES QUE FICA SUSPENSO
        add(board);
        board.setBounds(0, 50, 400, 720);
        setLocationRelativeTo(null);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

                panel.keyboardEvent(e);
                board.keyPressed(e);

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        aboutItem.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this, "OpenTetris Classic v1.0-dev 08/10/2021\n\nA full open-source recreation of the 1989 hit game.\n\nOriginally developed by Kyle Bredenkamp.\n\nRefactored and improved by:\nPedro G. K. Bertella\nJailson L. Panizzon\nArthur R. P. So.\n\nGitHub\nhttps://github.com/pedrobertella/OpenTetrisClassic\n", "About OpenTetris Classic", JOptionPane.INFORMATION_MESSAGE);
        });

    }

    public void setVolumeFromMenu(double val) {
        TinySound.setGlobalVolume(val);
    }

    public static void main(String[] args) {

        Frame frame = new Frame();

        frame.setVisible(true);

    }

}
