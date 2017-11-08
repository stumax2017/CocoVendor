package cn.edu.stu.max.cocovendor.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.databaseClass.Goods;
import cn.edu.stu.max.cocovendor.adapters.SingleProductSalesAnalyzeAdapter;

public class SingleProductSalesAnalyzeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSalesAnalyze;
    private SingleProductSalesAnalyzeAdapter singleProductSalesAnalyzeAdapter;
    private int pageOffset = 0;
    private int listRows = 10;
    private List<Goods> list = new ArrayList<Goods>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product_sales_analyze);

        List<Goods> allGoods = DataSupport.findAll(Goods.class);
        for (int i = 0; i < 22; i ++) {
            list.add(new Goods());
        }
        //找到UI控件
        recyclerViewSalesAnalyze = (RecyclerView) findViewById(R.id.rv_sales_setting);
        //设置线性布局 Creates a vertical LinearLayoutManager
        recyclerViewSalesAnalyze.setLayoutManager(new LinearLayoutManager(this));
        //设置recyclerView每个item间的分割线
        recyclerViewSalesAnalyze.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //创建recyclerView的实例，并将数据传输到适配器
        singleProductSalesAnalyzeAdapter = new SingleProductSalesAnalyzeAdapter(list, this);
        //recyclerView显示适配器内容
        recyclerViewSalesAnalyze.setAdapter(singleProductSalesAnalyzeAdapter);

//        Button btnDailySales = (Button) findViewById(R.id.btn_daily_sales);
//        btnDailySales.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SingleProductSalesAnalyzeActivity.this, DailySalesActivity.class);
//                startActivity(intent);
//            }
//        });

    }
}
