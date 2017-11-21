package cn.edu.stu.max.cocovendor.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.edu.stu.max.cocovendor.R;

public class OthersSettingActivity extends AppCompatActivity {

    private static final String cameraSettingDataFileName = "cameraSettingDataFile";     // 定义保存的文件的名称
    private SharedPreferences share;

    private ButtonListener buttonListener = new ButtonListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_others_setting);

        share = super.getSharedPreferences(cameraSettingDataFileName, MODE_PRIVATE);  // 实例化

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
        Button buttonCameraSpy = (Button) findViewById(R.id.btn_camera_spy);
        buttonCameraSpy.setOnClickListener(buttonListener);
        Button buttonSerialPortSetting = (Button) findViewById(R.id.btn_serialport_setting);
        buttonSerialPortSetting.setOnClickListener(buttonListener);
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_sys_parm_setting:
                    break;
                case R.id.btn_network_setting:
                    Intent intentWifi = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    intentWifi.putExtra("extra_prefs_show_button_bar", true);
                    intentWifi.putExtra("extra_prefs_set_back_text","返回");
                    intentWifi.putExtra("extra_prefs_set_next_text","");
                    startActivity(intentWifi);
                    break;
                case R.id.btn_update_goods:
                    Intent intentData =  new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);//WIFI网络
                    intentData.putExtra("extra_prefs_show_button_bar", true);
                    intentData.putExtra("extra_prefs_set_back_text","返回");
                    intentData.putExtra("extra_prefs_set_next_text","");
                    startActivity(intentData);
                    break;
                case R.id.btn_change_pswd:
                    Intent intentChangePswd = new Intent(OthersSettingActivity.this, LoginActivity.class);
                    intentChangePswd.putExtra("IsLogin", false);
                    startActivity(intentChangePswd);
                    break;
                case R.id.btn_download_pic:
                    break;
                case R.id.btn_change_tel:
                    break;
                case R.id.update_basic_data:
                    break;
                case R.id.btn_sales_analyze:
                    break;
                case R.id.btn_download_ad:
                    break;
                case R.id.btn_camera_spy:
                    Intent intent_camera_setting = new Intent(OthersSettingActivity.this, CameraSettingActivity.class);
                    startActivity(intent_camera_setting);
                    break;
                case R.id.btn_serialport_setting:
                    Intent intent_serialport_setting = new Intent(OthersSettingActivity.this, SerialPortSettingMainMenu.class);
                    startActivity(intent_serialport_setting);
                case R.id.btn_sys_setting_back:
                    finish();
                    break;
            }
        }
    }
}
