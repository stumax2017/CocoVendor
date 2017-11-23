package cn.edu.stu.max.cocovendor.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.litepal.crud.DataSupport;

import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.databaseClass.Goods;
import cn.edu.stu.max.cocovendor.javaClass.QRCode;
import cn.edu.stu.max.cocovendor.javaClass.ToastFactory;

public class PayActivity extends AppCompatActivity {

    private TextView numberView;
    private TextView moneyView;
    private ImageView logoView;

    private ImageView goodsImageView;
    private TextView goodsTextView;
    private TextView priceTextView;

    private int whichFloor;
    private int whichGoods;
    private String whichWay = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            final ImageView qrcodeView = (ImageView) findViewById(R.id.qrcode_view);
            switch (item.getItemId()) {
                case R.id.navigation_wepay:
                    logoView.setImageResource(R.drawable.wepay_logo);
                    numberView.setText("004565671035");
                    moneyView.setText(String.valueOf(2.55f));
                    whichWay = "微信";
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap wepayBitmap = QRCode.createQRCodeBitmap("http://www.bing.com", 300, 300);
                            qrcodeView.setImageBitmap(wepayBitmap);
                        }
                    });

                    return true;
                case R.id.navigation_alipay:
                    logoView.setImageResource(R.drawable.alipay_logo);
                    numberView.setText("001123771035");
                    moneyView.setText(String.valueOf(3.15f));
                    whichWay = "支付宝";
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap alipayBitmap = QRCode.createQRCodeBitmap("http://www.baidu.com", 300, 300);
                            qrcodeView.setImageBitmap(alipayBitmap);
                        }
                    });
                    return true;
                case R.id.navigation_cash:
                    whichWay = "现金";
                    return true;
                case R.id.navigation_return:
//                    Intent intent = new Intent(PayActivity.this, HomePageActivity.class);
//                    startActivity(intent);
                    Intent intent = new Intent();
                    intent.putExtra("which_floor", whichFloor);
                    intent.putExtra("which_goods", whichGoods);
                    intent.putExtra("which_way", whichWay);
                    setResult(RESULT_OK, intent);
                    finish();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_pay);

        Intent intent = getIntent();
        whichFloor = intent.getIntExtra("which_floor", 0);

        goodsImageView = (ImageView) findViewById(R.id.imageView_goods);
        goodsTextView = (TextView) findViewById(R.id.textView_goods);
        priceTextView = (TextView) findViewById(R.id.textView_price);

        numberView = (TextView) findViewById(R.id.number);
        moneyView = (TextView) findViewById(R.id.moneyView);
        BottomNavigationViewEx navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);
        navigation.enableAnimation(false);
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(false);
        navigation.setItemHeight(100);
        navigation.setIconSize(50, 50);
        navigation.setTextSize(20);
        logoView = (ImageView) findViewById(R.id.logo_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        SharedPreferences preferences = getSharedPreferences("cabinet_floor", MODE_PRIVATE);
        whichGoods =  preferences.getInt("cabinet_floor_" + whichFloor, 0);
        try {
            Goods goods = DataSupport.find(Goods.class, whichGoods);
            goodsImageView.setImageResource(goods.getImage_path());
            goodsTextView.setText(goods.getName());
            priceTextView.setText("¥ " + String.valueOf(goods.getSales_price()));
        } catch (NullPointerException e) {
            ToastFactory.makeText(PayActivity.this, "当前没有商品", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
