package cn.edu.stu.max.cocovendor.javaClass;

import android.content.Context;
import android.widget.Toast;

public class ToastFactory {
    private static Toast toast;

    public static Toast makeText(Context context, String text, int duration){
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
        } else {
            toast.setText(text);
            toast.setDuration(duration);
        }
        return toast;
    }
}
