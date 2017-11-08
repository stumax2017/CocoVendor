package cn.edu.stu.max.cocovendor.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.edu.stu.max.cocovendor.R;

public class SalesAnalyzeActivity extends AppCompatActivity {

//    private int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_analyze);

        Button btn1 = (Button) findViewById(R.id.btn_1);
        Button btn2 = (Button) findViewById(R.id.btn_2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalesAnalyzeActivity.this, SingleProductSalesPandectActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SalesAnalyzeActivity.this, SingleProductSalesAnalyzeActivity.class);
                startActivity(intent);
            }
        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        TextView textViewAnalyze = (TextView) findViewById(R.id.tv_analyze);
//        textViewAnalyze.setText(Integer.valueOf(DataSupport.sum(Sales.class, "machine_floor", int.class)));
//        try {
//            result = DataSupport.sum(Sales.class, "machine_floor", int.class);
//            textViewAnalyze.setText(String.valueOf(result));
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        } finally {
//            Log.d("SalesAnalyzeActivity", String.valueOf(result));
//        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//            finish();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
