package cn.edu.stu.max.cocovendor.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

import cn.edu.stu.max.cocovendor.javaClass.FileService;
import cn.edu.stu.max.cocovendor.javaClass.ToastFactory;
import cn.edu.stu.max.cocovendor.R;

public class ScreenSaverActivity extends AppCompatActivity {

    private final static String TOPATH = "/storage/sdcard0/tencent/QQfile_recv/b/";               // 本机广告存储路径
    private VideoView videoViewScreenSaver;
    private ImageView imageViewScreenSaver;
    private static int videoIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_screen_saver);

        videoViewScreenSaver = (VideoView) findViewById(R.id.vv_screen_saver);
        imageViewScreenSaver = (ImageView) findViewById(R.id.iv_screen_saver);

        try {
            final File[] currentFiles = FileService.getFiles(TOPATH);

            playVideo(currentFiles[0].getPath());

            videoViewScreenSaver.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    videoIndex = videoIndex + 1;
                    if (videoIndex >= currentFiles.length) {
                        videoIndex = 0;
                    }
                    playVideo(currentFiles[videoIndex].getPath());
                }
            });
        } catch (NullPointerException e) {
            ToastFactory.makeText(ScreenSaverActivity.this, "找不到文件路径", Toast.LENGTH_SHORT).show();
        }
        videoViewScreenSaver.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(ScreenSaverActivity.this, HomePageActivity.class);
                startActivity(intent);
                return false;
            }
        });

        imageViewScreenSaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScreenSaverActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
    }

    public void playVideo(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            imageViewScreenSaver.setVisibility(View.INVISIBLE);
            videoViewScreenSaver.setVisibility(View.VISIBLE);
            videoViewScreenSaver.setVideoPath(file.getPath());
            videoViewScreenSaver.start();
        } else {
            imageViewScreenSaver.setVisibility(View.VISIBLE);
            videoViewScreenSaver.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        finish();
        return super.onTouchEvent(event);
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























