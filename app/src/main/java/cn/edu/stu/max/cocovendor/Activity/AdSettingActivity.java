package cn.edu.stu.max.cocovendor.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.stu.max.cocovendor.JavaClass.FileService;
import cn.edu.stu.max.cocovendor.JavaClass.MyInternalListAdapter;
import cn.edu.stu.max.cocovendor.JavaClass.MyUSBListAdapter;
import cn.edu.stu.max.cocovendor.R;


public class AdSettingActivity extends Activity {

    private final static String FROMPATH = "/mnt/usb_storage/USB_DISK2/udisk0/advertisement/";   // U盘广告存储路径
    private final static String TOPATH = "/storage/sdcard0/tencent/QQfile_recv/b/";               // 本机广告存储路径

    private VideoView videoViewAd;    // 播放广告视频

    private ImageView imageViewAd;    // 播放图片

    private ListView listViewAdUSB;         // U盘广告列表
    private ListView listViewAdInternal;   // 本机广告列表

    private MyUSBListAdapter myUSBListAdapter;    // U盘广告内容适配器
    private MyInternalListAdapter myInternalListAdapter;    // 本机广告内容适配器

    private boolean hasAddedFlag = false;             // 用来判断有无文件添加
    private boolean hasFetchedFlag = false;           // 用来判断有无文件取回

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_setting);

        Button buttonAdReturn = (Button) findViewById(R.id.btn_ad_return);
        Button buttonAdAdd = (Button) findViewById(R.id.btn_ad_add);
        Button buttonAdDelete = (Button) findViewById(R.id.btn_ad_delete);
        Button buttonAdFetch = (Button) findViewById(R.id.btn_ad_fetch);
        Button buttonAdSetting = (Button) findViewById(R.id.btn_ad_setting);

        imageViewAd = (ImageView) findViewById(R.id.iv_ad);

        videoViewAd = (VideoView) findViewById(R.id.vv_ad);

        listViewAdUSB = (ListView) findViewById(R.id.lv_ad_usb);
        listViewAdInternal = (ListView) findViewById(R.id.lv_ad_internal);

        imageViewAd.setVisibility(View.VISIBLE);
        videoViewAd.setVisibility(View.INVISIBLE);


        myUSBListAdapter = new MyUSBListAdapter(this, getList(FROMPATH));
        listViewAdUSB.setAdapter(myUSBListAdapter);
        listViewAdUSB.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File[] files = FileService.getFiles(FROMPATH);
                playVideo(files[position].getPath());
                videoViewAd.setVisibility(View.VISIBLE);
                imageViewAd.setVisibility(View.INVISIBLE);
            }
        });

        myInternalListAdapter = new MyInternalListAdapter(this, getList(TOPATH));
        listViewAdInternal.setAdapter(myInternalListAdapter);
        listViewAdInternal.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File[] files = FileService.getFiles(TOPATH);
                playVideo(files[position].getPath());
                videoViewAd.setVisibility(View.VISIBLE);
                imageViewAd.setVisibility(View.INVISIBLE);
            }
        });

        videoViewAd.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoViewAd.start();
            }
        });

        buttonAdAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File[] currentFiles = FileService.getFiles(FROMPATH);
                for (int i = 0; i < MyUSBListAdapter.isFileAdded.length; i++) {
                    if (MyUSBListAdapter.isFileAdded[i]) {
                        FileService.copyFile(currentFiles[i].getPath(), TOPATH + currentFiles[i].getName());
                        hasAddedFlag = true;
                    }
                }

                if (hasAddedFlag) {
                    MyInternalListAdapter.setList(getList(TOPATH));
                    MyInternalListAdapter.setIsSelectedAndIsFileAdded(getList(TOPATH));
                    myInternalListAdapter.notifyDataSetChanged();
                    //MyUSBListAdapter.setList(getList(FROMPATH));
                    MyUSBListAdapter.setIsSelectedAndIsFileAdded(getList(FROMPATH));
                    myUSBListAdapter.notifyDataSetChanged();
                    hasAddedFlag = false;
                }
            }
        });

        buttonAdFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File[] currentFiles = FileService.getFiles(TOPATH);
                for (int i = 0; i < MyInternalListAdapter.isFileAdded.length; i++) {
                    if (MyInternalListAdapter.isFileAdded[i]) {
                        FileService.copyFile(currentFiles[i].getPath(), FROMPATH + currentFiles[i].getName());
                        hasFetchedFlag = true;
                    }
                }

                if (hasFetchedFlag) {
                    MyUSBListAdapter.setList(getList(FROMPATH));
                    MyUSBListAdapter.setIsSelectedAndIsFileAdded(getList(FROMPATH));
                    myUSBListAdapter.notifyDataSetChanged();
                    //MyInternalListAdapter.setList(getList(FROMPATH));
                    MyInternalListAdapter.setIsSelectedAndIsFileAdded(getList(TOPATH));
                    myInternalListAdapter.notifyDataSetChanged();
                    hasFetchedFlag = false;
                }
            }
        });

        buttonAdDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FileService.isFileAdded(FROMPATH, MyUSBListAdapter.isFileAdded)
                        || FileService.isFileAdded(TOPATH, MyInternalListAdapter.isFileAdded)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AdSettingActivity.this);
                    builder.setMessage("确认删除吗？");
                    builder.setTitle("提示");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            FileService.deleteFile(FROMPATH, MyUSBListAdapter.isFileAdded);
                            FileService.deleteFile(TOPATH, MyInternalListAdapter.isFileAdded);

                            MyInternalListAdapter.setList(getList(TOPATH));
                            MyInternalListAdapter.setIsSelectedAndIsFileAdded(getList(TOPATH));
                            myInternalListAdapter.notifyDataSetChanged();
                            MyUSBListAdapter.setList(getList(FROMPATH));
                            MyUSBListAdapter.setIsSelectedAndIsFileAdded(getList(FROMPATH));
                            myUSBListAdapter.notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }
            }
        });
    }

    public void playVideo(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            videoViewAd.setVideoPath(file.getPath());
            videoViewAd.start();
        } else {
            imageViewAd.setVisibility(View.VISIBLE);
            videoViewAd.setVisibility(View.INVISIBLE);
        }

    }

    private List<Map<String, Object>> getList(String filePath)
    {
        File[] files = FileService.getFiles(filePath);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for(int i = 0; i < files.length; i++)
        {
            map = new HashMap<String, Object>();
            map.put("img", R.mipmap.ic_launcher);
            map.put("title", files[i].getName());
            list.add(map);
        }
        return list;
    }














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
}
