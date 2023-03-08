package gui.listener;

import gui.panel.ConfigPanel;
import service.ConfigService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ConfigListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        ConfigPanel p = ConfigPanel.instance;

        if(!GUIUtil.checkEmpty(p.tfTMB, "本月预算")){
            return;
        }

        String mysqlPath = p.tfMysql.getText();

        if(mysqlPath.length() != 0){
            File commandFile = new File(mysqlPath, "bin/mysql.exe");
            if(!commandFile.exists()){
                JOptionPane.showMessageDialog(p, "Mysql路径不正确");
                p.tfMysql.grabFocus();
                return;
            }
        }

        ConfigService cs = new ConfigService();
        cs.update(ConfigService.budget, p.tfTMB.getText());
        cs.update(ConfigService.mysqlPath, mysqlPath);
        JOptionPane.showMessageDialog(p, "设置修改成功");
    }
}
