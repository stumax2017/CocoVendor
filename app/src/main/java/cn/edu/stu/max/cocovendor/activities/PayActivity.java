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
import android.view.View;
import android.widget.Button;
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

    private ImageView ivGoodsPicture;
    private TextView tvGoodsName;
    private TextView tvGoodsPrice;

    private Button buttonGoodsPunch;
    private Button buttonGoodsBagAndStraw;

    private ImageView ivQRCodes;

    private Button buttonPayReturn;
    private Button buttonAlipay;
    private Button buttonWechat;
    private Button buttonCash;
//    private TextView numberView;
//    private TextView moneyView;
//    private ImageView logoView;



    private int whichFloor;
    private int whichGoods;
    private String whichWay = null;
//
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            final ImageView qrcodeView = (ImageView) findViewById(R.id.qrcode_view);
//            switch (item.getItemId()) {
//                case R.id.navigation_wepay:
//                    logoView.setImageResource(R.drawable.wepay_logo);
//                    numberView.setText("004565671035");
//                    moneyView.setText(String.valueOf(2.55f));
//                    whichWay = "微信";
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Bitmap wepayBitmap = QRCode.createQRCodeBitmap("http://www.bing.com", 300, 300);
//                            qrcodeView.setImageBitmap(wepayBitmap);
//                        }
//                    });
//
//                    return true;
//                case R.id.navigation_alipay:
//                    logoView.setImageResource(R.drawable.alipay_logo);
//                    numberView.setText("001123771035");
//                    moneyView.setText(String.valueOf(3.15f));
//                    whichWay = "支付宝";
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Bitmap alipayBitmap = QRCode.createQRCodeBitmap("http://www.baidu.com", 300, 300);
//                            qrcodeView.setImageBitmap(alipayBitmap);
//                        }
//                    });
//                    return true;
//                case R.id.navigation_cash:
//                    whichWay = "现金";
//                    return true;
//                case R.id.navigation_return:
////                    Intent intent = new Intent(PayActivity.this, HomePageActivity.class);
////                    startActivity(intent);
//                    Intent intent = new Intent();
//                    intent.putExtra("which_floor", whichFloor);
//                    intent.putExtra("which_goods", whichGoods);
//                    intent.putExtra("which_way", whichWay);
//                    setResult(RESULT_OK, intent);
//                    finish();
//                    return true;
//            }
//            return false;
//        }
//
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_pay);

        Intent intent = getIntent();

        whichFloor = intent.getIntExtra("which_floor", 0);

        ivGoodsPicture = (ImageView) findViewById(R.id.iv_goods_picture);
        tvGoodsName = (TextView) findViewById(R.id.tv_goods_name);
        tvGoodsPrice = (TextView) findViewById(R.id.tv_goods_price);

        buttonGoodsPunch = (Button) findViewById(R.id.btn_goods_punch);
        buttonGoodsBagAndStraw = (Button) findViewById(R.id.btn_goods_bag_and_straw);

        ivQRCodes = (ImageView) findViewById(R.id.iv_qrcodes);

        buttonGoodsPunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastFactory.makeText(PayActivity.this, "buttonGoodsPunch", Toast.LENGTH_SHORT).show();
            }
        });

        buttonGoodsBagAndStraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastFactory.makeText(PayActivity.this, "buttonGoodsBagAndStraw", Toast.LENGTH_SHORT).show();
            }
        });

        buttonPayReturn = (Button) findViewById(R.id.btn_pay_return);
        buttonAlipay = (Button) findViewById(R.id.btn_alipay);
        buttonWechat = (Button) findViewById(R.id.btn_wechat);
        buttonCash = (Button) findViewById(R.id.btn_cash);

        buttonPayReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("which_floor", whichFloor);
                intent.putExtra("which_goods", whichGoods);
                intent.putExtra("which_way", whichWay);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        buttonAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whichWay = "支付宝";
                Bitmap alipayBitmap = QRCode.createQRCodeBitmap("http://www.baidu.com", 300, 300);
                ivQRCodes.setImageBitmap(alipayBitmap);
                ToastFactory.makeText(PayActivity.this, "zhifubao", Toast.LENGTH_SHORT).show();
            }
        });

        buttonWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whichWay = "微信";
                Bitmap wepayBitmap = QRCode.createQRCodeBitmap("http://www.bing.com", 300, 300);
                ivQRCodes.setImageBitmap(wepayBitmap);
                ToastFactory.makeText(PayActivity.this, "wechat", Toast.LENGTH_SHORT).show();
            }
        });

        buttonCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whichWay = "现金";
                ToastFactory.makeText(PayActivity.this, "cash", Toast.LENGTH_SHORT).show();
            }
        });

        SharedPreferences preferences = getSharedPreferences("cabinet_floor", MODE_PRIVATE);
        whichGoods =  preferences.getInt("cabinet_floor_" + whichFloor, 0);
        try {
            Goods goods = DataSupport.find(Goods.class, whichGoods);
            ivGoodsPicture.setImageResource(goods.getImage_path());
            tvGoodsName.setText(goods.getName());
            tvGoodsPrice.setText("¥ " + String.valueOf(goods.getSales_price()));
        } catch (NullPointerException e) {
            ToastFactory.makeText(PayActivity.this, "当前没有商品", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
