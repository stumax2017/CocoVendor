package cn.edu.stu.max.cocovendor.databaseClass;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;

/**
 * Created by 0 on 2017/11/15.
 */

public class CabinetDailySales extends DataSupport {

    //货柜日销售数量
    private long cabinetDailySalesNum;
    //货柜日销售日期
    private String cabinetDailySalesDate;
    //货柜日销售总金额
    private double cabinetDailySalesTotalMoney;

    public long getCabinetDailySalesNum() {
        return cabinetDailySalesNum;
    }

    public void setCabinetDailySalesNum(long cabinetDailySalesNum) {
        this.cabinetDailySalesNum = cabinetDailySalesNum;
    }

    public String getCabinetDailySalesDate() {
        return cabinetDailySalesDate;
    }

    public void setCabinetDailySalesDate(SimpleDateFormat sdf) {
        String cabinetDailySalesDate = sdf.format(new java.util.Date());
        this.cabinetDailySalesDate = cabinetDailySalesDate;
    }

    public double getCabinetDailySalesTotalMoney() {
        return cabinetDailySalesTotalMoney;
    }

    public void setCabinetDailySalesTotalMoney(double cabinetDailySalesTotalMoney) {
        this.cabinetDailySalesTotalMoney = cabinetDailySalesTotalMoney;
    }

}
