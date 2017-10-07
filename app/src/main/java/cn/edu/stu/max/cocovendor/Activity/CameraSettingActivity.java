package cn.edu.stu.max.cocovendor.Activity;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import cn.edu.stu.max.cocovendor.R;

public class CameraSettingActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_setting);

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

        radioGroupIsSpy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == radioButtonYes.getId()) {

                } else if (i == radioButtonNo.getId()) {

                }
            }
        });
    }
}
