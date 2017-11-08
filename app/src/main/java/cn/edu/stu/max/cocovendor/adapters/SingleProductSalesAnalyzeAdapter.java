package cn.edu.stu.max.cocovendor.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.activities.DailySalesActivity;
import cn.edu.stu.max.cocovendor.activities.SingleProductSalesAnalyzeActivity;
import cn.edu.stu.max.cocovendor.databaseClass.Goods;

public class SingleProductSalesAnalyzeAdapter extends RecyclerView.Adapter {
    private List<Goods> list;
    private Context context;

    public SingleProductSalesAnalyzeAdapter(List<Goods> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_product_sales_analyze_item, parent, false);
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

        }
        vh.getTv_sheetRow2().setOnClickListener(new CustomClickListener(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class sheetViewHolder extends RecyclerView.ViewHolder{
        private final View mView;
        private final TextView tv_sheetRow1;
        private final ImageView iv_sheetRow2;
        private final Button tv_sheetRow2;
        private final Button tv_sheetRow3;

        private sheetViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tv_sheetRow1 = (TextView) itemView.findViewById(R.id.text_id);
            iv_sheetRow2 = (ImageView) itemView.findViewById(R.id.text_goods_picture);
            tv_sheetRow2 = (Button) itemView.findViewById(R.id.btn_daily_sales);
            tv_sheetRow3 = (Button) itemView.findViewById(R.id.btn_monthly_sales);
        }

        private TextView getTv_sheetRow1() {
            return tv_sheetRow1;
        }

        public ImageView getIv_sheetRow2() {
            return iv_sheetRow2;
        }

        private Button getTv_sheetRow2() {
            return tv_sheetRow2;
        }

        private Button getTv_sheetRow3() {
            return tv_sheetRow3;
        }
    }

    class CustomClickListener implements View.OnClickListener {
        int position;
        public CustomClickListener(int pos) {
            position = pos;
        }
        @Override
        public void onClick(View view) {
//            curCount = Integer.parseInt(saveMap.get(position));
            if (view.getId() == R.id.btn_daily_sales) {
                Intent intent = new Intent(context, DailySalesActivity.class);
                context.startActivity(intent);
//                list.get(position).put("tv_frequency", "" + (curCount + 1));
//                saveMap.put(position, (String)list.get(position).get("tv_frequency"));
//                notifyDataSetChanged();
            } else if (view.getId() == R.id.ad_setting_setting_item_btn_minus) {
//                if (curCount > 1) {
//                    list.get(position).put("tv_frequency", "" + (curCount - 1));
//                    saveMap.put(position, (String)list.get(position).get("tv_frequency"));
//                    notifyDataSetChanged();
//                }
            }
        }
    }
}
