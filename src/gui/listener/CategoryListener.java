package gui.listener;

import entity.Category;
import gui.panel.CategoryPanel;
import service.CategoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryPanel p = CategoryPanel.instance;
        JButton b = (JButton) e.getSource();

        if(b == p.bInsert){
            String name = JOptionPane.showInputDialog(p,"请输入分类");
//            解决取消报错
            if(name == null)
                return;

            if(name.length() == 0){
                JOptionPane.showMessageDialog(p, "分类名称不能为空");
                return;
            }
            new CategoryService().add(name);
        }

        if(b == p.bEdit){
            Category c = p.getSelectedCategory();
            String name = JOptionPane.showInputDialog(p,"修改分类名称", c.name );
            if(null == name)
                return;
            if(name.length() == 0){
                JOptionPane.showMessageDialog(p, "分类名称不能为空");
                return;
            }
            new CategoryService().update(c.id, name);
        }

        if(b == p.bDelete){
            Category category = p.getSelectedCategory();
            if(category.recordNumber != 0){
                JOptionPane.showMessageDialog(p, "本分类下有记录存在，不能删除");
                return;
            }

            if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确认要删除?"))
                return;

            new CategoryService().delete(category.id);
        }

        p.updateData();
    }
}
