package gui.listener;

import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import gui.panel.RecoverPanel;
import service.ConfigService;
import startup.MysqlUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class RecoverListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecoverPanel p = RecoverPanel.instance;
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        if(0 == mysqlPath.length()){
            JOptionPane.showMessageDialog(p, "恢复前请事先配置mysql路径");
            MainPanel.instance.workingPanel.show(ConfigPanel.instance);
            ConfigPanel.instance.tfMysql.grabFocus();
        }
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("hutubill.sql"));
        fc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".sql");
            }

            @Override
            public String getDescription() {
                return ".sql";
            }
        });
        int returnVal = fc.showOpenDialog(p);
        File f = fc.getSelectedFile();
        if(returnVal == JFileChooser.APPROVE_OPTION){
            try {
                MysqlUtil.recover(mysqlPath, f.getAbsolutePath());
                JOptionPane.showMessageDialog(p, "恢复成功");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(p, "恢复失败\r\n"+
                        "错误:"+ex.getMessage());
            }
        }
    }
}
