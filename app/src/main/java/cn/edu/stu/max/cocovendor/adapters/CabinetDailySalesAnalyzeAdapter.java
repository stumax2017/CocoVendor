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
import cn.edu.stu.max.cocovendor.databaseClass.CabinetDailySales;
import cn.edu.stu.max.cocovendor.databaseClass.Goods;
import cn.edu.stu.max.cocovendor.databaseClass.Sales;

public class CabinetDailySalesAnalyzeAdapter extends RecyclerView.Adapter {
    private List<CabinetDailySales> list;

    public CabinetDailySalesAnalyzeAdapter(List<CabinetDailySales> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cabinet_daily_sales_analyze_item, parent, false);
        return new sheetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        sheetViewHolder vh = (sheetViewHolder) holder;
        vh.getTvSalesDate().setText(String.valueOf(list.get(position).getCabinetDailySalesDate()));
        vh.getTvSalesNum().setText(String.valueOf(list.get(position).getCabinetDailySalesNum()));
        vh.getTvSalesTotalMoney().setText(String.valueOf(list.get(position).getCabinetDailySalesTotalMoney()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class sheetViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView tv_sales_date;
        private final TextView tv_sales_num;
        private final TextView tv_sales_total_money;

        private sheetViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            tv_sales_date = (TextView) itemView.findViewById(R.id.text_sales_date);
            tv_sales_num = (TextView) itemView.findViewById(R.id.text_sales_num);
            tv_sales_total_money = (TextView) itemView.findViewById(R.id.text_sales_total_money);
        }



        private TextView getTvSalesDate() {
            return tv_sales_date;
        }

        private TextView getTvSalesNum() {
            return tv_sales_num;
        }

        public TextView getTvSalesTotalMoney() {
            return tv_sales_total_money;
        }

    }
}
