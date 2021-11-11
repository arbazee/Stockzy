package com.arbaz.stockzy.ViewModel;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.arbaz.stockzy.DataModel.PriceDataModel;
import com.arbaz.stockzy.Stocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class PriceViewModel extends ViewModel {

    private PriceDataModel priceDataModel = new PriceDataModel();
    private MutableLiveData<List<Stocks>> stockList;
    final Handler handler = new Handler();
    Runnable runnable;

    public LiveData<List<Stocks>> getStockList() {
        if (stockList == null) {
            stockList = new MutableLiveData<>();
            loadUsers();
        }
        return stockList;
    }

    private void loadUsers() {
        //  asynchronous operation to fetch users.
        stockList.setValue(priceDataModel.getStockList());
    }


    public void startThread(){

        runnable = new Runnable() {
            public void run() {
                refreshData();
                handler.postDelayed(this, 300);
            }
        };
        handler.postDelayed(runnable,300);
    }

    private void refreshData(){
        stockList.setValue(priceDataModel.getChangingStocks(stockList.getValue()));
    }

    public void stopThread(){
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
