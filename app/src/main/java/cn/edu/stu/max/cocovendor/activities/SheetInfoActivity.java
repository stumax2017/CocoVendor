package cn.edu.stu.max.cocovendor.activities;

import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.os.Bundle;
import android.view.MenuItem;

import org.litepal.crud.DataSupport;

import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.databaseClass.LocalInfo;

public class SheetInfoActivity extends AppCompatPreferenceActivity {
    static Preference prefMachineId, prefIp, prefMacAddress, prefServerIp, prefLanguage, prefLocalAddress, prefVersion, prefTelNumber, prefAdRules;
    private static LocalInfo localInfo = DataSupport.findFirst(LocalInfo.class);
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

            prefMachineId.setSummary(String.valueOf(localInfo.getMachine_id()));
            prefIp.setSummary(localInfo.getIp());
            prefMacAddress.setSummary(localInfo.getMac_address());
            prefServerIp.setSummary(localInfo.getServer_ip());
            prefLanguage.setSummary(localInfo.getLanguage());
            prefLocalAddress.setSummary(localInfo.getLocal_address());
            prefVersion.setSummary("v" + localInfo.getVersion() + ".0");
            prefTelNumber.setSummary(localInfo.getTel_number());
            prefAdRules.setSummary(localInfo.getAd_rules());
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
