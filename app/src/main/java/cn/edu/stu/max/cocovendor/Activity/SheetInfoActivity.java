package cn.edu.stu.max.cocovendor.Activity;

import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.litepal.util.Const;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.PreferencesFactory;

import cn.edu.stu.max.cocovendor.JavaClass.SheetInfoAdapter;
import cn.edu.stu.max.cocovendor.JavaClass.ToastFactory;
import cn.edu.stu.max.cocovendor.R;

public class SheetInfoActivity extends AppCompatPreferenceActivity {
    static Preference prefMachineId, prefIp, prefMacAddress, prefServerIp, prefLanguage, prefLocalAddress, prefVersion, prefTelNumber, prefAdRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SheetInfoFragment()).commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static class SheetInfoFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_sheet_info);

            prefMachineId = findPreference("key_machine_id");
            prefIp = findPreference("key_ip");
            prefMacAddress = findPreference("key_mac_address");
            prefServerIp = findPreference("key_server_ip");
            prefLanguage = findPreference("key_language");
            prefLocalAddress = findPreference("key_local_address");
            prefVersion = findPreference("key_version");
            prefTelNumber = findPreference("key_tel_number");
            prefAdRules = findPreference("key_ad_rules");

            prefMachineId.setSummary("0000001");
            prefIp.setSummary("10.28.34.6");
            prefMacAddress.setSummary("64-00-6A-76-46-60");
            prefServerIp.setSummary("127.0.0.1");
            prefLanguage.setSummary("简体中文");
            prefLocalAddress.setSummary("汕头大学");
            prefVersion.setSummary("v0.2.0");
            prefTelNumber.setSummary("0750-1234567");
            prefAdRules.setSummary("a1b1c1d1e1");
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
