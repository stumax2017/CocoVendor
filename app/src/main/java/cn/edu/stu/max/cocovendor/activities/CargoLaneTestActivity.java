package cn.edu.stu.max.cocovendor.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.javaClass.ToastFactory;

public class CargoLaneTestActivity extends AppCompatActivity {

    private Button goodsTestAllButton;
    private Button goodsTestBackButton;
    private boolean flag = true;

    String cargoLaneStatus;

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
                editor.putString("serial_mode", "C");
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

        CargoStatusTestThread cargoStatusTestThread = new CargoStatusTestThread();
        cargoStatusTestThread.start();
    }

    private class CargoStatusTestThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (!isInterrupted()) {
                SharedPreferences preferences = getSharedPreferences("cargo_lane_test_file", MODE_PRIVATE);
                cargoLaneStatus =  preferences.getString("cargo_lane_status", "bad");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        switch (cargoLaneStatus) {
                            case "good":
                                goodsTestAllButton.setBackgroundColor(Color.WHITE);
                                break;
                            case "bad":
                                goodsTestAllButton.setBackgroundColor(Color.BLACK);
                                break;
                        }
                    }
                });
            }
        }
    }
}
