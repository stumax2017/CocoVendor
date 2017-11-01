package cn.edu.stu.max.cocovendor.Activity;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

import cn.edu.stu.max.cocovendor.R;

public class WlanItemActivity extends AppCompatActivity {

    private Switch switchWlanState;
    private WifiManager wifiManager;
    private List<ScanResult>listResult;
    private int wlanstate_flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wlan_item);
        WlanItemInit();
        if(wlanstate_flag == 1){
            scan();
        }


        switchWlanState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //打开WiFi，network设置wlan状态显示on
                    if(!wifiManager.isWifiEnabled()){
                        wifiManager.setWifiEnabled(true);
                        wlanstate_flag = 1;
                    }
                }else{
                    if(wifiManager.isWifiEnabled()){
                        wifiManager.setWifiEnabled(false);
                        wlanstate_flag = 0;
                    }
                    //关闭WiFi，network设置wlan状态显示off
                }
            }
        });

    }

    public void WlanItemInit(){
        switchWlanState=(Switch)findViewById(R.id.switch_wlan_state);

        if(wifiManager.isWifiEnabled()){
            switchWlanState.setChecked(true);
            wlanstate_flag = 1;
        }else{
            switchWlanState.setChecked(false);
            wlanstate_flag = 0;
        }
    }

    public void scan(){
        wifiManager.startScan();
        listResult = wifiManager.getScanResults();
        if(listResult != null){
            Toast.makeText(getApplicationContext(),"存在无线网络，看扫描结果",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"无网络",Toast.LENGTH_SHORT).show();
        }

    }

}
