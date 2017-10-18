package cn.edu.stu.max.cocovendor.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import cn.edu.stu.max.cocovendor.R;

public class CameraSettingActivity extends AppCompatActivity {

    private static final String cameraSettingDataFileName = "cameraSettingDataFile";     // 定义保存的文件的名称
    private SharedPreferences.Editor editor;

    private Button buttonReturn;

    private RadioGroup radioGroupIsSpy;
    private RadioButton radioButtonYes;
    private RadioButton radioButtonNo;

    private RadioGroup radioGroupResolutionSelect;
    private RadioButton radioButtonCIF;
    private RadioButton radioButton480P;
    private RadioButton radioButton720P;

    private RadioGroup radioGroupSpyMethod;
    private RadioButton radioButton24hours;
    private RadioButton radioButtonATime;
    private RadioButton radioButtonCustom;

    private int resolutionSelection = 0;
    private int spyMethodSelection = 0;
    private boolean isOpen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_setting);




        SharedPreferences share = super.getSharedPreferences(cameraSettingDataFileName, MODE_PRIVATE);  // 实例化
        editor = share.edit();   // 使处于可编辑状态
        editor.putBoolean("isOpen", false);
        editor.putInt("resolutionSelection", resolutionSelection);
        editor.putInt("spyMethodSelection", spyMethodSelection);


        radioGroupIsSpy = (RadioGroup) findViewById(R.id.rg_is_spy);
        radioButtonYes = (RadioButton) findViewById(R.id.rb_yes);
        radioButtonNo = (RadioButton) findViewById(R.id.rb_no);


        radioGroupResolutionSelect = (RadioGroup) findViewById(R.id.rg_resolution_select);
        radioButtonCIF = (RadioButton) findViewById(R.id.rb_cif);
        radioButton480P = (RadioButton) findViewById(R.id.rb_480p);
        radioButton720P = (RadioButton) findViewById(R.id.rb_720p);

        radioGroupSpyMethod = (RadioGroup) findViewById(R.id.rg_spy_method);
        radioButton24hours = (RadioButton) findViewById(R.id.rb_24hours);
        radioButtonATime = (RadioButton) findViewById(R.id.rb_a_time);
        radioButtonCustom = (RadioButton) findViewById(R.id.rb_custom);

        buttonReturn = (Button) findViewById(R.id.btn_camera_setting_return);

//        radioButtonYes.setChecked(share.getBoolean("isOpen", true));
//        radioButtonNo.setChecked(!share.getBoolean("isOpen", true));
//        switch (share.getInt("resolutionSelection", 0)) {
//            case 0:
//                radioButtonCIF.setChecked(true);
//                break;
//            case 1:
//                radioButton480P.setChecked(true);
//                break;
//            case 2:
//                radioButton720P.setChecked(true);
//                break;
//        }
//        switch (share.getInt("spyMethodSelection", 0)) {
//            case 0:
//                radioButton24hours.setChecked(true);
//                break;
//            case 1:
//                radioButtonATime.setChecked(true);
//                break;
//            case 2:
//                radioButtonCustom.setChecked(true);
//                break;
//        }


        radioGroupIsSpy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == radioButtonYes.getId()) {
                    isOpen = true;
                } else if (i == radioButtonNo.getId()) {
                    isOpen = false;
                    buttonReturn.setText("hhhhhh");
                }
            }
        });

        radioGroupResolutionSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == radioButtonCIF.getId()) {
                    resolutionSelection = 0;
                } else if (i == radioButton480P.getId()) {
                    resolutionSelection = 1;
                } else if (i == radioButton720P.getId()) {
                    resolutionSelection = 2;
                }
            }
        });

        radioGroupSpyMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == radioButton24hours.getId()) {
                    spyMethodSelection = 0;
                } else if (i == radioButtonATime.getId()) {
                    spyMethodSelection = 1;
                } else if (i == radioButton24hours.getId()) {
                    spyMethodSelection = 0;
                }
            }
        });

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean("isOpen", isOpen);
                editor.putInt("resolutionSelection", resolutionSelection);
                editor.putInt("spyMethodSelection", spyMethodSelection);
                finish();
            }
        });
    }
}
