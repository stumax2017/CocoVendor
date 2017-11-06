package cn.edu.stu.max.cocovendor.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.MenuItem;

import java.util.List;

import cn.edu.stu.max.cocovendor.R;

public class SettingMenuActivity extends AppCompatPreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //自动调用onBuildHeaders，将pref_headers.xml文件中的preference
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    //检验碎片是否有效
    @Override
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || SettingMenuActivity.SystemSettingFragment.class.getName().equals(fragmentName)
                || SettingMenuActivity.AdSettingFragment.class.getName().equals(fragmentName)
                || SettingMenuActivity.SalesSettingFragment.class.getName().equals(fragmentName)
                || SettingMenuActivity.HelpFragment.class.getName().equals(fragmentName);
    }

    //实现返回功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intentHomePage = new Intent(SettingMenuActivity.this, HomePageActivity.class);
            startActivity(intentHomePage);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //启动系统设置的碎片
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

    //启动广告设置的碎片
    public static class AdSettingFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_ad_setting);
        }
    }

    //启动销售设置的碎片
    public static class SalesSettingFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_sales_setting);
        }
    }

    //启动帮助页的碎片
    public static class HelpFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_help);
        }
    }
}
