package com.myproject;

import java.util.*;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    private static StockFeeder instance = null;

    // TODO: Implement Singleton pattern
    private StockFeeder() {}

    public static StockFeeder getInstance() {
        // TODO: Implement Singleton logic
        if (instance == null)
        {
            synchronized(StockFeeder.class)
            {
                if (instance == null)
                {
                  instance = new StockFeeder();
                }
            }
        }
        return instance;
    }

    public void addStock(Stock stock) {
        // TODO: Implement adding a stock to stockList 
        for (Stock stock_test:stockList)
        {
            if (stock_test.getCode().equals(stock.getCode()))
            {
                if (stock_test.getName().equals(stock.getName())) return;
                else {
                    stockList.remove(stock_test);
                    break;
                }
            }
        }
        stockList.add(stock);
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        // TODO: Implement registration logic, including checking stock existence
        for (Stock stock:stockList)
        {
            if (stock.getCode().equals(code)) 
            {
                if (viewers.get(code) == null)
                {
                    viewers.put(code, new ArrayList<>());
                }
                else    
                {
                    for ( StockViewer view : viewers.get(code))
                    {
                        if ( view.getClass().equals(stockViewer.getClass()))
                        {
                            Logger.errorRegister(code);
                            return;
                        }
                    }
                }
                viewers.get(code).add(stockViewer);
                return;
            }
        }
        Logger.errorRegister(code);
    }    

    public void unregisterViewer(String code, StockViewer stockViewer) {
        // TODO: Implement unregister logic, including error logging
        if(viewers.containsKey(code))
        {
            viewers.get(code).remove(stockViewer);
            if (viewers.get(code).isEmpty())
            {
                viewers.remove(code);
            }
        }
        else {
            Logger.errorUnregister(code);
        }
    }

    public void notify(StockPrice stockPrice) {
        // TODO: Implement notifying registered viewers about price updates
        List<StockViewer> stock_view = viewers.get(stockPrice.getCode());
        if (!viewers.containsKey(stockPrice.getCode())) return ;
        if (stock_view != null)
        {
            for (StockViewer view:stock_view)
            {
                view.onUpdate(stockPrice);
            }
        }
    }
}
