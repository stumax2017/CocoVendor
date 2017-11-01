package cn.edu.stu.max.cocovendor.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import cn.edu.stu.max.cocovendor.JavaClass.ToastFactory;
import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.Service.VideoService;
import cn.edu.stu.max.cocovendor.databaseClass.LocalInfo;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化数据库
        LitePal.initialize(this);
        setContentView(R.layout.activity_start);

        startService(new Intent(StartActivity.this, VideoService.class));

        //判断程序是否首次启动，配置默认设置
        SharedPreferences preferences = getSharedPreferences("default_setting", MODE_PRIVATE);
        if (preferences.getBoolean("firstRun", true)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstRun", false);
            editor.apply();
            ToastFactory.makeText(StartActivity.this, "第一次启动", Toast.LENGTH_SHORT).show();

            LocalInfo localInfo = new LocalInfo();
            fillLocalInfo(localInfo);
        }
        Log.d("SHA1:", sHA1(this));

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

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length()-1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

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
