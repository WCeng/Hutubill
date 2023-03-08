package gui.listener;

import entity.Category;
import gui.panel.*;
import service.RecordService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RecordListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordPanel p = RecordPanel.instance;
//        判断是否有分类
        if(0 == p.cbModel.cs.size()){
            JOptionPane.showMessageDialog(p, "暂无消费分类，无法添加，请先增加消费分类");
            MainPanel.instance.workingPanel.show(CategoryPanel.instance);
            return;
        }
//        检查花费是否为零
        if(!GUIUtil.checkZero(p.tfSpend,"花费金额"))
            return;

        int spend = Integer.parseInt(p.tfSpend.getText());
        Category c = p.getSelectedCategory();
        String comment = p.tfRemark.getText();
        Date d  = p.datePicker.getDate();

        new RecordService().add(spend, c, comment, d);
        JOptionPane.showMessageDialog(p, "添加成功");

        MainPanel.instance.workingPanel.show(SpendPanel.instance);
    }

}
