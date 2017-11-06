package cn.edu.stu.max.cocovendor.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import cn.edu.stu.max.cocovendor.R;

public class PayActivity extends AppCompatActivity {

    private int goods_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Intent intent = new Intent();
        goods_id = intent.getIntExtra("goods_id", 0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent();
            if (goods_id != 0) {
                intent.putExtra("pay_result", "OK");
                intent.putExtra("goods_id", goods_id);
                setResult(RESULT_OK, intent);
            }
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
