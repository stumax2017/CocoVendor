package cn.edu.stu.max.cocovendor.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.edu.stu.max.cocovendor.R;

public class SystemSettingActivity extends AppCompatActivity {

    private ButtonListener buttonListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);

        Button buttonSysParmSetting = (Button) findViewById(R.id.btn_sys_parm_setting);
        buttonSysParmSetting.setOnClickListener(buttonListener);
        Button buttonNetworkSetting = (Button) findViewById(R.id.btn_network_setting);
        buttonNetworkSetting.setOnClickListener(buttonListener);
        Button buttonUpdateGoods = (Button) findViewById(R.id.btn_update_goods);
        buttonUpdateGoods.setOnClickListener(buttonListener);
        Button buttonChangePswd = (Button) findViewById(R.id.btn_change_pswd);
        buttonChangePswd.setOnClickListener(buttonListener);
        Button buttonDownloadPic = (Button) findViewById(R.id.btn_download_pic);
        buttonDownloadPic.setOnClickListener(buttonListener);
        Button buttonChangeTel = (Button) findViewById(R.id.btn_change_tel);
        buttonChangeTel.setOnClickListener(buttonListener);
        Button buttonBasicData = (Button) findViewById(R.id.update_basic_data);
        buttonBasicData.setOnClickListener(buttonListener);
        Button buttonDownloadAd = (Button) findViewById(R.id.btn_download_ad);
        buttonDownloadAd.setOnClickListener(buttonListener);
        Button buttonSysSettingBack = (Button) findViewById(R.id.btn_sys_setting_back);
        buttonSysSettingBack.setOnClickListener(buttonListener);
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_system_setting:
                    break;
                case R.id.btn_sheet_setting:
                    break;
                case R.id.btn_goods_test:
                    break;
                case R.id.btn_ad_setting:
                    break;
                case R.id.btn_money_setting:
                    break;
                case R.id.btn_help:
                    break;
                case R.id.btn_sales_setting:
                    break;
                case R.id.btn_sales_analyze:
                    break;
                case R.id.btn_sys_setting_back:
                    finish();
                    break;
            }
        }
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
