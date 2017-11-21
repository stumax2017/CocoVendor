package cn.edu.stu.max.cocovendor.databaseClass;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;

/**
 * Created by 0 on 2017/11/21.
 */

public class SingleProductDailySales extends DataSupport {

    // 关联类
    private SingleProductSalesAnalyze singleProductSalesAnalyze;
    // 单品日销售日期
    private String singleProductDailySalesDate;
    // 单品日销售数量
    private int singleProductDailySalesNum;
    // 单品日销售总额
    private float singleProductDailySalesTotalMoney;

    public SingleProductSalesAnalyze getSingleProductSalesAnalyze() {
        return singleProductSalesAnalyze;
    }
    public void setSingleProductSalesAnalyze(SingleProductSalesAnalyze singleProductSalesAnalyze) {
        this.singleProductSalesAnalyze = singleProductSalesAnalyze;
    }

    public String getSingleProductDailySalesDate() {return singleProductDailySalesDate;}
    public void setSingleProductDailySalesDate(SimpleDateFormat sdf) {
        String singleProductDailySalesDate = sdf.format(new java.util.Date());
        this.singleProductDailySalesDate = singleProductDailySalesDate;
    }

    public int getSingleProductDailySalesNum() { return singleProductDailySalesNum;}
    public void setSingleProductDailySalesNum(int singleProductDailySalesNum) {
        this.singleProductDailySalesNum = singleProductDailySalesNum;
    }

    public double getSingleProductDailySalesTotalMoney() {return singleProductDailySalesTotalMoney;}
    public void setSingleProductDailySalesTotalMoney(float singleProductDailySalesTotalMoney) {
        this.singleProductDailySalesTotalMoney = singleProductDailySalesTotalMoney;
    }
}
