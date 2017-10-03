package cn.edu.stu.max.cocovendor.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.litepal.crud.DataSupport;

import java.util.List;

import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.JavaClass.SalesSettingAdapter;
import cn.edu.stu.max.cocovendor.databaseClass.Goods;

public class SalesSettingActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSalesSetting;
    private SalesSettingAdapter salesSettingAdapter;
    private int pageOffset = 0;
    private int listRows = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_setting);

        List<Goods> allGoods = DataSupport.findAll(Goods.class);

        recyclerViewSalesSetting = (RecyclerView) findViewById(R.id.rv_sales_setting);
        //设置线性布局 Creates a vertical LinearLayoutManager
        recyclerViewSalesSetting.setLayoutManager(new LinearLayoutManager(this));
        //设置recyclerView每个item间的分割线
        recyclerViewSalesSetting.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //创建recyclerView的实例，并将数据传输到适配器
        salesSettingAdapter = new SalesSettingAdapter(allGoods);
        //recyclerView显示适配器内容
        recyclerViewSalesSetting.setAdapter(salesSettingAdapter);
    }

    //活动转换之间都调用沉浸模式全屏
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
