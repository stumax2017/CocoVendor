package cn.edu.stu.max.cocovendor.Activity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.FindMultiCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.edu.stu.max.cocovendor.JavaClass.AddGoodsDialog;
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

        Button buttonSalesSettingAdd = (Button) findViewById(R.id.btn_sales_setting_add);
        buttonSalesSettingAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList goodsSelectedItems = new ArrayList();
                AlertDialog.Builder builder = new AlertDialog.Builder(SalesSettingActivity.this);
                builder.setTitle(R.string.label_add_goods)
                        .setMultiChoiceItems(R.array.goods_array, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    goodsSelectedItems.add(which);
                                } else if (goodsSelectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    goodsSelectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                        .setPositiveButton(R.string.label_save, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // FIRE ZE MISSILES!
                                for (int i = 0; i < goodsSelectedItems.size(); i ++) {
                                    Goods goods = new Goods();
                                    goods.setName(getResources().getStringArray(R.array.goods_array)[i]);
                                    goods.save();
                                }
                                DataSupport.findAllAsync(Goods.class).listen(new FindMultiCallback() {
                                    @Override
                                    public <T> void onFinish(List<T> t) {
                                        List<Goods> allGoods = (List<Goods>) t;
                                        salesSettingAdapter = new SalesSettingAdapter(allGoods);
                                        //recyclerView显示适配器内容
                                        recyclerViewSalesSetting.setAdapter(salesSettingAdapter);
                                    }
                                });
                            }
                        })
                        .setNegativeButton(R.string.label_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                builder.setCancelable(false);
                builder.create().show();
            }
        });
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
