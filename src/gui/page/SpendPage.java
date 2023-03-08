package gui.page;

public class SpendPage {
    //    本月消费
    public String ltms;
    //    今日消费
    public String ltds;
    //    日均消费
    public String s1;
    //    本月剩余
    public String s2;
    //    日均可用
    public String s3;
    //    距离月末
    public String s4;
    //    使用比例
    public int usagePercentage;
    //    是否超支
    public boolean isOverSpend = false;

    public SpendPage(int ltms, int ltds, int s1, int s2, int s3, int s4, int usagePercentage){
        this.ltms = "￥"+ltms;
        this.ltds = "￥"+ltds;
        this.s1 = "￥"+s1;
        if(s2 < 0){
            isOverSpend = true;
        }
        if(!isOverSpend){
            this.s2 = "￥"+s2;
            this.s3 = "￥"+s3;
        }else{
            this.s2 = "超支"+(-s2);
            this.s3 = "￥0";
        }

        this.s4 = s4+"天";
        this.usagePercentage = usagePercentage;

    }
}
