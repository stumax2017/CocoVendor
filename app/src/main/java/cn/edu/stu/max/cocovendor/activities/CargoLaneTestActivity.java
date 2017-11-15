package cn.edu.stu.max.cocovendor.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.javaClass.ToastFactory;

public class CargoLaneTestActivity extends SerialPortActivity {

    private Button goodsTestAllButton;
    private Button goodsTestBackButton;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_lane_test);

        goodsTestAllButton = (Button) findViewById(R.id.goods_test_all_button);
        goodsTestAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences share = getSharedPreferences("serial_mode_file", MODE_PRIVATE);  // 实例化
                SharedPreferences.Editor editor = share.edit();   // 使处于可编辑状态
                editor.putString("serial_mode", "D");
                editor.apply();
            }
        });

        goodsTestBackButton = (Button) findViewById(R.id.goods_test_back_button);
        goodsTestBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // 串口接收函数
    @Override
    protected void onDataReceived(final byte[] buffer, final int size) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (flag) {
                    goodsTestAllButton.setBackgroundColor(5555);
                    ToastFactory.makeText(CargoLaneTestActivity.this, "hh", Toast.LENGTH_SHORT).show();
                    flag = false;
                } else {
                    goodsTestAllButton.setBackgroundColor(0000);
                    ToastFactory.makeText(CargoLaneTestActivity.this, "gg", Toast.LENGTH_SHORT).show();
                    flag = true;
                }

            }
        });
    }
}
