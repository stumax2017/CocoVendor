package cn.edu.stu.max.cocovendor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import cn.edu.stu.max.cocovendor.R;

public class SheetInfoAdapter extends BaseAdapter {
    private List<Map> list;
    private LayoutInflater inflater = null;

    public SheetInfoAdapter(Context context, List<Map> list) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.sheet_info_item, parent, false);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.tv_sheet_info);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(list.get(position).toString());
        return convertView;
    }

    //静态内部类
    static class ViewHolder {
        TextView textView;
    }
}
