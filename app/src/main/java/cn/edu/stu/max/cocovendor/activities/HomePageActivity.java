package cn.edu.stu.max.cocovendor.activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import java.io.File;
import java.util.List;

import cn.edu.stu.max.cocovendor.javaClass.FileService;
import cn.edu.stu.max.cocovendor.javaClass.GridViewAdapter;
import cn.edu.stu.max.cocovendor.javaClass.Model;
import cn.edu.stu.max.cocovendor.javaClass.ToastFactory;
import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.databaseClass.Goods;
import cn.edu.stu.max.cocovendor.databaseClass.Sales;
import cn.edu.stu.max.cocovendor.javaClass.ViewHolder;
import cn.edu.stu.max.cocovendor.javaClass.ViewPagerAdapter;
import cn.edu.stu.max.cocovendor.services.VideoService;

public class HomePageActivity extends SerialPortActivity {

    private final static String TOPATH = "/storage/sdcard0/tencent/QQfile_recv/b/";               // 本机广告存储路径

    private static final int REQUEST_CODE_1 = 1;

    private VideoView videoViewHomePageAd;
    private ImageView imageViewHomePageAd;

    private static int videoFileIndex = 0;
    private static int[] videoListOrder;
    private static int[] videoListFrequency;
    private static int tempFrequency = 0;

    private SharedPreferences share;

    private static final String adSettingDataFileName = "sharedfile";     // 定义保存的文件的名称

    private Context context;
    private Toast toast;
    private int i = 0;
    Handler handler = new Handler();

    private ImageView imageViewGoods1;
    private ImageView imageViewGoods2;
    private ImageView imageViewGoods3;
    private ImageView imageViewGoods4;
    private ImageView imageViewGoods5;
    private ImageView imageViewGoods6;



    private String[] titles = {"美食", "电影", "酒店住宿", "休闲娱乐", "外卖", "自助餐", "KTV", "机票/火车票", "周边游", "美甲美睫",
            "火锅", "生日蛋糕", "甜品饮品", "水上乐园", "汽车服务", "美发", "丽人", "景点", "足疗按摩", "运动健身", "健身", "超市", "买菜",
            "今日新单", "小吃快餐", "面膜", "洗浴/汗蒸", "母婴亲子", "生活服务", "婚纱摄影", "学习培训", "家装", "结婚", "全部分配"};
    private ViewPager mPager;
    private List<View> mPagerList;
    private List<Model> mDatas;
    private LinearLayout mLlDot;
    private LayoutInflater inflater;
    /**
     * 总的页数
     */
    private int pageCount;
    /**
     * 每一页显示的个数
     */
    private int pageSize = 6;
    /**
     * 当前显示的是第几页
     */
    private int curIndex = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home_page);


        mPager = (ViewPager) findViewById(R.id.viewpager);
        mLlDot = (LinearLayout) findViewById(R.id.ll_dot);

        mPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: { // 手指下来的时候,取消之前绑定的Runnable

                        handler.removeCallbacks(runnable);

                        break;
                    }
                    case MotionEvent.ACTION_UP: { // 手指离开屏幕，发送延迟消息 ，5秒后执行

                        handler.postDelayed(runnable, 1000 * 20);

                        break;
                    }
                }
                return false;
            }
        });

        //初始化数据源
        initDatas();
        inflater = LayoutInflater.from(this);
        //总的页数=总数/每页数量，并取整
        pageCount = (int) Math.ceil(mDatas.size() * 1.0 / pageSize);
        mPagerList = new ArrayList<View>();
        try {
            for (int i = 0; i < pageCount; i++) {
                //每个页面都是inflate出一个新实例
                GridView gridView = (GridView) inflater.inflate(R.layout.gridview, mPager, false);
                gridView.setAdapter(new GridViewAdapter(this, mDatas, i, pageSize));
                mPagerList.add(gridView);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int pos = position + curIndex * pageSize;
                        ToastFactory.makeText(HomePageActivity.this, mDatas.get(pos).getName(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HomePageActivity.this, PayActivity.class);
                        startActivity(intent);
                    }
                });
            }
            //设置适配器
            mPager.setAdapter(new ViewPagerAdapter(mPagerList));
            //设置圆点
            setOvalLayout();
        } catch (NullPointerException e) {
            ToastFactory.makeText(HomePageActivity.this, "当前没有商品", Toast.LENGTH_SHORT).show();
        }

        final String packageName = getPackageName();
        SharedPreferences settings = getSharedPreferences(packageName + "_preferences", MODE_PRIVATE);
        boolean sw = settings.getBoolean("SwitchPreference", false);
        ViewHolder.sw = sw;
        // String packageName = "cn.edu.stu.max.cocovendor";
        // SharedPreferences sp = getSharedPreferences(packageName + "_preferences", MODE_PRIVATE);
        if (sw) {
            Toast.makeText(HomePageActivity.this, "hhh" + packageName, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(HomePageActivity.this, "xxxxxxxxxxxx" + packageName, Toast.LENGTH_SHORT).show();
        }


        //尝试定位操作
        AMapLocationClient mLocationClient = new AMapLocationClient(this);
        mLocationClient.setLocationListener(mLocationListener);
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(false);
        mLocationOption.setInterval(3000);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();

        context = this;

//        final Intent intent = new Intent("cn.edu.stu.max.cocovendor.services.VideoService");
        //intent.setAction("cn.edu.stu.max.cocovendor.services.VideoService");
        Button a = (Button) findViewById(R.id.a);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                stopService(new Intent(HomePageActivity.this, VideoService.class));
//}
//               finish();
//                android.os.Process.killProcess(android.os.Process.myPid());
//                System.exit(0);
//                startActivity(new Intent(HomePageActivity.this, StartActivity.class));
//                ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//                manager.killBackgroundProcesses(getPackageName());
//                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
//                ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//                manager.restartPackage(getPackageName());
                ToastFactory.makeText(HomePageActivity.this, "good job", Toast.LENGTH_SHORT).show();
            }
        });

//        imageViewGoods1 = (ImageView) findViewById(R.id.iv_goods_1);
//        imageViewGoods2 = (ImageView) findViewById(R.id.iv_goods_2);
//        imageViewGoods3 = (ImageView) findViewById(R.id.iv_goods_3);
//        imageViewGoods4 = (ImageView) findViewById(R.id.iv_goods_4);
//        imageViewGoods5 = (ImageView) findViewById(R.id.iv_goods_5);
//        imageViewGoods6 = (ImageView) findViewById(R.id.iv_goods_6);

//        imageViewGoods1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN: { // 手指下来的时候,取消之前绑定的Runnable
//
//                        handler.removeCallbacks(runnable);
//
//                        break;
//                    }
//                    case MotionEvent.ACTION_UP: { // 手指离开屏幕，发送延迟消息 ，5秒后执行
//
//                        handler.postDelayed(runnable, 1000 * 20);
//
//                        break;
//                    }
//                }
//                return false;
//            }
//        });
//
//        imageViewGoods2.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN: { // 手指下来的时候,取消之前绑定的Runnable
//
//                        handler.removeCallbacks(runnable);
//
//                        break;
//                    }
//                    case MotionEvent.ACTION_UP: { // 手指离开屏幕，发送延迟消息 ，5秒后执行
//
//                        handler.postDelayed(runnable, 1000 * 20);
//
//                        break;
//                    }
//                }
//                return false;
//            }
//        });
//
//        imageViewGoods3.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN: { // 手指下来的时候,取消之前绑定的Runnable
//
//                        handler.removeCallbacks(runnable);
//
//                        break;
//                    }
//                    case MotionEvent.ACTION_UP: { // 手指离开屏幕，发送延迟消息 ，5秒后执行
//
//                        handler.postDelayed(runnable, 1000 * 20);
//
//                        break;
//                    }
//                }
//                return false;
//            }
//        });
//
//        imageViewGoods4.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN: { // 手指下来的时候,取消之前绑定的Runnable
//
//                        handler.removeCallbacks(runnable);
//
//                        break;
//                    }
//                    case MotionEvent.ACTION_UP: { // 手指离开屏幕，发送延迟消息 ，5秒后执行
//
//                        handler.postDelayed(runnable, 1000 * 20);
//
//                        break;
//                    }
//                }
//                return false;
//            }
//        });
//
//        imageViewGoods5.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN: { // 手指下来的时候,取消之前绑定的Runnable
//
//                        handler.removeCallbacks(runnable);
//
//                        break;
//                    }
//                    case MotionEvent.ACTION_UP: { // 手指离开屏幕，发送延迟消息 ，5秒后执行
//
//                        handler.postDelayed(runnable, 1000 * 20);
//
//                        break;
//                    }
//                }
//                return false;
//            }
//        });
//
//        imageViewGoods6.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN: { // 手指下来的时候,取消之前绑定的Runnable
//
//                        handler.removeCallbacks(runnable);
//
//                        break;
//                    }
//                    case MotionEvent.ACTION_UP: { // 手指离开屏幕，发送延迟消息 ，5秒后执行
//
//                        handler.postDelayed(runnable, 1000 * 20);
//
//                        break;
//                    }
//                }
//                return false;
//            }
//        });

        videoViewHomePageAd = (VideoView) findViewById(R.id.vv_hp_ad);
        imageViewHomePageAd = (ImageView) findViewById(R.id.iv_hp_ad);
        imageViewHomePageAd.setVisibility(View.INVISIBLE);

        share = super.getSharedPreferences(adSettingDataFileName, MODE_PRIVATE);
        if (share.getBoolean("isAdSettingChanged", false)) {
            Toast.makeText(HomePageActivity.this, "good" + share.getString("Frequency_" + 0, "null"), Toast.LENGTH_LONG).show();
        }

        initVideoPath();


        videoViewHomePageAd.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                if (share.getBoolean("isAdSettingChanged", false)) {
                    if (videoListFrequency[videoFileIndex] > 1) {
                        videoListFrequency[videoFileIndex] = videoListFrequency[videoFileIndex] - 1;

                    } else {
                        videoListFrequency[videoFileIndex] = Integer.parseInt(share.getString("Frequency_" + videoFileIndex, "0"));
                        videoFileIndex ++;
                    }
                    File[] currentFiles = FileService.getFiles(TOPATH);

                    if (currentFiles.length != 0) {
                        if (videoFileIndex >= currentFiles.length) {
                            videoFileIndex = 0;
                        }
                        playVideo(currentFiles[videoListOrder[videoFileIndex]].getPath());
                    } else {
                        imageViewHomePageAd.setVisibility(View.VISIBLE);
                        videoViewHomePageAd.setVisibility(View.INVISIBLE);
                    }
                } else {
                    videoFileIndex ++;
                    File[] currentFiles = FileService.getFiles(TOPATH);

                    if (currentFiles.length != 0) {
                        if (videoFileIndex >= currentFiles.length) {
                            videoFileIndex = 0;
                        }
                        playVideo(currentFiles[videoFileIndex].getPath());
                    } else {
                        imageViewHomePageAd.setVisibility(View.VISIBLE);
                        videoViewHomePageAd.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });


//        name.setText(share.getString("name", "no"));
//        sex.setText(share.getString("sex", "ren"));
//        age.setText("hh" + share.getInt("age", 0));

//        Toast.makeText(HomePageActivity.this, "good" + share.getInt("age", 123), Toast.LENGTH_LONG).show();


        TextView textViewHomepageTestlogin = (TextView) findViewById(R.id.tv_homepage_testlogin);
        textViewHomepageTestlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
                intent.putExtra("IsLogin", true);
                startActivity(intent);
            }
        });

//        ImageView imageViewGoods1 = (ImageView) findViewById(R.id.iv_goods_1);
//        imageViewGoods1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String text = "You clicked on item 1";
//                try {
//                    mOutputStream.write(text.getBytes());
//                    mOutputStream.write('\n');
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                try {
//                    mOutputStream.write(text.getBytes());
//                    mOutputStream.write('\n');
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                try {
////                    Sales sales = new Sales();
////                    sales.setSales_date(new Date());
////                    sales.setGoods_id(DataSupport.find(Goods.class,1).getId());
////                    sales.setGoods_name(DataSupport.find(Goods.class,1).getName());
////                    sales.setMachine_floor(1);
////                    sales.setPay_way("支付宝");
////                    sales.save();
//                } catch (NullPointerException e) {
//                    ToastFactory.makeText(HomePageActivity.this, "目前没有商品1", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        ImageView imageViewGoods2 = (ImageView) findViewById(R.id.iv_goods_2);
//        imageViewGoods2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String text = "You clicked on item 2";
//                try {
//                    mOutputStream.write(text.getBytes());
//                    mOutputStream.write('\n');
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//                try {
//                    Sales sales = new Sales();
//                    sales.setSales_date(new Date());
//                    sales.setGoods_id(DataSupport.find(Goods.class,2).getId());
//                    sales.setGoods_name(DataSupport.find(Goods.class,2).getName());
//                    sales.setMachine_floor(2);
//                    sales.setPay_way("支付宝");
//                    sales.save();
//                } catch (NullPointerException e) {
//                    ToastFactory.makeText(HomePageActivity.this, "目前没有商品2", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        ImageView imageViewGoods3 = (ImageView) findViewById(R.id.iv_goods_3);
//        imageViewGoods3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String text = "You clicked on item 3";
//                try {
//                    mOutputStream.write(text.getBytes());
//                    mOutputStream.write('\n');
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//                try {
//                    Sales sales = new Sales();
//                    sales.setSales_date(new Date());
//                    sales.setGoods_id(DataSupport.find(Goods.class, 3).getId());
//                    sales.setGoods_name(DataSupport.find(Goods.class, 3).getName());
//                    sales.setMachine_floor(3);
//                    sales.setPay_way("微信");
//                    sales.save();
//                } catch (NullPointerException e) {
//                    ToastFactory.makeText(HomePageActivity.this, "目前没有商品3", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        ImageView imageViewGoods4 = (ImageView) findViewById(R.id.iv_goods_4);
//        imageViewGoods4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String text = "You clicked on item 4";
//                try {
//                    mOutputStream.write(text.getBytes());
//                    mOutputStream.write('\n');
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//                try {
//                    Sales sales = new Sales();
//                    sales.setSales_date(new Date());
//                    sales.setGoods_id(DataSupport.find(Goods.class,4).getId());
//                    sales.setGoods_name(DataSupport.find(Goods.class,4).getName());
//                    sales.setMachine_floor(4);
//                    sales.setPay_way("微信");
//                    sales.save();
//                } catch (NullPointerException e) {
//                    ToastFactory.makeText(HomePageActivity.this, "目前没有商品4", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        ImageView imageViewGoods5 = (ImageView) findViewById(R.id.iv_goods_5);
//        imageViewGoods5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String text = "You clicked on item 5";
//                try {
//                    mOutputStream.write(text.getBytes());
//                    mOutputStream.write('\n');
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//                try {
//                    Sales sales = new Sales();
//                    sales.setSales_date(new Date());
//                    sales.setGoods_id(DataSupport.find(Goods.class,5).getId());
//                    sales.setGoods_name(DataSupport.find(Goods.class,5).getName());
//                    sales.setMachine_floor(5);
//                    sales.setPay_way("现金");
//                    sales.save();
//                } catch (NullPointerException e) {
//                    ToastFactory.makeText(HomePageActivity.this, "目前没有商品5", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        ImageView imageViewGoods6 = (ImageView) findViewById(R.id.iv_goods_6);
//        imageViewGoods6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String text = "You clicked on item 6";
//                try {
//                    mOutputStream.write(text.getBytes());
//                    mOutputStream.write('\n');
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//                try {
//                    Sales sales = new Sales();
//                    sales.setSales_date(new Date());
//                    sales.setGoods_id(DataSupport.find(Goods.class,6).getId());
//                    sales.setGoods_name(DataSupport.find(Goods.class,6).getName());
//                    sales.setMachine_floor(6);
//                    sales.setPay_way("现金");
//                    sales.save();
//                } catch (NullPointerException e) {
//                    ToastFactory.makeText(HomePageActivity.this, "目前没有商品6", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    private AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    TextView textView = (TextView) findViewById(R.id.tv_homepage_testlogin);
                    textView.setText(aMapLocation.getAddress());
                } else {
                }
            }
        }
    };
    //活动转换之间都调用沉浸模式全屏
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    //重写返回按键内容
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }


    private void initVideoPath() {
        videoFileIndex = 0;
        try {
            File[] currentFiles = FileService.getFiles(TOPATH);
            if (currentFiles.length != 0) {
                if (share.getBoolean("isAdSettingChanged", false)) {
                    videoListOrder = new int[currentFiles.length];
                    videoListFrequency = new int[currentFiles.length];
                    for (int i = 0; i < currentFiles.length; i++) {
                        videoListFrequency[i] = Integer.parseInt(share.getString("Frequency_" + i, "0"));
                        for (int j = 0; j < currentFiles.length; j++) {
                            if (share.getString("Ad_" + i, "null").equals(currentFiles[j].getName())) {
                                videoListOrder[i] = j;
                                break;
                            }
                        }
                    }
                    playVideo(currentFiles[videoListOrder[videoFileIndex]].getPath());
                }
                else {
                    playVideo(currentFiles[videoFileIndex].getPath());
                }
            } else {
                imageViewHomePageAd.setVisibility(View.VISIBLE);
                videoViewHomePageAd.setVisibility(View.INVISIBLE);
            }
        } catch (NullPointerException e) {
            ToastFactory.makeText(HomePageActivity.this, "找不到文件路径", Toast.LENGTH_SHORT).show();
        }
    }

    public void playVideo(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            videoViewHomePageAd.setVideoPath(file.getPath());
            videoViewHomePageAd.start();
        } else {
            imageViewHomePageAd.setVisibility(View.VISIBLE);
            videoViewHomePageAd.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoViewHomePageAd != null) {
            videoViewHomePageAd.suspend();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_1:
                if (resultCode == RESULT_OK) {
                    videoFileIndex = 0;
                    File[] currentFiles = FileService.getFiles(TOPATH);
                    if (currentFiles.length != 0) {
                        //Toast.makeText(HomePageActivity.this, "good", Toast.LENGTH_LONG).show();
                        imageViewHomePageAd.setVisibility(View.INVISIBLE);
                        videoViewHomePageAd.setVisibility(View.VISIBLE);
                        //videoViewHomePageAd.start();
                        if (share.getBoolean("isAdSettingChanged", false)) {
                            videoListFrequency = new int[currentFiles.length];
                            videoListOrder = new int[currentFiles.length];
                            for (int i = 0; i < currentFiles.length; i++) {
                                videoListFrequency[i] = Integer.parseInt(share.getString("Frequency_" + i, "0"));
                                for (int j = 0; j < currentFiles.length; j++) {
                                    if (share.getString("Ad_" + i, "null").equals(currentFiles[j].getName())) {
                                        videoListOrder[i] = j;
                                        break;
                                    }
                                }
                            }
                            playVideo(currentFiles[videoListOrder[videoFileIndex]].getPath());
                        } else {
                            playVideo(currentFiles[videoFileIndex].getPath());
                        }
                    } else {
                        imageViewHomePageAd.setVisibility(View.VISIBLE);
                        videoViewHomePageAd.setVisibility(View.INVISIBLE);
                    }
                }
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        handler.postDelayed(runnable, 1000 * 20);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        handler.removeCallbacks(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
        // 用户5秒没操作了
        Intent i = new Intent();
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setClass(context, ScreenSaverActivity.class);
        context.startActivity(i);
        }
    };

    public boolean onTouchEvent(android.view.MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: { // 手指下来的时候,取消之前绑定的Runnable

                handler.removeCallbacks(runnable);
                break;
            }
            case MotionEvent.ACTION_UP: { // 手指离开屏幕，发送延迟消息 ，5秒后执行

                handler.postDelayed(runnable, 1000 * 20);

                break;
            }
        }
        return super.onTouchEvent(event);
    };

    @Override
    protected void onDataReceived(final byte[] buffer, final int size) {
        runOnUiThread(new Runnable() {
            public void run() {
                ToastFactory.makeText(HomePageActivity.this, new String (buffer, 0, size), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化数据源
     */
    private void initDatas() {
        mDatas = new ArrayList<Model>();
        for (int i = 0; i < DataSupport.count(Goods.class); i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
            int imageId = getResources().getIdentifier("ic_category_" + i, "drawable", getPackageName());
            mDatas.add(new Model(titles[i], imageId));
        }
    }

    /**
     * 设置圆点
     */
    public void setOvalLayout() {
        for (int i = 0; i < pageCount; i++) {
            mLlDot.addView(inflater.inflate(R.layout.dot, null));
        }
        // 默认显示第一页
        mLlDot.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                // 取消圆点选中
                mLlDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                mLlDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }
}
