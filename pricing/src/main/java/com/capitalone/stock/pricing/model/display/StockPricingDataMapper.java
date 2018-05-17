package com.capitalone.stock.pricing.model.display;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capitalone.stock.pricing.model.MonthlyPriceSum;
import com.capitalone.stock.pricing.model.StockStatistics;

public class StockPricingDataMapper {
	public static List<StockPricingData> map(List<StockStatistics> stockStats) {
		List<StockPricingData> mapped = new ArrayList<>();
		for (StockStatistics stock : stockStats) {

			mapped.add(StockPricingData.builder().stockSymbol(stock.getStockSymbol()).busyDays(stock.getBusyDays())
					.losingDaysCount(stock.getLosingDaysCount())
					.maxDailyProfit(convertToCurrency(stock.getMaxDailyProfit()))
					.maxDailyProfitDate(stock.getMaxDailyProfitDate())
					.monthlyAverages(mapMonthlyPriceAverage(stock.getMonthlySums())).build());
		}

		return mapped;
	}

	protected static List<MonthlyPriceAverage> mapMonthlyPriceAverage(HashMap<String, MonthlyPriceSum> monthlySums) {
		List<MonthlyPriceAverage> monthlyAverages = new ArrayList<>();

		for (Map.Entry<String, MonthlyPriceSum> entry : monthlySums.entrySet()) {

			MonthlyPriceSum monthlySum = entry.getValue();
			monthlyAverages.add(MonthlyPriceAverage.builder().yearMonth(entry.getKey())
					.averageOpen(convertToCurrency(monthlySum.getAverageOpen()))
					.averageClose(convertToCurrency(monthlySum.getAverageClose())).build());

		}
		return monthlyAverages;
	}

	protected static String convertToCurrency(BigDecimal value) {
		DecimalFormat formatter = new DecimalFormat();
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);

		return "$" + formatter.format(value);
	}
}
