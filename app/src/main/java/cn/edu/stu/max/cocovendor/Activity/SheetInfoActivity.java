package cn.edu.stu.max.cocovendor.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.stu.max.cocovendor.JavaClass.SheetInfoAdapter;
import cn.edu.stu.max.cocovendor.R;

public class SheetInfoActivity extends AppCompatActivity {

    private List<Map> list = new ArrayList<>();
    private Map<String, String> map = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_info);
        map.put("machine_id", "00001");
        map.put("server_ip", "10.28.34.6");
        list.add(map);
        ListView listView = (ListView) findViewById(R.id.lv_sheet_info);
        SheetInfoAdapter adapter = new SheetInfoAdapter(this, list);
        listView.setAdapter(adapter);
    }
}
