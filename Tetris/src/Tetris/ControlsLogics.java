package Tetris;

public class ControlsLogics {
    public ControlsLogics(ControlsWindow window) {
        //Nesta classe onde existe a lógica dos botões foi possível usar funções lambdas tornando  mais legível.

        window.btnClose.addActionListener(e -> window.setVisible(false));

        window.btnApply.addActionListener(e -> window.setVisible(false));

        window.btnDefaults.addActionListener(e -> {

        });
    }
}
