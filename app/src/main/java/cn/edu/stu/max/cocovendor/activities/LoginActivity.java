package cn.edu.stu.max.cocovendor.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import cn.edu.stu.max.cocovendor.javaClass.ToastFactory;
import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.databaseClass.LocalInfo;

public class LoginActivity extends AppCompatActivity {

    private TextView textViewLoginPassword;
    private StringBuffer passwordTemper = new StringBuffer();//缓存输入的密码字符串
    private ButtonListener buttonListener = new ButtonListener();
    private boolean isPasswordVisible = false;
    private static final String passwordHintsLogin = "请输入密码";
    private static final String passwordOldHints = "请输入旧密码";
    private static final String passwordNewHints = "请输入新密码";
    private boolean isLogin;
    private boolean isOldPswdCurrent = false;
    private ImageButton imageButtonLook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //加入返回箭头
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        isLogin = intent.getBooleanExtra("IsLogin", true);
        textViewLoginPassword = (TextView) findViewById(R.id.tv_login_password);
        if (isLogin) {
            textViewLoginPassword.setText(passwordHintsLogin);
        } else {
            textViewLoginPassword.setText(passwordOldHints);
        }

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
        Button buttonLoginEnter = (Button) findViewById(R.id.btn_login_enter);
        buttonLoginEnter.setOnClickListener(buttonListener);
        Button buttonLoginReturn = (Button) findViewById(R.id.btn_login_return);
        buttonLoginReturn.setOnClickListener(buttonListener);
        ImageButton imageButtonDel = (ImageButton) findViewById(R.id.imgbtn_login_del);
        imageButtonDel.setOnClickListener(buttonListener);
        imageButtonDel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                passwordTemper.delete(0, passwordTemper.length());
                if (TextUtils.isEmpty(passwordTemper)) {
                    if (isLogin) {
                        textViewLoginPassword.setText(passwordHintsLogin);
                    } else {
                        if (!isOldPswdCurrent) {
                            textViewLoginPassword.setText(passwordOldHints);
                        } else {
                            textViewLoginPassword.setText(passwordNewHints);
                        }
                    }
                }
                return false;
            }
        });
        imageButtonLook = (ImageButton) findViewById(R.id.imgbtn_login_look);
        imageButtonLook.setOnClickListener(buttonListener);
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
                case R.id.imgbtn_login_del:
                    if (!TextUtils.isEmpty(passwordTemper)) {
                        passwordTemper.deleteCharAt(passwordTemper.length() - 1);
                    }
                    break;
                case R.id.imgbtn_login_look:
                    isPasswordVisible = invertBoolean(isPasswordVisible);
                    break;
                case R.id.btn_login_enter:
                    if (isLogin) {
                        if (passwordTemper.toString().trim().equals(DataSupport.find(LocalInfo.class, 1).getLogin_password())) {
                            ToastFactory.makeText(LoginActivity.this, "密码正确", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, SettingMenuActivity.class);
                            startActivity(intent);
                        } else {
                            ToastFactory.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if(!isOldPswdCurrent) {
                            if (passwordTemper.toString().trim().equals(DataSupport.find(LocalInfo.class, 1).getLogin_password())) {
                                passwordTemper.delete(0, passwordTemper.length());
                                ToastFactory.makeText(LoginActivity.this, "旧密码正确", Toast.LENGTH_SHORT).show();
                                isOldPswdCurrent = true;
                            } else {
                                ToastFactory.makeText(LoginActivity.this, "旧密码错误", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            //DataSupport.find(LocalInfo.class, 1).setLogin_password(passwordTemper.toString().trim());
                            LocalInfo localInfoToChange = new LocalInfo();
                            localInfoToChange.setLogin_password(passwordTemper.toString().trim());
                            localInfoToChange.update(1);
                            passwordTemper.delete(0, passwordTemper.length());
                            ToastFactory.makeText(LoginActivity.this, String.valueOf(DataSupport.find(LocalInfo.class, 1).getLogin_password()), Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }
                    break;
                case R.id.btn_login_return:
                    Intent intent_return = new Intent();
                    setResult(RESULT_OK, intent_return);
                    finish();
                    break;
            }
            if (passwordTemper.length() > 6) {
                passwordTemper.deleteCharAt(passwordTemper.length() - 1);
                ToastFactory.makeText(LoginActivity.this, "密码长度最多6位", Toast.LENGTH_SHORT).show();
            }
            if (isPasswordVisible) {
                textViewLoginPassword.setText(passwordTemper.toString().trim());
                imageButtonLook.setImageResource(R.drawable.ic_visibility_off_black_50dp);
            } else {
                textViewLoginPassword.setText(fillPassword(passwordTemper.toString().trim()));
                imageButtonLook.setImageResource(R.drawable.ic_visibility_black_50dp);
            }
            if (TextUtils.isEmpty(passwordTemper)) {
                if (isLogin) {
                    textViewLoginPassword.setText(passwordHintsLogin);
                } else {
                    if (!isOldPswdCurrent) {
                        textViewLoginPassword.setText(passwordOldHints);
                    } else {
                        textViewLoginPassword.setText(passwordNewHints);
                    }
                }
            }
        }
    }

    //实现返回功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //取反布尔值方法
    private boolean invertBoolean(boolean a) {
        return !a;
    }

    //填充●个数方法
    private String fillPassword(String Plaintext) {
        return Plaintext.replaceAll("[0-9]", "*");
    }
}
