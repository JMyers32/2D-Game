package Main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {


        JFrame window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("My Dog");

        GamePanel gamepanel=new GamePanel();
        window.add(gamepanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamepanel.setupGame();
        gamepanel.startGameThread();
    }

}
