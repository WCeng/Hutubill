package gui.panel;

import entity.Category;
import gui.listener.CategoryListener;
import gui.model.CategoryTableModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.net.BindException;

public class CategoryPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static CategoryPanel instance = new CategoryPanel();

    public CategoryTableModel tableModel = new CategoryTableModel();
    public JTable table = new JTable(tableModel);

    public JButton bInsert = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");

    public CategoryPanel(){
        GUIUtil.setColor(ColorUtil.blueColor, bInsert, bEdit, bDelete);

        JScrollPane sp = new JScrollPane(table);

        JPanel pButton = new JPanel();

        pButton.add(bInsert);
        pButton.add(bEdit);
        pButton.add(bDelete);

        this.setLayout(new BorderLayout());
        this.add(sp, BorderLayout.CENTER);
        this.add(pButton, BorderLayout.SOUTH);

        addListener();

    }

    public static void main(String[] args) {
        GUIUtil.showPanel(CategoryPanel.instance);
    }

    public Category getSelectedCategory(){
        int index = table.getSelectedRow();
        return tableModel.cs.get(index);
    }

    public void updateData(){
        tableModel.cs = new CategoryService().list();
        table.updateUI();
        table.getSelectionModel().setSelectionInterval(0, 0);

        if(tableModel.cs.size() == 0){
            bDelete.setEnabled(false);
            bEdit.setEnabled(false);
        }else {
            bDelete.setEnabled(true);
            bEdit.setEnabled(true);
        }
    }

    public void addListener(){
        CategoryListener cl = new CategoryListener();
        bInsert.addActionListener(cl);
        bEdit.addActionListener(cl);
        bDelete.addActionListener(cl);
    }

}
