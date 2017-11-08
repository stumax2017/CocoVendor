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

import cn.edu.stu.max.cocovendor.adapters.SalesSettingAdapter;
import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.databaseClass.Goods;

public class SheetGoodsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSalesSetting;
    private SalesSettingAdapter salesSettingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_goods);

        //加入返回箭头
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<Goods> allGoods = DataSupport.findAll(Goods.class);

        recyclerViewSalesSetting = (RecyclerView) findViewById(R.id.rv_sales_setting);
        //设置线性布局 Creates a vertical LinearLayoutManager
        recyclerViewSalesSetting.setLayoutManager(new LinearLayoutManager(this));
        //设置recyclerView每个item间的分割线
        recyclerViewSalesSetting.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //创建recyclerView的实例，并将数据传输到适配器
        salesSettingAdapter = new SalesSettingAdapter(allGoods, SheetGoodsActivity.this);
        //recyclerView显示适配器内容
        recyclerViewSalesSetting.setAdapter(salesSettingAdapter);

        Button buttonSalesSettingReturn = (Button) findViewById(R.id.btn_sales_setting_return);
        buttonSalesSettingReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //实现返回功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
