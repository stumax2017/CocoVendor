package cn.edu.stu.max.cocovendor.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.edu.stu.max.cocovendor.R;
import cn.edu.stu.max.cocovendor.databaseClass.Sales;

public class SheetSalesAdapter extends RecyclerView.Adapter<SheetSalesAdapter.ViewHolder>{
    private List<Sales> list;

    public SheetSalesAdapter(List<Sales> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sheet_sales_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_row_id.setText(String.valueOf(list.get(position).getId()));
        holder.tv_row_date.setText(String.valueOf(list.get(position).getSales_date()));
        holder.tv_row_tradeId.setText(list.get(position).getTrade_id());
        holder.tv_row_name.setText(list.get(position).getGoods_name());
        holder.tv_row_payWay.setText(list.get(position).getPay_way());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tv_row_id;
        final TextView tv_row_date;
        final TextView tv_row_tradeId;
        final TextView tv_row_name;
        final TextView tv_row_payWay;

        private ViewHolder(View itemView) {
            super(itemView);
            tv_row_id = (TextView) itemView.findViewById(R.id.tv_row_id);
            tv_row_date = (TextView) itemView.findViewById(R.id.tv_row_date);
            tv_row_tradeId = (TextView) itemView.findViewById(R.id.tv_row_tradeId);
            tv_row_name = (TextView) itemView.findViewById(R.id.tv_row_name);
            tv_row_payWay = (TextView) itemView.findViewById(R.id.tv_row_payWay);
        }
    }
}
