package com.arbaz.stockzy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arbaz.stockzy.R;
import com.arbaz.stockzy.StockAdapter;
import com.arbaz.stockzy.StockInterface;
import com.arbaz.stockzy.Stocks;
import com.arbaz.stockzy.ViewModel.PriceViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements StockInterface {

    RecyclerView recyclerView;
    StockAdapter stockAdapter;
    PriceViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View Model initialization
        viewModel = new ViewModelProvider(this).get(PriceViewModel.class);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("MainActivity","onStart");
        // observer
        // update UI
        viewModel.getStockList().observe(this, this::displayStocks);

        viewModel.startThread();
    }

    private void displayStocks(List<Stocks> stocksList){
        stockAdapter = new StockAdapter(this,stocksList,this);
        recyclerView.setAdapter(stockAdapter);
    }

    @Override
    public void onStockItemClicked(int position) {
        Intent i = new Intent(this,marketDetailsActivity.class);
        i.putExtra("companyIndex",position);
        startActivity(i);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity","onStop");
        viewModel.stopThread();
    }

}