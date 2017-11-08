package cn.edu.stu.max.cocovendor.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.databaseClass.Goods;

public class SalesSettingAdapter extends RecyclerView.Adapter {
    private List<Goods> list;
    private Context context;

    public SalesSettingAdapter(List<Goods> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_setting_item, parent, false);
        return new sheetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        sheetViewHolder vh = (sheetViewHolder) holder;

        vh.getTv_sheetRow1().setText(String.valueOf(position + 1));
        if (list.get(position).getName() == null) {
            vh.getIv_sheetRow2().setImageResource(R.color.colorTransparency);
        } else {
            vh.getIv_sheetRow2().setImageResource(list.get(position).getImage_path());
            vh.getTv_sheetRow2().setText(list.get(position).getName());
            vh.getTv_sheetRow3().setText(String.valueOf(list.get(position)));
            vh.getTv_sheetRow4().setText(String.valueOf(list.get(position).getImage_path()));
            vh.getBtn_sheetRow5().setText(String.valueOf(list.get(position).getSales_price()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class sheetViewHolder extends RecyclerView.ViewHolder{
        private final View mView;
        private final TextView tv_sheetRow1;
        private final ImageView iv_sheetRow2;
        private final TextView tv_sheetRow2;
        private final TextView tv_sheetRow3;
        private final TextView tv_sheetRow4;
        private final Button btn_sheetRow5;

        private sheetViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tv_sheetRow1 = (TextView) itemView.findViewById(R.id.tv_sheetRow1);
            iv_sheetRow2 = (ImageView) itemView.findViewById(R.id.iv_sheetRow2);
            tv_sheetRow2 = (TextView) itemView.findViewById(R.id.tv_sheetRow2);
            tv_sheetRow3 = (TextView) itemView.findViewById(R.id.tv_sheetRow3);
            tv_sheetRow4 = (TextView) itemView.findViewById(R.id.tv_sheetRow4);
            btn_sheetRow5 = (Button) itemView.findViewById(R.id.btn_sheetRow5);
        }

        private TextView getTv_sheetRow1() {
            return tv_sheetRow1;
        }

        public ImageView getIv_sheetRow2() {
            return iv_sheetRow2;
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

        public Button getBtn_sheetRow5() {
            return btn_sheetRow5;
        }
    }
}
