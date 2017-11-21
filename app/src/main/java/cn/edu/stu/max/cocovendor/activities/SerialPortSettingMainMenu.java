/*
 * Copyright 2009 Cedric Priscal
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package cn.edu.stu.max.cocovendor.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.edu.stu.max.cocovendor.R;

public class SerialPortSettingMainMenu extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_port_main_menu);

        final Button buttonSetup = (Button) findViewById(R.id.ButtonSetup);
        buttonSetup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SerialPortSettingMainMenu.this, SerialPortPreferences.class);
                intent.putExtra("extra_prefs_show_button_bar", true);
                intent.putExtra("extra_prefs_set_back_text","返回");
                intent.putExtra("extra_prefs_set_next_text","");
                startActivity(intent);
            }
        });

        final Button buttonConsole = (Button) findViewById(R.id.ButtonConsole);
        buttonConsole.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //startActivity(new Intent(SerialPortSettingMainMenu.this, ConsoleActivity.class));
            }
        });

        final Button buttonLoopback = (Button) findViewById(R.id.ButtonLoopback);
        buttonLoopback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //startActivity(new Intent(SerialPortSettingMainMenu.this, LoopbackActivity.class));
            }
        });

        final Button button01010101 = (Button) findViewById(R.id.Button01010101);
        button01010101.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //startActivity(new Intent(SerialPortSettingMainMenu.this, Sending01010101Activity.class));
            }
        });

        final Button buttonAbout = (Button) findViewById(R.id.ButtonAbout);
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(SerialPortSettingMainMenu.this);
//                builder.setTitle("About");
//                builder.setMessage(R.string.about_msg);
//                builder.show();
            }
        });

        final Button buttonQuit = (Button) findViewById(R.id.ButtonQuit);
        buttonQuit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SerialPortSettingMainMenu.this, StartActivity.class));
            }
        });
    }
}
