package cn.edu.stu.max.cocovendor.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.adapters.CabinetDailySalesAnalyzeAdapter;
import cn.edu.stu.max.cocovendor.databaseClass.Goods;
import cn.edu.stu.max.cocovendor.adapters.DailySalesAdapter;

public class CabinetDailySalesAnalyzeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSalesAnalyze;
    private CabinetDailySalesAnalyzeAdapter cabinetDailySalesAnalyzeAdapter;
    private int pageOffset = 0;
    private int listRows = 10;
    private List<Goods> list = new ArrayList<Goods>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_cabinet_daily_sales_analyze);

        List<Goods> allGoods = DataSupport.findAll(Goods.class);
//        for (int i = 0; i < 22; i ++) {
//
//            Goods goods = new Goods();
//            goods.setName(getResources().getStringArray(R.array.goods_name_array)[i]);
//            goods.setCost_price((float) getResources().getIntArray(R.array.goods_price_array)[i] / 10.0f);
//            goods.setSales_price(goods.getCost_price());
//            goods.setImage_path(getResources().getIdentifier("ic_category_" + i, "drawable", getPackageName()));
//            goods.setNum(5);
//            goods.setOnSale(false);
//            goods.save();
//
//            list.add(goods);
//        }
        //找到UI控件
        recyclerViewSalesAnalyze = (RecyclerView) findViewById(R.id.rv_sales_setting);
        //设置线性布局 Creates a vertical LinearLayoutManager
        recyclerViewSalesAnalyze.setLayoutManager(new LinearLayoutManager(this));
        //设置recyclerView每个item间的分割线
        recyclerViewSalesAnalyze.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //创建recyclerView的实例，并将数据传输到适配器
        cabinetDailySalesAnalyzeAdapter = new CabinetDailySalesAnalyzeAdapter(list, this);
        //recyclerView显示适配器内容
        recyclerViewSalesAnalyze.setAdapter(cabinetDailySalesAnalyzeAdapter);
    }
}
