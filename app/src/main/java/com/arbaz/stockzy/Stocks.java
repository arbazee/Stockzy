package com.arbaz.stockzy;

public class Stocks {

    private String stockName,companyName;
    private double value,change,defaultValue;
    private double lowestValue, highestValue;

    public Stocks(String stockName,String companyName, double value,
                  double change, double defaultValue, double lowestValue,
                  double highestValue){

        this.stockName = stockName;
        this.companyName = companyName;
        this.value = value;
        this.change = change;
        this.defaultValue = defaultValue;
        this.lowestValue = lowestValue;
        this.highestValue = highestValue;
    }

    public String getStockName() {
        return stockName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getChange() {
        return change;
    }

    public double getHighestValue() {
        return highestValue;
    }

    public double getLowestValue() {
        return lowestValue;
    }
}
