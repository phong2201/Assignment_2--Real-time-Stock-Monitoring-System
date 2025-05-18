package com.myproject;

import java.util.ArrayList;
import java.util.List;

public class HoseAdapter implements PriceFetcher {
    private HosePriceFetchLib hoseLib;
    private List<String> stockCodes;
 
    public HoseAdapter(HosePriceFetchLib hoseLib, List<String> stockCodes) {
        // TODO: Implement constructor
        this.hoseLib = hoseLib;
        this.stockCodes=stockCodes;
    }

    @Override
    public List<StockPrice> fetch() {
        // TODO: Fetch stock data and convert it to StockPrice list
        List<HoseData> Hose_list = this.hoseLib.getPrices(stockCodes);
        List<StockPrice> Stock_list = new ArrayList<>();
        for (HoseData hose:Hose_list)
        {
            Stock_list.add(convertToStockPrice(hose));
        }
        return Stock_list;
    }

    private StockPrice convertToStockPrice(HoseData hoseData) {
        // TODO: Convert HoseData to StockPrice
        StockPrice stock_price = new StockPrice(hoseData.getStockCode(), hoseData.getPrice(), hoseData.getVolume(), hoseData.getTimestamp());
        return stock_price;
    }
}
