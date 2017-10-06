package cn.edu.stu.max.cocovendor.JavaClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.stu.max.cocovendor.R;

/**
 * Created by 0 on 2017/10/5.
 */

public class MySettingListAdapter extends BaseAdapter {

    private static List<Map<String, Object>> list;   // 填充数据的List

    public static HashMap<Integer, Boolean> isSelected;   // 用来判断CheckBox的选中情况
    public static boolean[] isFileAdded;                 // 用来判断广告添加的情况

    private Context context;   // 上下文

    private LayoutInflater inflater = null;   // 用来导入布局

    public MySettingListAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        MySettingListAdapter.list = list;
        inflater = LayoutInflater.from(context);
        isSelected = new HashMap<Integer, Boolean>();
        isFileAdded = new boolean[list.size()];
        initIsSelectedAndIsFileAdded();
    }

    public void initIsSelectedAndIsFileAdded() {
        for (int i = 0; i < list.size(); i++) {
            getIsSelected().put(i, false);
            isFileAdded[i] = false;
        }
    }

    public static void setIsSelectedAndIsFileAdded(List<Map<String, Object>> list) {
        HashMap<Integer, Boolean> tempIsSelected = new HashMap<Integer, Boolean>();
        boolean[] tempIsFileAdded = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tempIsSelected.put(i, false);
            tempIsFileAdded[i] = false;
        }
        MySettingListAdapter.isSelected = tempIsSelected;
        MySettingListAdapter.isFileAdded = tempIsFileAdded;
    }

    public static void setList(List<Map<String, Object>> list) {
        MySettingListAdapter.list = list;
    }

    public static HashMap<Integer, Boolean> getIsSelected() { return isSelected; }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            // 获得ViewHolder对象
            holder = new ViewHolder();
            // 导入布局并赋值给convertView
            convertView = inflater.inflate(R.layout.ad_setting_setting_item, null);
            holder.img = (ImageView) convertView.findViewById(R.id.ad_setting_setting_item_img);
            holder.title = (TextView) convertView.findViewById(R.id.ad_setting_setting_item_title);
            holder.etOrder = (EditText) convertView.findViewById(R.id.ad_setting_setting_item_et_order);
            holder.etFrequency = (EditText) convertView.findViewById(R.id.ad_setting_setting_item_et_frequency);
            // 为view设置标签
            convertView.setTag(holder);
        } else {
            // 取出holder
            holder = (ViewHolder) convertView.getTag();
        }

        holder.img.setBackgroundResource((Integer)list.get(position).get("img"));
        holder.title.setText((String)list.get(position).get("title"));

        return convertView;
    }
}
