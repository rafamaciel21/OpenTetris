package Tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlsLogics {
    private ControlsWindow window;
    public ControlsLogics(ControlsWindow window) {
        this.window = window;

            window.btnClose.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        window.setVisible(false);
                }
            });

        window.btnApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
            }
        });

        window.btnDefaults.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
