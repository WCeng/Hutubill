package service;

import dao.RecordDAO;
import entity.Record;
import gui.page.SpendPage;
import util.DateUtil;

import java.util.List;

public class SpendService {

    public SpendPage getSpendPage() {
        RecordDAO recordDAO = new RecordDAO();

//          本月总记录
        List<Record> thisMonthRecords = recordDAO.listThisMonth();
//        今天记录
        List<Record> thisDayRecords = recordDAO.listToday();
//        这个月总天数
        int thisMonthTotalDay = DateUtil.thisMonthTotalDay();

//        本月消费
        int ltms = 0;
//        今日消费
        int ltds = 0;
//        日均消费
        int s1 = 0;
//        本月剩余
        int s2 = 0;
//        日均可用
        int s3 = 0;
//        距离月末
        int s4 = 0;
//        使用比例
        int usagePercentage = 0;

//        本月预算
        int monthBudget = new ConfigService().getIntBudget();


//          本月总消费多少元
        for(Record record : thisMonthRecords){
            ltms += record.getSpend();
        }
//          今日消费
        for(Record record : thisDayRecords){
            ltds += record.getSpend();
        }
//          计算日均消费
        s1 = ltms / thisMonthTotalDay;
//        计算本月剩余钱
        s2 = monthBudget - ltms;
//        距离月末
        s4 = DateUtil.thisMonthLeftDay();
//        计算日均可用
        s3 = s2/s4;
//        计算使用比例
        usagePercentage = ltms*100/monthBudget;

        return new SpendPage(ltms, ltds, s1, s2, s3, s4, usagePercentage);
    }
}
