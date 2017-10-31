package cn.edu.stu.max.cocovendor.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

import cn.edu.stu.max.cocovendor.R;

public class SettingMenuActivity extends AppCompatPreferenceActivity {
//    private ButtonListener buttonListener = new ButtonListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
//        setContentView(R.layout.activity_setting_menu);

//        Button buttonSystemSetting = (Button) findViewById(R.id.btn_system_setting);
//        buttonSystemSetting.setOnClickListener(buttonListener);
//        Button buttonSheetSetting = (Button) findViewById(R.id.btn_sheet_setting);
//        buttonSheetSetting.setOnClickListener(buttonListener);
//        Button buttonGoodsTest = (Button) findViewById(R.id.btn_goods_test);
//        buttonGoodsTest.setOnClickListener(buttonListener);
//        Button buttonAdSetting = (Button) findViewById(R.id.btn_ad_setting);
//        buttonAdSetting.setOnClickListener(buttonListener);
//        Button buttonMoneySetting = (Button) findViewById(R.id.btn_money_setting);
//        buttonMoneySetting.setOnClickListener(buttonListener);
//        Button buttonHelp = (Button) findViewById(R.id.btn_help);
//        buttonHelp.setOnClickListener(buttonListener);
//        Button buttonSalesSetting = (Button) findViewById(R.id.btn_sales_setting);
//        buttonSalesSetting.setOnClickListener(buttonListener);
//        Button buttonSalesAnalyze = (Button) findViewById(R.id.btn_sales_analyze);
//        buttonSalesAnalyze.setOnClickListener(buttonListener);
//        Button buttonReturnSales = (Button) findViewById(R.id.btn_setting_return_sales);
//        buttonReturnSales.setOnClickListener(buttonListener);
//        Button buttonReboot = (Button) findViewById(R.id.btn_reboot);
//        buttonReboot.setOnClickListener(buttonListener);
    }

//    private class ButtonListener implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.btn_system_setting:
//                    Intent intent_sys_setting = new Intent(SettingMenuActivity.this, SystemSettingActivity.class);
//                    startActivity(intent_sys_setting);
//                    break;
//                case R.id.btn_sheet_setting:
//                    Intent intent_sheet_setting = new Intent(SettingMenuActivity.this, SystemSettingActivity.class);
//                    startActivity(intent_sheet_setting);
//                    break;
//                case R.id.btn_goods_test:
//                    break;
//                case R.id.btn_ad_setting:
//                    Intent intent_ad_setting = new Intent(SettingMenuActivity.this, AdSettingActivity.class);
//                    startActivity(intent_ad_setting);
//                    break;
//                case R.id.btn_money_setting:
//                    break;
//                case R.id.btn_help:
//                    break;
//                case R.id.btn_sales_setting:
//                    Intent intent_sales_setting = new Intent(SettingMenuActivity.this, SalesSettingActivity.class);
//                    startActivity(intent_sales_setting);
//                    break;
//                case R.id.btn_sales_analyze:
//                    break;
//                case R.id.btn_setting_return_sales:
//                    Intent intent_homepage = new Intent(SettingMenuActivity.this, HomePageActivity.class);
//                    startActivity(intent_homepage);
//                    break;
//                case R.id.btn_reboot:
//                    break;
//            }
//        }
//    }

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || SettingMenuActivity.SystemSettingFragment.class.getName().equals(fragmentName)
                || SettingMenuActivity.AdSettingFragment.class.getName().equals(fragmentName)
                || SettingMenuActivity.SalesSettingFragment.class.getName().equals(fragmentName)
                || SettingMenuActivity.HelpFragment.class.getName().equals(fragmentName);
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setTitle("设置");
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

    public static class AdSettingFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_ad_setting);
        }
    }

    public static class SalesSettingFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_sales_setting);
        }
    }

    public static class HelpFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_help);
        }
    }
}
