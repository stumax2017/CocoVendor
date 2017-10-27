package cn.edu.stu.max.cocovendor.Activity;


import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cn.edu.stu.max.cocovendor.R;

public class CameraSettingActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {
    private ListPreference revolutionListPreference = null;
    private ListPreference spyTimeListPreference = null;
    private SwitchPreference switchPreference = null;
    private final static String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //从xml文件中添加Preference项
        addPreferencesFromResource(R.xml.camera_setting_preferences);
        //findPreference函数通过key可以找到对应的设置项
        switchPreference = (SwitchPreference)findPreference("SwitchPreference");
        switchPreference.setOnPreferenceChangeListener(this);
        revolutionListPreference = (ListPreference) findPreference("RevolutionListPreference");
        revolutionListPreference.setOnPreferenceChangeListener(this);
        revolutionListPreference.setSummary(revolutionListPreference.getEntry());
        spyTimeListPreference = (ListPreference) findPreference("SpyTimeListPreference");
        spyTimeListPreference.setOnPreferenceChangeListener(this);
        spyTimeListPreference.setSummary(spyTimeListPreference.getEntry());
    }

    /*
     * (non-Javadoc)
     * @param preference
     * @param newValue
     * @return
     * @see android.preference.Preference.OnPreferenceChangeListener#onPreferenceChange(android.preference.Preference, java.lang.Object)
     */
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        // TODO Auto-generated method stub
        //判断是哪个Preference改变了
        if (preference.getKey().equals("RevolutionListPreference")) {
//            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

//            Boolean sw = settings.getBoolean("SwitchPreference", false);

            ListPreference listPreference = (ListPreference) preference;
            CharSequence[] entries = listPreference.getEntries();
            int index = listPreference.findIndexOfValue((String)newValue);
            listPreference.setSummary(entries[index]);
        } else if (preference.getKey().equals("SwitchPreference")){
            //读取设置信息
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
            Boolean sw = settings.getBoolean("SwitchPreference", false);
        } else if (preference.getKey().equals("SpyTimeListPreference")){
            ListPreference listPreference = (ListPreference) preference;
            CharSequence[] entries = listPreference.getEntries();
            int index = listPreference.findIndexOfValue((String)newValue);
            listPreference.setSummary(entries[index]);
        }else{
            //如果返回false表示不允许被改变
            return false;
        }
        //返回true表示允许改变
        return true;
    }
}