package com.arbaz.stockzy.DataModel;

import android.util.Log;

import com.arbaz.stockzy.Stocks;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PriceDataModel {


    public List<Stocks> getStockList() {
        //  asynchronous operation to fetch users.

        double da = generateRandomPrice(197.43,234.54);
        Stocks apple = new Stocks("Apple","Apple Inc.",
                da,da - 201.23,201.23,197.43,234.54);

        double dt = generateRandomPrice(984.23,1303.24);
        Stocks tesla = new Stocks("Tesla","Tesla Inc.",
                dt,139.44,1023.50,984.23,1303.24);

        double dam = generateRandomPrice(3200.54,3210.23);
        Stocks amazon = new Stocks("Amazon","Amazon.com Inc.",
                dam,dam - 3200.54,3203.42,3200.54,3210.23);


        Stocks samsung = new Stocks("Samsung","Samsung Electronics Co. Ltd",
                7041.84,0,7041.84,6901.12,7302.34);

        List<Stocks> s = new ArrayList<>();
        s.add(apple);
        s.add(tesla);
        s.add(amazon);
        s.add(samsung);
        return s;

    }

    public List<Stocks> getChangingStocks(List<Stocks> stocks){

        double min,max,currentValue;

        for (int i=0;i<stocks.size()-1;i++) {
            min = stocks.get(i).getLowestValue();
            max = stocks.get(i).getHighestValue();
            currentValue = generateRandomPrice(min ,max);

            double changePrice = decimalPlaces(currentValue - stocks.get(i).getValue());

            stocks.get(i).setValue(currentValue);
            stocks.get(i).setChange(changePrice);

        }

        return stocks;
    }

    private double generateRandomPrice(double min, double max) {

        Random r = new Random();
        double randomValue = min + (max - min) * r.nextDouble();

        return decimalPlaces(randomValue);
    }

    private double decimalPlaces(double value){
        DecimalFormat limitDecimal = new DecimalFormat("#.##");

        return Double.parseDouble(limitDecimal.format(value));
    }

}
