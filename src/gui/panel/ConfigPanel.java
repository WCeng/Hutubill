package gui.panel;

import entity.Config;
import gui.listener.ConfigListener;
import service.ConfigService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static ConfigPanel instance = new ConfigPanel();

    JLabel lThisMonthBudget = new JLabel("本月预算(￥)");
    JLabel lMysql = new JLabel("Mysql安装目录");
    public JTextField tfTMB = new JTextField("0");
    public JTextField tfMysql = new JTextField();

    public JButton bUpdate = new JButton("更新");

    public ConfigPanel(){
        GUIUtil.setColor(ColorUtil.grayColor, lThisMonthBudget, lMysql);
        GUIUtil.setColor(ColorUtil.blueColor, bUpdate);

        this.setLayout(new BorderLayout());

        JPanel pInput = new JPanel();
        JPanel pSubmit = new JPanel();
        int gap = 40;
        pInput.setLayout(new GridLayout(4, 1, gap, gap));

        pInput.add(lThisMonthBudget);
        pInput.add(tfTMB);
        pInput.add(lMysql);
        pInput.add(tfMysql);

        pSubmit.add(bUpdate);

        this.add(pInput, BorderLayout.NORTH);
        this.add(pSubmit, BorderLayout.CENTER);

        addListener();
    }

    @Override
    public void updateData() {
        String budget = new ConfigService().get(ConfigService.budget);
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        tfTMB.setText(budget);
        tfMysql.setText(mysqlPath);
        tfTMB.grabFocus();
    }

    public void addListener(){
        ConfigListener cl = new ConfigListener();
        bUpdate.addActionListener(cl);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ConfigPanel.instance);
    }
}
