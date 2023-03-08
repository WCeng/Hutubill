package gui.frame;


import gui.panel.MainPanel;

import javax.swing.*;

public class MainFrame extends JFrame {
    public static MainFrame instance = new MainFrame();

    private MainFrame(){
        this.setSize(500, 450);
        this.setTitle("一本糊涂账");
        this.setLocationRelativeTo(null);
        this.setContentPane(MainPanel.instance);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        instance.setVisible(true);
    }
}
