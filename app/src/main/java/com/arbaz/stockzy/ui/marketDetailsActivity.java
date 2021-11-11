package com.arbaz.stockzy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import com.arbaz.stockzy.R;
import com.arbaz.stockzy.Stocks;
import com.arbaz.stockzy.ViewModel.PriceViewModel;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class marketDetailsActivity extends AppCompatActivity {

    TextView stockName,companyName, stockValue, changeValue;
    ImageView trendingImage;
    PriceViewModel viewModel;
    private int companyIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_details);

        stockName = findViewById(R.id.stockName);
        stockValue = findViewById(R.id.stockPrice);
        companyName = findViewById(R.id.companyName);
        changeValue = findViewById(R.id.change);
        trendingImage = findViewById(R.id.trendingImage);

        // get the index value
        companyIndex = getIntent().getIntExtra("companyIndex",0);

        // View Model initialization
        viewModel = new ViewModelProvider(this).get(PriceViewModel.class);

        // setting the back action
        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("marketDetails","onStart");
        // observer
        // update UI
        viewModel.getStockList().observe(this,stocks -> {
            Stocks s  = stocks.get(companyIndex);
            displayStockInfo(s);
        });

        viewModel.startThread();
    }

    private void displayStockInfo(Stocks s){

        companyName.setText(s.getCompanyName());
        stockName.setText(s.getStockName());

        double changedPrice = s.getChange();
        double value = s.getValue();
        stockValue.setText(String.valueOf(value));

        String changedText;

        if (changedPrice < 0){
            changedText = String.valueOf(s.getChange());
            trendingImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.trending_down_icon,null));
            changeValue.setTextColor(ResourcesCompat.getColor(getResources(),R.color.trending_down_color, null));
        } else if (0 == changedPrice){
            changedText = "0.0";
        }
        else {
            changedText = "+" + changedPrice;
            trendingImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.trending_up_icon,null));
            changeValue.setTextColor(ResourcesCompat.getColor(getResources(),R.color.trending_up_color, null));
        }

        changeValue.setText(changedText);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("marketDetails","onStop");
        viewModel.stopThread();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}