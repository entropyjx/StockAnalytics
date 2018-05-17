package com.capitalone.stock.pricing.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.capitalone.stock.pricing.model.DataTable;
import com.capitalone.stock.pricing.model.StockData;
import com.capitalone.stock.pricing.model.StockStatistics;

@Component
public class StockPriceCalculatorImpl implements StockPriceCalculator {

	@Value("${busyDayThreshold}")
	double busyDay;
	
	protected String convertToCurrency(BigDecimal value) {
		DecimalFormat formatter = new DecimalFormat();
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);

		return "$" + formatter.format(value);
	}

	protected StockData getStockData(List<Object> row) {
		String date = (String) row.get(DataTable.DATE_COL);

		// Using BigDecimal as it's the most precise data type to use when
		// dealing with currency, especially averaging.
		BigDecimal open = BigDecimal.valueOf((Double) row.get(DataTable.OPEN_COL));
		BigDecimal close = BigDecimal.valueOf((Double) row.get(DataTable.CLOSE_COL));
		BigDecimal high = BigDecimal.valueOf((Double) row.get(DataTable.HIGH_COL));
		BigDecimal low = BigDecimal.valueOf((Double) row.get(DataTable.LOW_COL));
		Double volume = (Double) row.get(DataTable.VOLUME_COL);

		StockData data = StockData.builder().date(date).open(open).close(close).high(high).low(low).volume(volume)
				.build();

		return data;
	}

	@Override
	public List<StockStatistics>  getStockStatistics(DataTable dataTable) {

		HashMap<String, StockStatistics> stocksStatsMap = new HashMap<>();

		for (List<Object> row : dataTable.getData()) {

			String stockSymbol = (String) row.get(DataTable.TICKER_COL);

			StockData stockData = getStockData(row);

			if (stocksStatsMap.containsKey(stockSymbol)) {
				StockStatistics myData = stocksStatsMap.get(stockSymbol);
				myData.processData(stockData);
			} else {
				stocksStatsMap.put(stockSymbol, new StockStatistics(stockSymbol, stockData));
			}
		}
		
		List<StockStatistics> stockStats = new ArrayList<>(stocksStatsMap.values());
				
		setBusyDays(stockStats);
		
		return stockStats;
	}
	
	public void setBusyDays(List<StockStatistics> stockStatistics)
	{
		for(StockStatistics stock : stockStatistics)
		{
			stock.calculateBusyDays(busyDay);
		}
	}
}
