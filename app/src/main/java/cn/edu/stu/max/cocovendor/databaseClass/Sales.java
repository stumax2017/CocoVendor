package cn.edu.stu.max.cocovendor.databaseClass;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sales extends DataSupport{
    private int id;
    //销售物品对应货物的id
    private int goods_id;
    //销售物品名字
    private String goods_name;
    //销售日期
    private String sales_date;
    //销售货物的层数
    private int machine_floor;
    //是否开孔
    private boolean is_hole;
    //是否提供吸管
    private boolean is_straw;
    //支付方式
    private String pay_way;
    //销售机器编号
    private long sales_machine_id;
    //交易编号
    private String trade_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getSales_date() {
        return sales_date;
    }

    public void setSales_date(SimpleDateFormat sdf) {
        String sales_date = sdf.format(new java.util.Date());
        this.sales_date = sales_date;
    }

    public int getMachine_floor() {
        return machine_floor;
    }

    public void setMachine_floor(int machine_floor) {
        this.machine_floor = machine_floor;
    }

    public boolean is_hole() {
        return is_hole;
    }

    public void setIs_hole(boolean is_hole) {
        this.is_hole = is_hole;
    }

    public boolean is_straw() {
        return is_straw;
    }

    public void setIs_straw(boolean is_straw) {
        this.is_straw = is_straw;
    }

    public String getPay_way() {
        return pay_way;
    }

    public void setPay_way(String pay_way) {
        this.pay_way = pay_way;
    }

    public long getSales_machine_id() {
        return sales_machine_id;
    }

    public void setSales_machine_id(long sales_machine_id) {
        this.sales_machine_id = sales_machine_id;
    }

    public String getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(String trade_id) {
        this.trade_id = trade_id;
    }
}
