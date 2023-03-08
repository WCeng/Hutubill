package gui.panel;

import entity.Record;
import service.ReportService;
import util.ChartUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.Image;
import java.util.List;

public class ReportPanel extends WorkingPanel{
    static {
        GUIUtil.useLNF();
    }

    public static ReportPanel instance = new ReportPanel();
    public JLabel l = new JLabel();

    public ReportPanel() {
//        this.setLayout(new BorderLayout());
        Image image = ChartUtil.getImage(400, 300);
        ImageIcon imageIcon = new ImageIcon(image);
        l.setIcon(imageIcon);

        this.add(l);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(instance);
    }

    @Override
    public void updateData() {
        List<Record> rs = new ReportService().listThisMonthRecords();
        Image i = ChartUtil.getImage(rs, 350, 250);
        ImageIcon icon = new ImageIcon(i);
        l.setIcon(icon);
    }

    @Override
    public void addListener() {

    }
}
