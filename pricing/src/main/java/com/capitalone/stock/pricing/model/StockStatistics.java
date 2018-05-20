package com.capitalone.stock.pricing.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class StockStatistics {
	String stockSymbol;
	@Setter(AccessLevel.NONE)
	List<StockDailyData> stockDataPoints = new ArrayList<>();
	@Setter(AccessLevel.NONE)
	BigDecimal maxDailyProfit;
	@Setter(AccessLevel.NONE)
	String maxDailyProfitDate;
	@Setter(AccessLevel.NONE)
	List<String> busyDays = new ArrayList<>();
	@Setter(AccessLevel.NONE)
	double volumeSum = 0;
	@Setter(AccessLevel.NONE)
	int days = 0;
	@Setter(AccessLevel.NONE)
	int losingDaysCount = 0;
	@Setter(AccessLevel.NONE)
	HashMap<String, MonthlyPriceSum> monthlySums = new HashMap<>();
	
	public StockStatistics(String stockSymbol, List<StockDailyData> stockDataPoints)
	{
		this.stockSymbol = stockSymbol;
		for(StockDailyData dataPoint : stockDataPoints)
		{
			processData(dataPoint);
		}
	}
	
	public StockStatistics(String stockSymbol, StockDailyData stockDataPoint)
	{
		this.stockSymbol = stockSymbol;
		processData(stockDataPoint);
	}
	
	public void calculateBusyDays(double threshold)
	{
		double averageVolume = getAverageVolume();
		for(StockDailyData data: stockDataPoints)
		{
			if(data.getVolume() > averageVolume)
			{
				busyDays.add(data.getDate());
			}
		}
	}
	
	@JsonIgnore
	public double getAverageVolume() {
		return volumeSum / days;
	}

	public void processData(StockDailyData data) {
		stockDataPoints.add(data);
		days++;
		volumeSum += data.getVolume();
		
		BigDecimal diff = data.getClose().subtract(data.getOpen());
		
		if (diff.compareTo(BigDecimal.ZERO) < 0) {
			losingDaysCount++;
		}
		
		BigDecimal maxProfit = data.getHigh().subtract(data.getLow());
		
		if (maxDailyProfit == null || maxProfit.compareTo(maxDailyProfit) > 0) {
			maxDailyProfit = maxProfit;
			maxDailyProfitDate = data.getDate();
		}
		
		String yearMonth = data.getYearMonth();
		
		if(monthlySums.containsKey(yearMonth))
		{
			monthlySums.get(yearMonth).addOpenClosePrices(data.getOpen(),  data.getClose());
		}
		else
		{
			monthlySums.put(yearMonth, new MonthlyPriceSum(data.getOpen(), data.getClose()));
		}
	}
}
