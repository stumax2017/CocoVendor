package cn.edu.stu.max.cocovendor.Service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.edu.stu.max.cocovendor.JavaClass.CameraThread;

/**
 * Created by 0 on 2017/10/7.
 */

public class VideoService extends Service implements SurfaceHolder.Callback {

    private static final int VALUE_OF_MINUTES = 30;
    private SurfaceView surfaceView; // 视频预览控件
    private LinearLayout lay; // 愿揽控件
    private SurfaceHolder surfaceHolder;
    private Context context;
    private boolean isRecorder = false;
    WindowManager wm;
    LinearLayout relLay;
    CameraThread thread;

    @Override
    public void onCreate() {
        super.onCreate();
        // 设置悬浮窗体对象
        // 创建WindowManager对象
        wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        //  创建WindowManager.Layoutparams对象
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        // 设置窗口类型
        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        // 设置图片格式，效果为背景透明
        wmParams.format = 1;
        // 下面的flags属性的效果形同“锁定”。 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        // 设置悬浮窗口长宽数据
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        // 调整悬浮窗口至中间
        wmParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER;
        // 以屏幕左上角为原点，设置x、y初始值
        wmParams.x = 0;
        wmParams.y = 0;
        // 将需要加到悬浮窗口中的View加入到窗口中了：
        // 如果view没有被加入到某个父组件中，则加入WindowManager中
        surfaceView = new SurfaceView(this);
        surfaceHolder = surfaceView.getHolder();
        WindowManager.LayoutParams params_sur = new WindowManager.LayoutParams();
        params_sur.width = 1;
        params_sur.height = 1;
        params_sur.alpha = 255;
        surfaceView.setLayoutParams(params_sur);

        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceView.getHolder().addCallback((SurfaceHolder.Callback) this);

        relLay = new LinearLayout(this);
        WindowManager.LayoutParams params_rel = new WindowManager.LayoutParams();
        params_rel.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params_rel.height = WindowManager.LayoutParams.WRAP_CONTENT;
        relLay.setLayoutParams(params_rel);
        relLay.addView(surfaceView);
        wm.addView(relLay, wmParams); // 创建View
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int arg1, int arg2,
                               int arg3) {
        surfaceHolder = holder;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 将holder，这个holder为开始在oncreat里面取得的holder，将它赋给surfaceHolder
        surfaceHolder = holder;
        Log.i("process", Thread.currentThread().getName());
        //录像线程，当然也可以在别的地方启动，但是一定要在onCreate方法执行完成以及surfaceHolder被赋值以后启动
        thread = new CameraThread(1000 + 60 * 1000 * VALUE_OF_MINUTES, surfaceView, surfaceHolder);
        thread.start();
        //Toast.makeText(this, "开始录像", Toast.LENGTH_LONG).show();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        surfaceView = null;
        surfaceHolder = null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
