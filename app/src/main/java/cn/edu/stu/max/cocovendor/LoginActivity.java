package cn.edu.stu.max.cocovendor;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private TextView textViewLoginPassword;
    private StringBuffer passwordTemper = new StringBuffer();//缓存输入的密码字符串
    private ButtonListener buttonListener = new ButtonListener();
    private boolean isPasswordVisible = false;
    private static final String passwordHints = "请输入密码";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewLoginPassword = (TextView) findViewById(R.id.tv_login_password);

        Button buttonLogin1 = (Button) findViewById(R.id.btn_login_1);
        buttonLogin1.setOnClickListener(buttonListener);
        Button buttonLogin2 = (Button) findViewById(R.id.btn_login_2);
        buttonLogin2.setOnClickListener(buttonListener);
        Button buttonLogin3 = (Button) findViewById(R.id.btn_login_3);
        buttonLogin3.setOnClickListener(buttonListener);
        Button buttonLogin4 = (Button) findViewById(R.id.btn_login_4);
        buttonLogin4.setOnClickListener(buttonListener);
        Button buttonLogin5 = (Button) findViewById(R.id.btn_login_5);
        buttonLogin5.setOnClickListener(buttonListener);
        Button buttonLogin6 = (Button) findViewById(R.id.btn_login_6);
        buttonLogin6.setOnClickListener(buttonListener);
        Button buttonLogin7 = (Button) findViewById(R.id.btn_login_7);
        buttonLogin7.setOnClickListener(buttonListener);
        Button buttonLogin8 = (Button) findViewById(R.id.btn_login_8);
        buttonLogin8.setOnClickListener(buttonListener);
        Button buttonLogin9 = (Button) findViewById(R.id.btn_login_9);
        buttonLogin9.setOnClickListener(buttonListener);
        Button buttonLogin0 = (Button) findViewById(R.id.btn_login_0);
        buttonLogin0.setOnClickListener(buttonListener);
        Button buttonLoginStar = (Button) findViewById(R.id.btn_login_star);
        buttonLoginStar.setOnClickListener(buttonListener);
        Button buttonLoginWell = (Button) findViewById(R.id.btn_login_well);
        buttonLoginWell.setOnClickListener(buttonListener);
        Button buttonLoginDel = (Button) findViewById(R.id.btn_login_del);
        buttonLoginDel.setOnClickListener(buttonListener);
        Button buttonLoginClear = (Button) findViewById(R.id.btn_login_clear);
        buttonLoginClear.setOnClickListener(buttonListener);
        Button buttonLoginLook = (Button) findViewById(R.id.btn_login_look);
        buttonLoginLook.setOnClickListener(buttonListener);
        Button buttonLoginEnter = (Button) findViewById(R.id.btn_login_enter);
        buttonLoginEnter.setOnClickListener(buttonListener);
        Button buttonLoginReturn = (Button) findViewById(R.id.btn_login_return);
        buttonLoginReturn.setOnClickListener(buttonListener);
    }

    //自定义ButtonListener类，一个监听多个按钮
    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login_1:
                    passwordTemper.append("1");
                    break;
                case R.id.btn_login_2:
                    passwordTemper.append("2");
                    break;
                case R.id.btn_login_3:
                    passwordTemper.append("3");
                    break;
                case R.id.btn_login_4:
                    passwordTemper.append("4");
                    break;
                case R.id.btn_login_5:
                    passwordTemper.append("5");
                    break;
                case R.id.btn_login_6:
                    passwordTemper.append("6");
                    break;
                case R.id.btn_login_7:
                    passwordTemper.append("7");
                    break;
                case R.id.btn_login_8:
                    passwordTemper.append("8");
                    break;
                case R.id.btn_login_9:
                    passwordTemper.append("9");
                    break;
                case R.id.btn_login_0:
                    passwordTemper.append("0");
                    break;
                case R.id.btn_login_star:
                    passwordTemper.append("*");
                    break;
                case R.id.btn_login_well:
                    passwordTemper.append("#");
                    break;
                case R.id.btn_login_del:
                    if (!TextUtils.isEmpty(passwordTemper)) {
                        passwordTemper.deleteCharAt(passwordTemper.length() - 1);
                    }
                    break;
                case R.id.btn_login_clear:
                    passwordTemper.delete(0, passwordTemper.length());
                    break;
                case R.id.btn_login_look:
                    isPasswordVisible = invertBoolean(isPasswordVisible);
                    break;
                case R.id.btn_login_enter:
                    if (passwordTemper.toString().trim().equals("123")) {
                        Toast.makeText(LoginActivity.this, "密码正确", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btn_login_return:
                    finish();
                    break;
            }
            if (isPasswordVisible) {
                textViewLoginPassword.setText(passwordTemper.toString().trim());
            } else {
                textViewLoginPassword.setText(fillPassword(passwordTemper.toString().trim()));
            }
            if (TextUtils.isEmpty(passwordTemper)) {
                textViewLoginPassword.setText(passwordHints);
            }
        }
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

    //取反布尔值方法
    private boolean invertBoolean(boolean a) {
        return !a;
    }

    //填充●个数方法
    private String fillPassword(String Plaintext) {
        //想用正则表达式简化程序，没有实现出来
        //return Plaintext.replaceAll("[0-9]*", "* ");
        String Ciphertext = "";
        for (int i = 0; i < Plaintext.length(); i ++) {
            Ciphertext += "*";
        }
        return Ciphertext;
    }
}
