package cn.edu.stu.max.cocovendor.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.adapters.CabinetDailySalesAnalyzeAdapter;
import cn.edu.stu.max.cocovendor.adapters.CabinetMonthlySalesAnalyzeAdapter;
import cn.edu.stu.max.cocovendor.databaseClass.CabinetMonthlySales;
import cn.edu.stu.max.cocovendor.databaseClass.Goods;
import cn.edu.stu.max.cocovendor.adapters.DailySalesAdapter;

public class CabinetMonthlySalesAnalyzeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSalesAnalyze;
    private CabinetMonthlySalesAnalyzeAdapter cabinetMonthlySalesAnalyzeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_cabinet_monthly_sales_analyze);

        List<CabinetMonthlySales> allSales = DataSupport.findAll(CabinetMonthlySales.class);

        //找到UI控件
        recyclerViewSalesAnalyze = (RecyclerView) findViewById(R.id.rv_sales_setting);
        //设置线性布局 Creates a vertical LinearLayoutManager
        recyclerViewSalesAnalyze.setLayoutManager(new LinearLayoutManager(this));
        //设置recyclerView每个item间的分割线
        recyclerViewSalesAnalyze.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //创建recyclerView的实例，并将数据传输到适配器
        cabinetMonthlySalesAnalyzeAdapter = new CabinetMonthlySalesAnalyzeAdapter(allSales);
        //recyclerView显示适配器内容
        recyclerViewSalesAnalyze.setAdapter(cabinetMonthlySalesAnalyzeAdapter);

        Button buttonCabinetMonthlySalesReturn = (Button) findViewById(R.id.btn_cabinet_monthly_sales_return);
        buttonCabinetMonthlySalesReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
