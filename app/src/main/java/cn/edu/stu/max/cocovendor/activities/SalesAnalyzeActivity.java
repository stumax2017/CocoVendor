package cn.edu.stu.max.cocovendor.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.databaseClass.Sales;

public class SalesAnalyzeActivity extends AppCompatActivity {

    private int result = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_analyze);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textViewAnalyze = (TextView) findViewById(R.id.tv_analyze);
        //textViewAnalyze.setText(Integer.valueOf(DataSupport.sum(Sales.class, "machine_floor", int.class)));
        try {
            result = DataSupport.sum(Sales.class, "machine_floor", int.class);
            textViewAnalyze.setText(String.valueOf(result));
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            Log.d("SalesAnalyzeActivity", String.valueOf(result));
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
