package cn.edu.stu.max.cocovendor.javaClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.stu.max.cocovendor.R;

/**
 * Created by 0 on 2017/10/4.
 */

public class MyInternalListAdapter extends BaseAdapter {

    private static List<Map<String, Object>> list;   // 填充数据的List

    public static HashMap<Integer, Boolean> isSelected;   // 用来判断CheckBox的选中情况
    public static boolean[] isFileAdded;                 // 用来判断广告添加的情况

    private Context context;   // 上下文

    private LayoutInflater inflater = null;   // 用来导入布局

    public MyInternalListAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        MyInternalListAdapter.list = list;
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
        MyInternalListAdapter.isSelected = tempIsSelected;
        MyInternalListAdapter.isFileAdded = tempIsFileAdded;
    }

    public static void setList(List<Map<String, Object>> list) {
        MyInternalListAdapter.list = list;
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
            convertView = inflater.inflate(R.layout.ad_setting_display_item, null);
            holder.img = (ImageView) convertView.findViewById(R.id.ad_setting_display_item_img);
            holder.title = (TextView) convertView.findViewById(R.id.ad_setting_display_item_title);
            holder.cb = (CheckBox) convertView.findViewById(R.id.ad_setting_display_item_cb);
            // 为view设置标签
            convertView.setTag(holder);
        } else {
            // 取出holder
            holder = (ViewHolder) convertView.getTag();
        }

        holder.img.setBackgroundResource((Integer)list.get(position).get("img"));
        holder.title.setText((String)list.get(position).get("title"));

        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    isSelected.put(position, true);
                    isFileAdded[position] = true;
                } else {
                    isSelected.put(position, false);
                    isFileAdded[position] = false;
                }
            }
        });

        holder.cb.setChecked(getIsSelected().get(position));

        return convertView;
    }
}
