package cn.edu.stu.max.cocovendor.javaClass;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.stu.max.cocovendor.databaseClass.Goods;
import cn.edu.stu.max.cocovendor.databaseClass.Sales;

public class SalesFactory {
    void create() throws NullPointerException {
        Sales sales = new Sales();
        sales.setSales_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        sales.setGoods_id(DataSupport.find(Goods.class,1).getId());
        sales.setGoods_name(DataSupport.find(Goods.class,1).getName());
        sales.setMachine_floor(1);
        sales.setPay_way("支付宝");
        sales.save();
    }
}
