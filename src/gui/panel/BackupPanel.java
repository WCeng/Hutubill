package gui.panel;

import gui.listener.BackupListener;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;

public class BackupPanel extends WorkingPanel {
//    static {
//        GUIUtil.useLNF();
//    }

    public static BackupPanel instance = new BackupPanel();

    JButton bBackup = new JButton("备份");

    public BackupPanel(){
        GUIUtil.setColor(ColorUtil.blueColor, bBackup);

        this.add(bBackup);

        addListener();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(instance);
    }

    @Override
    public void updateData() {

    }

    @Override
    public void addListener() {
        BackupListener listener = new BackupListener();
        bBackup.addActionListener(listener);
    }
}
