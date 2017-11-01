package cn.edu.stu.max.cocovendor.Activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.FindMultiCallback;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import cn.edu.stu.max.cocovendor.JavaClass.AddGoodsDialog;
import cn.edu.stu.max.cocovendor.JavaClass.ToastFactory;
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
        salesSettingAdapter = new SalesSettingAdapter(allGoods);
        //recyclerView显示适配器内容
        recyclerViewSalesSetting.setAdapter(salesSettingAdapter);

        Button buttonSalesSettingAdd = (Button) findViewById(R.id.btn_sales_setting_add);
        buttonSalesSettingAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                final View view = inflater.inflate(R.layout.add_goods_dialog, (ViewGroup) findViewById(R.id.ll_add_goods));
                final int[] index = new int[1];
                final float[] goodsPrice = new float[1];
                AlertDialog.Builder builder = new AlertDialog.Builder(SalesSettingActivity.this);
                builder.setTitle(R.string.label_add_goods)
                        .setSingleChoiceItems(R.array.goods_array, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder builderIn = new AlertDialog.Builder(SalesSettingActivity.this);
                                if (view.getParent() != null) {
                                    ((ViewGroup) view.getParent()).removeView(view);
                                }
                                builderIn.setView(view);
                                builderIn.setTitle("价格设置")
                                        .setPositiveButton(R.string.label_save, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {
                                                // FIRE ZE MISSILES!
                                                EditText editTextPrice = (EditText) view.findViewById(R.id.ed_goods_price);
                                                try {
                                                    goodsPrice[0] = Float.valueOf(editTextPrice.getText().toString().trim());
                                                } catch (NumberFormatException e) {
                                                    ToastFactory.makeText(SalesSettingActivity.this, "没有输入价钱", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        })
                                        .setNegativeButton(R.string.label_cancel, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {
                                                // User cancelled the dialog
                                            }
                                        });
                                builderIn.setCancelable(false);
                                TextView textView = (TextView) view.findViewById(R.id.label_goods_name);
                                textView.setText(getResources().getStringArray(R.array.goods_array)[which]);
                                index[0] = which;
                                builderIn.create().show();
                            }
                        })
                        .setPositiveButton(R.string.label_save, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // FIRE ZE MISSILES!
                                try {
                                    Goods goods = new Goods();
                                    goods.setName(getResources().getStringArray(R.array.goods_array)[index[0]]);
                                    goods.setSales_price(goodsPrice[0]);
                                    goods.save();
                                } catch (IllegalStateException e) {
                                    ToastFactory.makeText(SalesSettingActivity.this, "已经存在这种商品了", Toast.LENGTH_SHORT).show();
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
        Button buttonSalesSettingDel = (Button) findViewById(R.id.btn_sales_setting_del);
        buttonSalesSettingDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastFactory.makeText(SalesSettingActivity.this, "按下删除", Toast.LENGTH_SHORT).show();
                Goods toChangeGoods = new Goods();
                toChangeGoods.setToDefault("quanlity");
                toChangeGoods.updateAll();
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
        });
        Button buttonSalesSettingFix = (Button) findViewById(R.id.btn_sales_setting_fix);
        buttonSalesSettingFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<Long> goodsSelectedItems = new ArrayList<>();
                AlertDialog.Builder builder = new AlertDialog.Builder(SalesSettingActivity.this);
                builder.setTitle("补充商品库存")
                        .setMultiChoiceItems(R.array.goods_array, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    goodsSelectedItems.add((long)which);
                                } else if (goodsSelectedItems.contains((long)which)) {
                                    // Else, if the item is already in the array, remove it
                                    goodsSelectedItems.remove(Long.valueOf((long)which));
                                }
                            }
                        })
                        .setPositiveButton(R.string.label_save, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // FIRE ZE MISSILES!
                                for (int i = 0; i < goodsSelectedItems.size(); i ++) {
                                    try {
                                        Goods toChangeGoods = DataSupport.find(Goods.class, goodsSelectedItems.get(i).longValue() + 1);
                                        toChangeGoods.setQuanlity(10);
                                        toChangeGoods.save();
                                    } catch (NullPointerException e) {
                                        ToastFactory.makeText(SalesSettingActivity.this, "所需商品并未上架", Toast.LENGTH_SHORT).show();
                                    }
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
