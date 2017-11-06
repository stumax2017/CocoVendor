package cn.edu.stu.max.cocovendor.javaClass;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.StatFs;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import android.content.SharedPreferences;

import cn.edu.stu.max.cocovendor.activities.CameraSettingActivity;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by 0 on 2017/10/7.
 */

public class CameraThread extends Thread {

    private static int oldestVideo = 0;
    private static int cameraFileNum = 0;


    private MediaRecorder mMediaRecorder;
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView mSurfaceView;
    private Camera mCamera;
    private long mRecordTime;
    private long mStartTime = Long.MIN_VALUE;
    private long mEndTime = Long.MIN_VALUE;
    private HashMap<String, String> map = new HashMap<String, String>();
    private static final String TAG = "SEDs508EG";
    public static int numOfVideo = 0;
    //public static int minuteOfVideo = 1;
    private Context context;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    public CameraThread(long recordTime, SurfaceView surfaceView,
                        SurfaceHolder surfaceHolder) {
        mRecordTime = recordTime;
        mSurfaceView = surfaceView;
        mSurfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        // 开始录像
        //startRecord();
        // 启动定时器，到规定时间mRecordTime后停止录像
        Timer timer = new Timer();
        timer.schedule(new TimerThread(), 0, mRecordTime);
    }

    // 获取摄像头实例对象
    public Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
            // 打开摄像头错误
            Log.i("info", "打开摄像头错误");
        }
        return c;
    }

    // 开始录像
    public void startRecord() {
        stopRecord();
        mMediaRecorder = new MediaRecorder(); // 创建MediaRecord对象
        mCamera = getCameraInstance(); // 创建Camera对象
        mCamera.unlock(); // 解锁Camera
        mMediaRecorder.setCamera(mCamera); // 设置录制视频源为Camera
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        if (ViewHolder.revolution.equals("qcif")) {
            mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_QCIF)); // 设置分辨率为CIF
        } else if (ViewHolder.revolution.equals("cif")) {
            mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_CIF)); // 设置分辨率为480P
        } else if (ViewHolder.revolution.equals("480p")) {
            mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_480P)); // 设置分辨率为720P
        }

        mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
        //mMediaRecorder.setMaxDuration(1000);
        mMediaRecorder.setOutputFile(getOutputMediaFile(MEDIA_TYPE_VIDEO).toString()); // 设置文件输出路径
        try {
            mMediaRecorder.prepare(); // 准备录制
            mMediaRecorder.start(); // 开始录制
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void releaseMediaRecorder() {
        if (mMediaRecorder != null) {
            // 清楚配置
            mMediaRecorder.reset();
            // 释放对象
            mMediaRecorder.release();
            mMediaRecorder = null;
            //为后续使用 锁定摄像头
            mCamera.lock();
        }
    }

    // 停止录制
    public void stopRecord() {
        if (mMediaRecorder != null) {
            // 停止录制
            mMediaRecorder.stop();
            // 释放资源
            mMediaRecorder.release();
            mMediaRecorder = null;
            if (mCamera != null) {
                mCamera.release();
                mCamera = null;
            }
        }
    }

    // 定时器线程任务
    public class TimerThread extends TimerTask {
        // 停止录像
        @Override
        public void run() {



                try {
//                stopRecord();
//                this.cancel();
//                numOfVideo = numOfVideo + 1;
//                if (numOfVideo > 3) {
//                    stopRecord();
//                    this.cancel();
//                } else {
                    if (ViewHolder.sw) {
                        if (ViewHolder.spyTime.equals("24hours")) {
                            startRecord();
                        } else if (ViewHolder.spyTime.equals("8002200")) {
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                            String date = sdf.format(new java.util.Date());
                            if (date.compareTo("08:00:00") > 0 && date.compareTo("22:00:00") < 0)
                                startRecord();
                            else stopRecord();
                        } else if (ViewHolder.spyTime.equals("6002400")) {
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                            String date = sdf.format(new java.util.Date());
                            if (date.compareTo("06:00:00") > 0 && date.compareTo("24:00:00") < 0)
                                startRecord();
                            else stopRecord();
                        }
                    } else {

                    }

//                }
                } catch (Exception e) {
                    map.clear();
                    map.put("recordingFlag", "false");
                    String ac_time = getVideoRecordTime();// 录像时间
                    map.put("recordTime", ac_time);
                    //sendMsgToHandle(m_msgHandler, iType, map);
                }
        }
    }
    // 计算当前已录像时间，默认值返回0
    public String getVideoRecordTime() {
        String result = "0";
        if (mStartTime != Long.MIN_VALUE && mEndTime != Long.MIN_VALUE) {
            long tempTime = mEndTime - mStartTime;
            result = String.valueOf(tempTime);
        }
        return result;
    }

    private static File getOutputMediaFile(int type) {
        // 判断SDCard是否存在
        if (!Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            Log.d(TAG, "SDCard不存在");
            return null;
        }



        File mediaStorageDir = new File(
                "/mnt/external_sd" + File.separator
                        + "/MyCocoCamera/");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdir()) {
                Log.d(TAG, "failed to create directory");
                return null;
            }
        }



        // 创建媒体文件名
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timestamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
           // cameraFileNum = cameraFileNum + 1;
            // mediaStorageDir.getPath()

//            mediaFile = new File(mediaStorageDir.getPath() + File.separator
//                    + "VID_" + timestamp + ".3gp");
            String a = ".wmv";
            if (ViewHolder.revolution.equals("cif")) {

                a = ".3gp";
                //mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_CIF)); // 设置分辨率为CIF
            } else if (ViewHolder.revolution.equals("480p")) {
                a = ".mp4";
                //mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_480P)); // 设置分辨率为480P
            } else if (ViewHolder.revolution.equals("qcif")) {
                a = ".avi";
                //mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P)); // 设置分辨率为720P
            }

            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timestamp + a);

        } else {
            Log.d(TAG, "文件类型有误");
            return null;
        }


        FileService fileService = new FileService();
        File[] files = FileService.getFiles("/mnt/external_sd/MyCocoCamera/");
        File path = new File("/mnt/external_sd/");
        StatFs stat = new StatFs(path.getPath());   // 创建StatFs对象，用来获取文件系统的状态
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        //String availableSize = Formatter.formatFileSize(availableBlocks * blockSize);   // 获得SD卡可用容量
        //Log.d("my", availableSize);
        //long a = 60 * 1024 * 1024 *1024;
        if ((availableBlocks * blockSize)/1024/1024/1024 < 59) {
            Arrays.sort(files, new Comparator<File>() {
                @Override
                public int compare(File f1, File f2) {
                    return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
                }
            });
            files[0].delete();
        }
//        if ((fileService.getSDAvailableSize()).compareTo("2.00GB") > 0) {
//
//        }


        return mediaFile;
    }
}
