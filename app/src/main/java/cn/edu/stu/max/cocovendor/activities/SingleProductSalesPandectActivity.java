package cn.edu.stu.max.cocovendor.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.databaseClass.Goods;
import cn.edu.stu.max.cocovendor.adapters.SingleProductSalesPandectAdapter;
import cn.edu.stu.max.cocovendor.databaseClass.SingleProductSalesPandect;

public class SingleProductSalesPandectActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSalesAnalyze;
    private SingleProductSalesPandectAdapter singleProductSalesPandectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product_sales_pandect);

        List<SingleProductSalesPandect> allGoods = DataSupport.findAll(SingleProductSalesPandect.class);
        SharedPreferences sharedPreferences = getSharedPreferences("cabinet_daily_sales_file", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();   // 设置为可编辑模式

        //找到UI控件
        recyclerViewSalesAnalyze = (RecyclerView) findViewById(R.id.rv_sales_setting);
        //设置线性布局 Creates a vertical LinearLayoutManager
        recyclerViewSalesAnalyze.setLayoutManager(new LinearLayoutManager(this));
        //设置recyclerView每个item间的分割线
        recyclerViewSalesAnalyze.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //创建recyclerView的实例，并将数据传输到适配器
        singleProductSalesPandectAdapter = new SingleProductSalesPandectAdapter(allGoods, this);
        //recyclerView显示适配器内容
        recyclerViewSalesAnalyze.setAdapter(singleProductSalesPandectAdapter);

        TextView textTotalNum = (TextView) findViewById(R.id.text_total_num);
        TextView textTotalCash = (TextView) findViewById(R.id.text_total_cash);
        TextView textTotalWechat = (TextView) findViewById(R.id.text_total_wechat);
        TextView textTotalAlipay = (TextView) findViewById(R.id.text_total_alipay);
        TextView textTotalSum = (TextView) findViewById(R.id.text_total_sum);

        textTotalNum.setText(String.valueOf(DataSupport.sum(SingleProductSalesPandect.class, "goodsSalesNum", int.class)));
        textTotalCash.setText(String.valueOf(DataSupport.sum(SingleProductSalesPandect.class, "cashTimes", int.class)));
        textTotalWechat.setText(String.valueOf(DataSupport.sum(SingleProductSalesPandect.class, "wechatTimes", int.class)));
        textTotalAlipay.setText(String.valueOf(DataSupport.sum(SingleProductSalesPandect.class, "alipayTimes", int.class)));
        textTotalSum.setText(String.valueOf(DataSupport.sum(SingleProductSalesPandect.class, "salesAll", float.class)));

        Button buttonSingleProductSalesPandectReturn = (Button) findViewById(R.id.btn_single_product_sales_pandect_return);
        buttonSingleProductSalesPandectReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
