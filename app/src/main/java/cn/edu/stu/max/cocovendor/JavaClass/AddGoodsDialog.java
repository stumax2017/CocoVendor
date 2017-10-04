package cn.edu.stu.max.cocovendor.JavaClass;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;

import cn.edu.stu.max.cocovendor.R;

public class AddGoodsDialog extends Dialog{
    public Context context;

    public AddGoodsDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public AddGoodsDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected AddGoodsDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.add_goods_dialog, null);
        setContentView(view);
    }
}
