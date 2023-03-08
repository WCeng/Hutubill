package gui.listener;

import gui.panel.BackupPanel;
import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import service.ConfigService;
import startup.MysqlUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class BackupListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        BackupPanel p = BackupPanel.instance;
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        if(0 == mysqlPath.length()){
            JOptionPane.showMessageDialog(p, "备份前请事先配置mysql路径");
            MainPanel.instance.workingPanel.show(ConfigPanel.instance);
            ConfigPanel.instance.tfMysql.grabFocus();
            return;
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

        int returnVal = fc.showSaveDialog(p);
        File file = fc.getSelectedFile();
        if(returnVal == JFileChooser.APPROVE_OPTION){
            if(!file.getName().toLowerCase().endsWith(".sql"))
                file = new File(file.getParent(), file.getName()+".sql");
            try {
                MysqlUtil.backup(mysqlPath, file.getAbsolutePath());
                JOptionPane.showMessageDialog(p, "备份成功，备份文件位于:\r\n"+
                        file.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(p, "备份失败\r\n,错误:"+ex.getMessage());
            }
        }
    }
}
