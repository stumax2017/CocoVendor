package cn.edu.stu.max.cocovendor.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import org.litepal.LitePal;

import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.databaseClass.LocalInfo;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化数据库
        LitePal.initialize(this);
        setContentView(R.layout.activity_start);

        //判断程序是否首次启动，配置默认设置
        SharedPreferences preferences = getSharedPreferences("default_setting", MODE_PRIVATE);
        if (preferences.getBoolean("firstRun", true)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstRun", false);
            editor.apply();
            Toast.makeText(StartActivity.this, "第一次启动", Toast.LENGTH_SHORT).show();

            LocalInfo localInfo = new LocalInfo();
            fillLocalInfo(localInfo);
        }

        //尝试定位操作
        AMapLocationClient mLocationClient = new AMapLocationClient(this);
        mLocationClient.setLocationListener(mLocationListener);
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(false);
        mLocationOption.setInterval(3000);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 2000ms
                Intent intent = new Intent(StartActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }

    private AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    TextView textView = (TextView) findViewById(R.id.tv_homepage_testlogin);
                    textView.setText(aMapLocation.getAddress());
                } else {
                }
            }
        }
    };

    private void fillLocalInfo(LocalInfo localInfo) {
        localInfo.setMachine_id(1);
        localInfo.setIp("10.28.34.5");
        localInfo.setMac_address("64-00-6A-76-46-60");
        localInfo.setServer_ip("127.0.0.1");
        localInfo.setLanguage("Chinese");
        localInfo.setLocal_address("未知");
        localInfo.setDefault_password("123");
        localInfo.setLogin_password("123");
        localInfo.setVersion(1);
        localInfo.setTel_number("0750-1234567");
        localInfo.setAd_rules(1);
        localInfo.save();
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
