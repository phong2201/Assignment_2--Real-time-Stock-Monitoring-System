package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockRealtimePriceView implements StockViewer {
    private final Map<String, Double> lastPrices = new HashMap<>();

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement logic to check if price has changed and log it
        Double price = lastPrices.get(stockPrice.getCode());
        if ( price == null )
        {
            lastPrices.put(stockPrice.getCode(), stockPrice.getAvgPrice());
        }
        else if (  price != stockPrice.getAvgPrice())
        {
            lastPrices.remove(stockPrice.getCode());
            lastPrices.put(stockPrice.getCode(), stockPrice.getAvgPrice());
            Logger.logRealtime(stockPrice.getCode(), stockPrice.getAvgPrice());
        }
    }
}
