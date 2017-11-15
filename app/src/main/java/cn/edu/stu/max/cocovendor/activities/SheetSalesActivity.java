package cn.edu.stu.max.cocovendor.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;

import java.util.List;

import cn.edu.stu.max.cocovendor.adapters.SheetSalesAdapter;
import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.databaseClass.Sales;

public class SheetSalesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_sales);

        //加入返回箭头
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<Sales> allSales = DataSupport.findAll(Sales.class);

        RecyclerView recyclerViewSheetSales = (RecyclerView) findViewById(R.id.rv_sheet_sales);
        //设置线性布局 Creates a vertical LinearLayoutManager
        recyclerViewSheetSales.setLayoutManager(new LinearLayoutManager(this));
        //设置recyclerView每个item间的分割线
        recyclerViewSheetSales.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //创建recyclerView的实例，并将数据传输到适配器
        SheetSalesAdapter sheetSalesAdapter = new SheetSalesAdapter(allSales);
        //recyclerView显示适配器内容
        recyclerViewSheetSales.setAdapter(sheetSalesAdapter);

        Button buttonSalesReturn = (Button) findViewById(R.id.btn_sales__return);
        buttonSalesReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
