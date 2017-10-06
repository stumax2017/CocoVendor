package cn.edu.stu.max.cocovendor.JavaClass;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.stu.max.cocovendor.R;

/**
 * Created by 0 on 2017/10/5.
 */

public class MyUSBSettingListAdapter extends BaseAdapter {

    private static List<Map<String, Object>> list;   // 填充数据的List
    //private static

    public static HashMap<Integer, Boolean> isSelected;   // 用来判断CheckBox的选中情况
    public static boolean[] isFileAdded;                 // 用来判断广告添加的情况
    public static HashMap<Integer, String> saveMap;                  // 用来存储对应位置上TextView中的文本内容
    private static int curCount = 1;

    private Context context;   // 上下文

    private LayoutInflater inflater = null;   // 用来导入布局

    private static boolean isSetting = false;

    private static int tvFrequency = 0;

    private String[] usbTvFrequency = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
            "12", "13", "14", "15", "16", "17", "18", "19", "20"};

    public MyUSBSettingListAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        MyUSBSettingListAdapter.list = list;
        inflater = LayoutInflater.from(context);
        isSelected = new HashMap<Integer, Boolean>();
        isFileAdded = new boolean[list.size()];
        initIsSelectedAndIsFileAdded();
        saveMap = new HashMap<Integer, String>();

        for (int i = 0; i < 100; i++) {
            saveMap.put(i, "1");
        }
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
        MyUSBSettingListAdapter.isSelected = tempIsSelected;
        MyUSBSettingListAdapter.isFileAdded = tempIsFileAdded;
    }

    public static void setList(List<Map<String, Object>> list) {
        MyUSBSettingListAdapter.list = list;
    }

    public static void setIsSetting(boolean isSetting) {
        MyUSBSettingListAdapter.isSetting = isSetting;
    }

    public static boolean getIsSetting() {
        return isSetting;
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

            if (isSetting) {
                convertView = inflater.inflate(R.layout.ad_setting_setting_item, null);
                holder.tv_order = (TextView) convertView.findViewById(R.id.ad_setting_setting_item_tv_order);
                holder.btn_minus = (Button) convertView.findViewById(R.id.ad_setting_setting_item_btn_minus);
                holder.tv_frequency = (TextView) convertView.findViewById(R.id.ad_setting_setting_item_tv_frequency);
                holder.btn_plus = (Button) convertView.findViewById(R.id.ad_setting_setting_item_btn_plus);
                holder.img = (ImageView) convertView.findViewById(R.id.ad_setting_setting_item_img);
                holder.title = (TextView) convertView.findViewById(R.id.ad_setting_setting_item_title);
                convertView.setTag(holder);
            } else {
                convertView = inflater.inflate(R.layout.ad_setting_setting_void_item, null);
            }
        } else {
            // 取出holder
            holder = (ViewHolder) convertView.getTag();
        }



        if (isSetting) {
            holder.img.setBackgroundResource((Integer)list.get(position).get("img"));
            holder.title.setText((String)list.get(position).get("title"));
            holder.tv_order.setText((String)list.get(position).get("tv_order"));

//            holder.tv_frequency.setTag(position);
 //           holder.tv_frequency.clearFocus();

//            final TextView tempTextView = holder.tv_frequency;
//            holder.tv_frequency.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable editable) {
//
//                    Integer tag = (Integer) tempTextView.getTag();
//                    saveMap.put(tag, editable.toString());
//
//                }
//            });

            holder.tv_frequency.setText(saveMap.get(position));
            holder.btn_minus.setOnClickListener(new CustomClickListener(position));
            holder.btn_plus.setOnClickListener(new CustomClickListener(position));

        }
        return convertView;
    }

    class CustomClickListener implements View.OnClickListener {
        int position;
        public CustomClickListener(int pos) {
            position = pos;
        }
        @Override
        public void onClick(View view) {
            curCount = Integer.parseInt(saveMap.get(position));
            if (view.getId() == R.id.ad_setting_setting_item_btn_plus) {
                list.get(position).put("tv_frequency", "" + (curCount + 1));
                saveMap.put(position, (String)list.get(position).get("tv_frequency"));
                notifyDataSetChanged();
            } else if (view.getId() == R.id.ad_setting_setting_item_btn_minus) {
                if (curCount > 1) {
                    list.get(position).put("tv_frequency", "" + (curCount - 1));
                    saveMap.put(position, (String)list.get(position).get("tv_frequency"));
                    notifyDataSetChanged();
                }
            }
        }
    }

}
