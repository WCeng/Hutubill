package gui.panel;

import gui.page.SpendPage;
import service.SpendService;
import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class SpendPanel extends WorkingPanel {

    static {
        GUIUtil.useLNF();
    }

    public static SpendPanel instance = new SpendPanel();

    public JLabel lDaySpend = new JLabel("日均消费");
    public JLabel lMonthLeft = new JLabel("本月剩余");
    public JLabel lDayUsed = new JLabel("日均可用");
    public JLabel lMonthEnd = new JLabel("距离月末");
    public JLabel s1 = new JLabel("￥120");
    public JLabel s2 = new JLabel("￥2084");
    public JLabel s3 = new JLabel("￥389");
    public JLabel s4 = new JLabel("15天");

    public CircleProgressBar cpb;

    public JLabel lThisMonthSpend = new JLabel("本月消费");
    public JLabel lThisDaySpend = new JLabel("今日消费");
    public JLabel ltms = new JLabel("￥2300");
    public JLabel ltds = new JLabel("￥25");

    public SpendPanel(){
        this.setLayout(new BorderLayout());
        cpb = new CircleProgressBar();
        cpb.setBackgroundColor(ColorUtil.blueColor);

        GUIUtil.setColor(ColorUtil.grayColor, lDaySpend, lMonthLeft, lDayUsed, lMonthEnd
        , s1, s2, s3, s4, lThisDaySpend, lThisMonthSpend);
        GUIUtil.setColor(ColorUtil.blueColor, ltds, ltms);
        ltms.setFont(new Font("微软雅黑", Font.BOLD, 23));
        ltds.setFont(new Font("微软雅黑", Font.BOLD, 23));

        this.add(south(), BorderLayout.SOUTH);
        this.add(center(), BorderLayout.CENTER);
    }

    private JPanel south(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,4));
        p.add(lDaySpend);
        p.add(lMonthLeft);
        p.add(lDayUsed);
        p.add(lMonthEnd);
        p.add(s1);
        p.add(s2);
        p.add(s3);
        p.add(s4);

        return p;
    }

    private JPanel center(){
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(center2(), BorderLayout.CENTER);
        p.add(west(), BorderLayout.WEST);

        return p;
    }

    private Component center2(){
        return cpb;
    }

    private JPanel west(){
        JPanel west = new JPanel();
        west.setLayout(new GridLayout(4, 1));
        west.add(lThisMonthSpend);
        west.add(ltms);
        west.add(lThisDaySpend);
        west.add(ltds);

        return west;
    }


    @Override
    public void updateData() {
        SpendPage spend = new SpendService().getSpendPage();
        ltms.setText(spend.ltms);
        ltds.setText(spend.ltds);
        s1.setText(spend.s1);
        s2.setText(spend.s2);
        s3.setText(spend.s3);
        s4.setText(spend.s4);

        cpb.setProgress(spend.usagePercentage);

        if(spend.isOverSpend){
            ltms.setForeground(ColorUtil.warningColor);
            ltds.setForeground(ColorUtil.warningColor);
            s2.setForeground(ColorUtil.warningColor);
        }else{
            ltms.setForeground(ColorUtil.blueColor);
            ltds.setForeground(ColorUtil.blueColor);
            s2.setForeground(ColorUtil.grayColor);
        }
        cpb.setForegroundColor(ColorUtil.getByPercentage(spend.usagePercentage));
        addListener();

    }

    @Override
    public void addListener() {

    }
}
