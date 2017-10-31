package cn.edu.stu.max.cocovendor.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.prefs.Preferences;

import cn.edu.stu.max.cocovendor.R;

public class SystemSettingActivity extends AppCompatPreferenceActivity {

//    private static final String cameraSettingDataFileName = "cameraSettingDataFile";     // 定义保存的文件的名称
//    private SharedPreferences share;
//
//    private ButtonListener buttonListener = new ButtonListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getFragmentManager().beginTransaction().replace(android.R.id.content, new SystemSettingFragment()).commit();
        setupActionBar();
//        setContentView(R.layout.activity_system_setting);
//
//        share = super.getSharedPreferences(cameraSettingDataFileName, MODE_PRIVATE);  // 实例化
//
//        Button buttonSysParmSetting = (Button) findViewById(R.id.btn_sys_parm_setting);
//        buttonSysParmSetting.setOnClickListener(buttonListener);
//        Button buttonNetworkSetting = (Button) findViewById(R.id.btn_network_setting);
//        buttonNetworkSetting.setOnClickListener(buttonListener);
//        Button buttonUpdateGoods = (Button) findViewById(R.id.btn_update_goods);
//        buttonUpdateGoods.setOnClickListener(buttonListener);
//        Button buttonChangePswd = (Button) findViewById(R.id.btn_change_pswd);
//        buttonChangePswd.setOnClickListener(buttonListener);
//        Button buttonDownloadPic = (Button) findViewById(R.id.btn_download_pic);
//        buttonDownloadPic.setOnClickListener(buttonListener);
//        Button buttonChangeTel = (Button) findViewById(R.id.btn_change_tel);
//        buttonChangeTel.setOnClickListener(buttonListener);
//        Button buttonBasicData = (Button) findViewById(R.id.update_basic_data);
//        buttonBasicData.setOnClickListener(buttonListener);
//        Button buttonDownloadAd = (Button) findViewById(R.id.btn_download_ad);
//        buttonDownloadAd.setOnClickListener(buttonListener);
//        Button buttonSysSettingBack = (Button) findViewById(R.id.btn_sys_setting_back);
//        buttonSysSettingBack.setOnClickListener(buttonListener);
//        Button buttonCameraSpy = (Button) findViewById(R.id.btn_camera_spy);
//        buttonCameraSpy.setOnClickListener(buttonListener);
    }

//    private class ButtonListener implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.btn_sys_parm_setting:
//                    break;
//                case R.id.btn_network_setting:
//                    break;
//                case R.id.btn_sheet_setting:
//                    break;
//                case R.id.btn_update_goods:
//                    break;
//                case R.id.btn_change_pswd:
//                    Intent intentChangePswd = new Intent(SystemSettingActivity.this, LoginActivity.class);
//                    intentChangePswd.putExtra("IsLogin", false);
//                    startActivity(intentChangePswd);
//                    break;
//                case R.id.btn_download_pic:
//                    break;
//                case R.id.btn_change_tel:
//                    break;
//                case R.id.update_basic_data:
//                    break;
//                case R.id.btn_sales_analyze:
//                    break;
//                case R.id.btn_download_ad:
//                    break;
//                case R.id.btn_camera_spy:
//                    boolean data = share.getBoolean("isOpen", false);
//                    //String data = "true";
//                    Intent intent_camera_setting = new Intent(SystemSettingActivity.this, CameraSettingActivity.class);
//                    intent_camera_setting.putExtra("isOpen", data);
//                    startActivity(intent_camera_setting);
//                    break;
//                case R.id.btn_sys_setting_back:
//                    finish();
//                    break;
//            }
//        }
//    }

    public static class SystemSettingFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_sys_setting);
            Preference preference = findPreference("key_change_pswd");
            Intent intentChangePswd = preference.getIntent();
            intentChangePswd.putExtra("IsLogin", false);
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName);
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setTitle("系统设置");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

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
