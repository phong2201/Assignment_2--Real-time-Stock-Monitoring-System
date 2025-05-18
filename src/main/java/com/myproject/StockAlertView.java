package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock

    public StockAlertView(double highThreshold, double lowThreshold) {
        // TODO: Implement constructor
        this.alertThresholdHigh=highThreshold;
        this.alertThresholdLow=lowThreshold;
    }

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement alert logic based on threshold conditions
        String code = stockPrice.getCode();
        Double price = stockPrice.getAvgPrice();
        Double Alert_price = lastAlertedPrices.get(code);
        if (price >= this.alertThresholdHigh && (Alert_price == null || !Alert_price.equals(price)))
        {
            Logger.logAlert(code, price);
        }
        else if ( price <= alertThresholdLow && (Alert_price == null || !Alert_price.equals(price)))
        {
            Logger.logAlert(code, price);
        }
        lastAlertedPrices.put(code,price);
    }

    private void alertAbove(String stockCode, double price) {
        // TODO: Call Logger to log the alert
        Logger.notImplementedYet("alertAbove");
    }

    private void alertBelow(String stockCode, double price) {
        // TODO: Call Logger to log the alert
        Logger.notImplementedYet("alertBelow");
    }
}
