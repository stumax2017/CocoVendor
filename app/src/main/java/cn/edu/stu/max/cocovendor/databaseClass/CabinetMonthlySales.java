package cn.edu.stu.max.cocovendor.databaseClass;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;

/**
 * Created by 0 on 2017/11/15.
 */

public class CabinetMonthlySales extends DataSupport {

    //货柜日销售数量
    private long cabinetMonthlySalesNum;
    //货柜日销售日期
    private String cabinetMonthlySalesDate;
    //货柜日销售总金额
    private double cabinetMonthlySalesTotalMoney;

    public long getCabinetMonthlySalesNum() {
        return cabinetMonthlySalesNum;
    }

    public void setCabinetMonthlySalesNum(long cabinetMonthlySalesNum) {
        this.cabinetMonthlySalesNum = cabinetMonthlySalesNum;
    }

    public String getCabinetMonthlySalesDate() {
        return cabinetMonthlySalesDate;
    }

    public void setCabinetMonthlySalesDate(SimpleDateFormat sdf) {
        String cabinetMonthlySalesDate = sdf.format(new java.util.Date());
        this.cabinetMonthlySalesDate = cabinetMonthlySalesDate;
    }

    public double getCabinetMonthlySalesTotalMoney() {
        return cabinetMonthlySalesTotalMoney;
    }

    public void setCabinetMonthlySalesTotalMoney(double cabinetMonthlySalesTotalMoney) {
        this.cabinetMonthlySalesTotalMoney = cabinetMonthlySalesTotalMoney;
    }

}
