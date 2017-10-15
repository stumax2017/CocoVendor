package cn.edu.stu.max.cocovendor.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.edu.stu.max.cocovendor.JavaClass.SheetSalesAdapter;
import cn.edu.stu.max.cocovendor.R;

public class SheetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);

        Button buttonSheetSales = (Button) findViewById(R.id.btn_sheet_sales);
        buttonSheetSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SheetActivity.this, SheetSalesActivity.class);
                startActivity(intent);
            }
        });

        Button buttonSheetSettingBack = (Button) findViewById(R.id.btn_sheet_setting_back);
        buttonSheetSettingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
