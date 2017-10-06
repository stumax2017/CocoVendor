package cn.edu.stu.max.cocovendor.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.Date;

import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.databaseClass.LocalInfo;
import cn.edu.stu.max.cocovendor.databaseClass.Sales;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        TextView textViewHomepageTestlogin = (TextView) findViewById(R.id.tv_homepage_testlogin);
        textViewHomepageTestlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
                intent.putExtra("IsLogin", true);
                startActivity(intent);
            }
        });

        ImageView imageViewGoods1 = (ImageView) findViewById(R.id.iv_goods_1);
        imageViewGoods1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sales sales = new Sales();
                sales.setSales_date(new Date());
                sales.setMachine_floor(1);
                sales.save();
            }
        });
        ImageView imageViewGoods2 = (ImageView) findViewById(R.id.iv_goods_2);
        imageViewGoods2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sales sales = new Sales();
                sales.setSales_date(new Date());
                sales.setMachine_floor(2);
                sales.save();
            }
        });
        ImageView imageViewGoods3 = (ImageView) findViewById(R.id.iv_goods_3);
        imageViewGoods3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sales sales = new Sales();
                sales.setSales_date(new Date());
                sales.setMachine_floor(3);
                sales.save();
            }
        });
        ImageView imageViewGoods4 = (ImageView) findViewById(R.id.iv_goods_4);
        imageViewGoods4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sales sales = new Sales();
                sales.setSales_date(new Date());
                sales.setMachine_floor(4);
                sales.save();
            }
        });
        ImageView imageViewGoods5 = (ImageView) findViewById(R.id.iv_goods_5);
        imageViewGoods5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sales sales = new Sales();
                sales.setSales_date(new Date());
                sales.setMachine_floor(5);
                sales.save();
            }
        });
        ImageView imageViewGoods6 = (ImageView) findViewById(R.id.iv_goods_6);
        imageViewGoods6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sales sales = new Sales();
                sales.setSales_date(new Date());
                sales.setMachine_floor(6);
                sales.save();
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

    //重写返回按键内容
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
