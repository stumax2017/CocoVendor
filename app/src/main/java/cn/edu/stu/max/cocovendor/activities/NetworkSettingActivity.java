package cn.edu.stu.max.cocovendor.activities;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import cn.edu.stu.max.cocovendor.R;

public class NetworkSettingActivity extends AppCompatActivity {

    private ButtonListener buttonListener = new ButtonListener();
    private WifiManager networkwifimanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_network_setting);
        getInternetState();

        Button buttonNetworkBack=(Button)findViewById(R.id.btn_network_back);
        buttonNetworkBack.setOnClickListener(buttonListener);
        Button buttonWlanSetting=(Button)findViewById(R.id.btn_wlan_setting);
        buttonWlanSetting.setOnClickListener(buttonListener);
        Button buttonDataSetting=(Button)findViewById(R.id.btn_data_setting);
        buttonDataSetting.setOnClickListener(buttonListener);
        Button buttonEthernetSetting=(Button)findViewById(R.id.btn_ethernet_setting);
        buttonEthernetSetting.setOnClickListener(buttonListener);

    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btn_network_back:
                    finish();
                    break;
                case R.id.btn_wlan_setting:
                    Intent intent_wlan_item=new Intent(NetworkSettingActivity.this,WlanItemActivity.class);
                    startActivity(intent_wlan_item);
                    break;
                case R.id.btn_data_setting:
                    break;
                case R.id.btn_ethernet_setting:
                    break;
            }
        }
    }

    public void networksettinginit(){

    }


    public void getInternetState(){
        TextView textviewWlanState=(TextView)findViewById(R.id.tv_wlan_state);
        TextView textviewDataState=(TextView)findViewById(R.id.tv_data_state);
        TextView textviewEthernetState=(TextView)findViewById(R.id.tv_ethernet_state);

        if(networkwifimanager.getWifiState() == 1){
            textviewWlanState.setText("Off");
        }else if (networkwifimanager.getWifiState() == 3){
            textviewWlanState.setText("On");
        }
    }

}






