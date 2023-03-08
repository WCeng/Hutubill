package gui.panel;

import entity.Category;
import gui.listener.RecordListener;
import gui.model.CategoryComboBoxModel;
import org.jdesktop.swingx.JXDatePicker;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class RecordPanel extends WorkingPanel{
    static {
        GUIUtil.useLNF();
    }

    public static RecordPanel instance = new RecordPanel();

    JLabel lSpend = new JLabel("花费(￥)");
    JLabel lCategory = new JLabel("分类");
    JLabel lRemark = new JLabel("备注");
    JLabel lDate = new JLabel("日期");
    public JTextField tfSpend = new JTextField("0");

    //    分类集合模型
    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    //    下拉框
    public JComboBox<Category> comboBox = new JComboBox<>(cbModel);

    public JTextField tfRemark = new JTextField();
    public JXDatePicker datePicker = new JXDatePicker(new Date());
    JButton b = new JButton("记一笔");

    public RecordPanel(){
        GUIUtil.setColor(ColorUtil.grayColor, lSpend,lCategory,lRemark,lDate);
        GUIUtil.setColor(ColorUtil.blueColor, b);
        JPanel pInput =new JPanel();
        JPanel pSubmit = new JPanel();
        int gap = 40;
        pInput.setLayout(new GridLayout(4,2,gap,gap));

        pInput.add(lSpend);
        pInput.add(tfSpend);
        pInput.add(lCategory);
        pInput.add(comboBox);
        pInput.add(lRemark);
        pInput.add(tfRemark);
        pInput.add(lDate);
        pInput.add(datePicker);

        pSubmit.add(b);

        this.setLayout(new BorderLayout());
        this.add(pInput,BorderLayout.NORTH);
        this.add(pSubmit,BorderLayout.CENTER);

        addListener();

    }

    public Category getSelectedCategory(){
        return (Category) comboBox.getSelectedItem();
    }


    public static void main(String[] args) {
        GUIUtil.showPanel(RecordPanel.instance);
    }

    @Override
    public void updateData() {
        cbModel.cs = new CategoryService().list();
        comboBox.updateUI();
        resetInput();
        tfSpend.grabFocus();
    }

    public void resetInput(){
        tfSpend.setText("0");
        tfRemark.setText("");
        if(0 != cbModel.cs.size())
            comboBox.setSelectedIndex(0);
        datePicker.setDate(new Date());
    }

    @Override
    public void addListener() {
        RecordListener rl = new RecordListener();
        b.addActionListener(rl);
    }
}
