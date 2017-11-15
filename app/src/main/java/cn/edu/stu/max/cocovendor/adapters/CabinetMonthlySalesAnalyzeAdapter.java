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
import cn.edu.stu.max.cocovendor.databaseClass.CabinetMonthlySales;
import cn.edu.stu.max.cocovendor.databaseClass.Goods;
import cn.edu.stu.max.cocovendor.databaseClass.Sales;

public class CabinetMonthlySalesAnalyzeAdapter extends RecyclerView.Adapter {
    private List<CabinetMonthlySales> list;

    public CabinetMonthlySalesAnalyzeAdapter(List<CabinetMonthlySales> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cabinet_monthly_sales_analyze_item, parent, false);
        return new sheetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        sheetViewHolder vh = (sheetViewHolder) holder;
        vh.getTvSalesDate().setText(String.valueOf(list.get(position).getCabinetMonthlySalesDate()));
        vh.getTvSalesNum().setText(String.valueOf(list.get(position).getCabinetMonthlySalesNum()));
        vh.getTvSalesTotalMoney().setText(String.valueOf(list.get(position).getCabinetMonthlySalesTotalMoney()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class sheetViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView tv_sales_month;
        private final TextView tv_monthly_sales_num;
        private final TextView tv_monthly_sales_total_money;

        private sheetViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            tv_sales_month = (TextView) itemView.findViewById(R.id.text_sales_month);
            tv_monthly_sales_num = (TextView) itemView.findViewById(R.id.text_monthly_sales_num);
            tv_monthly_sales_total_money = (TextView) itemView.findViewById(R.id.text_monthly_sales_total_money);
        }



        private TextView getTvSalesDate() {
            return tv_sales_month;
        }

        private TextView getTvSalesNum() {
            return tv_monthly_sales_num;
        }

        public TextView getTvSalesTotalMoney() {
            return tv_monthly_sales_total_money;
        }

    }
}
