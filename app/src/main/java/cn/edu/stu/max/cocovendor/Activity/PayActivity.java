package cn.edu.stu.max.cocovendor.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.IOException;

import cn.edu.stu.max.cocovendor.JavaClass.OkHttpUtil;
import cn.edu.stu.max.cocovendor.R;

public class PayActivity extends AppCompatActivity {

    private TextView textViewPay = (TextView) findViewById(R.id.tv_pay);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pay);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpUtil okHttpUtil = new OkHttpUtil();
                    String response = okHttpUtil.get("https://api.mch.weixin.qq.com/pay/unifiedorder");
                    textViewPay.setText(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
