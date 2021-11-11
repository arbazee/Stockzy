package com.arbaz.stockzy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Stocks> stockList;
    Context context;
    StockInterface listener;

    public StockAdapter(Context context, List<Stocks> stockList, StockInterface listener){
        this.context = context;
        this.stockList = stockList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_info_layout, parent, false);
        return new StockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        StockViewHolder stockViewHolder = (StockViewHolder) holder;

        double changedPrice = stockList.get(position).getChange();
        double value = stockList.get(position).getValue();
        stockViewHolder.stockName.setText(stockList.get(position).getStockName());
        stockViewHolder.companyName.setText(stockList.get(position).getCompanyName());
        stockViewHolder.value.setText(String.valueOf(value));

        String changedText;

        if (changedPrice < 0){
            changedText = String.valueOf(stockList.get(position).getChange());
            stockViewHolder.trendingImage.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.trending_down_icon,null));
            stockViewHolder.changeValue.setTextColor(ResourcesCompat.getColor(context.getResources(),R.color.trending_down_color, null));
        } else if (0 == changedPrice){
            changedText = "0.0";
        }
        else {
            changedText = "+" + stockList.get(position).getChange();
            stockViewHolder.trendingImage.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.trending_up_icon,null));
        }

        stockViewHolder.changeValue.setText(changedText);

        applyClickEvents(stockViewHolder,position);
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private void applyClickEvents(StockViewHolder holder, int position) {

        holder.constraintLayout.setOnClickListener(view -> listener.onStockItemClicked(position));

    }
}
