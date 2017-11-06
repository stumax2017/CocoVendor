package cn.edu.stu.max.cocovendor.javaClass;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.databaseClass.Goods;

public class SalesSettingAdapter extends RecyclerView.Adapter {
    private List<Goods> list;

    public SalesSettingAdapter(List<Goods> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_setting_item, parent, false);
        return new sheetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        sheetViewHolder vh = (sheetViewHolder) holder;

        vh.getTv_sheetRow1().setText(String.valueOf(list.get(position).getId()));
        vh.getTv_sheetRow2().setText(list.get(position).getName());
        vh.getTv_sheetRow3().setText(String.valueOf(list.get(position).getSales_price()));
        vh.getTv_sheetRow4().setText(String.valueOf(list.get(position).getQuanlity()));
        vh.getTv_sheetRow5().setText(String.valueOf(list.get(position).getSales_price()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class sheetViewHolder extends RecyclerView.ViewHolder{
        private final View mView;
        private final TextView tv_sheetRow1;
        private final TextView tv_sheetRow2;
        private final TextView tv_sheetRow3;
        private final TextView tv_sheetRow4;
        private final TextView tv_sheetRow5;

        private sheetViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tv_sheetRow1 = (TextView) itemView.findViewById(R.id.tv_sheetRow1);
            tv_sheetRow2 = (TextView) itemView.findViewById(R.id.tv_sheetRow2);
            tv_sheetRow3 = (TextView) itemView.findViewById(R.id.tv_sheetRow3);
            tv_sheetRow4 = (TextView) itemView.findViewById(R.id.tv_sheetRow4);
            tv_sheetRow5 = (TextView) itemView.findViewById(R.id.tv_sheetRow5);
        }

        private TextView getTv_sheetRow1() {
            return tv_sheetRow1;
        }

        private TextView getTv_sheetRow2() {
            return tv_sheetRow2;
        }

        private TextView getTv_sheetRow3() {
            return tv_sheetRow3;
        }

        private TextView getTv_sheetRow4() {
            return tv_sheetRow4;
        }

        private TextView getTv_sheetRow5() {
            return tv_sheetRow5;
        }
    }
}
