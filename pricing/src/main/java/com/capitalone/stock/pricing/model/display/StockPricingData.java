package com.capitalone.stock.pricing.model.display;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockPricingData {
	String stockSymbol;
	String maxDailyProfit;
	String maxDailyProfitDate;
	int losingDaysCount;
	List<String> busyDays;
	List<MonthlyPriceAverage> monthlyAverages;
}
