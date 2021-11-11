package com.arbaz.stockzy;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class StockViewHolder extends RecyclerView.ViewHolder {

    TextView stockName,companyName, value, changeValue;
    ImageView trendingImage;
    ConstraintLayout constraintLayout;

    public StockViewHolder(@NonNull View itemView) {
        super(itemView);

        stockName = itemView.findViewById(R.id.stockName);
        value = itemView.findViewById(R.id.stockPrice);
        companyName = itemView.findViewById(R.id.companyName);
        changeValue = itemView.findViewById(R.id.change);
        trendingImage = itemView.findViewById(R.id.trendingImage);
        constraintLayout = itemView.findViewById(R.id.stock_listing_layout);
    }
}
