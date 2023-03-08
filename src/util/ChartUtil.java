package util;

import com.objectplanet.chart.BarChart;
import com.objectplanet.chart.Chart;
import com.objectplanet.chart.LineChart;
import entity.Record;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Arrays;
import java.util.List;

public class ChartUtil {

    private static double[] sampleValues(){
        double[] d = new double[30];
        for (int i = 0; i < d.length; i++) {
            d[i] = (int)(Math.random()*300);
        }
        return d;
    }

    private static String[] sampleLabels(){
        String[] sampleLabels = new String[30];
        for (int i = 0; i < sampleLabels.length; i++) {
            if(0 == i%5)
                sampleLabels[i] = (i+1)+"日";
        }
        return sampleLabels;
    }

    private static int max(double[] sampleValues){
        double[] values = sampleValues();
        int max = 0;
        for (int i = 0; i < values.length; i++) {
            if(values[i] > max)
                max = (int)values[i];
        }
        return max;
    }

    public static Image getImage(int width, int height) {
        // 模拟样本数据
        double[] sampleValues = sampleValues();
        // 下方显示的文字
        String[] sampleLabels = sampleLabels();
        // 样本中的最大值
        int max = max(sampleValues);

        // 数据颜色
        Color[] sampleColors = new Color[] { ColorUtil.blueColor };

        // 柱状图
        BarChart chart = new BarChart();

        // 设置样本个数
        chart.setSampleCount(sampleValues.length);
        // 设置样本数据
        chart.setSampleValues(0, sampleValues);
        // 设置文字
        chart.setSampleLabels(sampleLabels);
        // 设置样本颜色
        chart.setSampleColors(sampleColors);
        // 设置取值范围
        chart.setRange(0, max * 1.2);
        // 显示背景横线
        chart.setValueLinesOn(true);
        // 显示文字
        chart.setSampleLabelsOn(true);
        // 把文字显示在下方
        chart.setSampleLabelStyle(Chart.BELOW);

        // 样本值的字体
        chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12));
        // 显示图例说明
        chart.setLegendOn(true);
        // 把图例说明放在左侧
        chart.setLegendPosition(Chart.LEFT);
        // 图例说明中的文字
        chart.setLegendLabels(new String[] { "月消费报表" });
        // 图例说明的字体
        chart.setFont("legendFont", new Font("Dialog", Font.BOLD, 13));
        // 下方文字的字体
        chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13));
        // 图表中间背景颜色
        chart.setChartBackground(Color.white);
        // 图表整体背景颜色
        chart.setBackground(ColorUtil.backgroundColor);
        // 把图表转换为Image类型
        Image im = chart.getImage(width, height);
        return im;
    }



    private static double[] sampleValues(List<Record> rs){
        double[] sampleValues = new double[rs.size()];
        for (int i = 0; i < sampleValues.length; i++) {
            sampleValues[i] = rs.get(i).spend;
        }
        return sampleValues;
    }

    private static String[] sampleLabels(List<Record> rs){
        String[] sampleLabels  = new String[rs.size()];
        for (int i = 0; i <sampleLabels.length ; i++) {
            if(0 == i%5){
                sampleLabels[i] = String.valueOf(i+1+"日");
            }
        }
        return sampleLabels;
    }

    public static Image getImage(List<Record> rs, int width, int height){
//        数据
        double[] sampleValues = sampleValues(rs);
//        下方显示的文字
        String[] sampleLabels = sampleLabels(rs);

        int max = max(sampleValues);
        // 数据颜色
        Color[] sampleColors = new Color[] { ColorUtil.blueColor };

        // 柱状图
        BarChart chart = new BarChart();

        // 设置样本个数
        chart.setSampleCount(sampleValues.length);
        // 设置样本数据
        chart.setSampleValues(0, sampleValues);
        // 设置文字
        chart.setSampleLabels(sampleLabels);
        // 设置样本颜色
        chart.setSampleColors(sampleColors);
        // 设置取值范围
        chart.setRange(0, max * 1.2);
        // 显示背景横线
        chart.setValueLinesOn(true);
        // 显示文字
        chart.setSampleLabelsOn(true);
        // 把文字显示在下方
        chart.setSampleLabelStyle(Chart.BELOW);

        // 样本值的字体
        chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12));
        // 显示图例说明
        chart.setLegendOn(true);
        // 把图例说明放在左侧
        chart.setLegendPosition(Chart.LEFT);
        // 图例说明中的文字
        chart.setLegendLabels(new String[] { "月消费报表" });
        // 图例说明的字体
        chart.setFont("legendFont", new Font("Dialog", Font.BOLD, 13));
        // 下方文字的字体
        chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13));
        // 图表中间背景颜色
        chart.setChartBackground(Color.white);
        // 图表整体背景颜色
        chart.setBackground(ColorUtil.backgroundColor);
        // 把图表转换为Image类型
        Image im = chart.getImage(width, height);
        return im;


    }

    public static void main(String[] args) {
        JPanel p = new JPanel();
        JLabel l = new JLabel();
        Image i = ChartUtil.getImage(400, 300);
        ImageIcon ii = new ImageIcon(i);
        l.setIcon(ii);
        p.add(l);

        GUIUtil.showPanel(p);
    }
}
