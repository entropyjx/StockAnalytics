package com.capitalone.stock.pricing.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StockStatisticsTest {

	StockStatistics stock;
	
	@Before
	public void init()
	{
		stock = new StockStatistics("MSFT",createStockDataPoints());
	}
	
	@Test
	public void calculateBusyDays() {

		stock.calculateBusyDays(0.10);
		
		List<String> expected = new ArrayList<>();
		expected.add("2017-01-02");
		expected.add("2017-01-03");
		expected.add("2017-01-04");
		assertEquals(expected,stock.getBusyDays());
		
	}
	
	@Test
	public void averageVolume() {
		double expected = 77.5;
		assertEquals(expected,stock.getAverageVolume(),0.001);
	}
	
	@Test
	public void processDataResults() {
		BigDecimal maxDailyProfit = BigDecimal.valueOf(2.3);
		String maxDailyProfitDate = "2017-01-01";
		double volumeSum = 310.0;
		int days = 4;
		int losingDaysCount = 1;
		MonthlyPriceSum monthlySum = new MonthlyPriceSum();
		monthlySum.setOpenSum(BigDecimal.valueOf(14.73));
		monthlySum.setCloseSum(BigDecimal.valueOf(17.46));
		monthlySum.setCount(4);
		
		assertEquals(maxDailyProfit, stock.getMaxDailyProfit());
		assertEquals(maxDailyProfitDate, stock.getMaxDailyProfitDate());
		assertEquals(volumeSum,stock.getVolumeSum(),0.001);
		assertEquals(days, stock.getDays());
		assertEquals(losingDaysCount, stock.getLosingDaysCount());
		assertEquals(monthlySum, stock.getMonthlySums().get("2017-01"));
		
	}
	
	private List<StockDailyData> createStockDataPoints()
	{
		List<StockDailyData> dataPoints = new ArrayList<>();
		dataPoints.add(stockData1());
		dataPoints.add(stockData2());
		dataPoints.add(stockData3());
		dataPoints.add(stockData4());
		return dataPoints;
	}
	
	private StockDailyData stockData1()
	{
		StockDailyData stockData = new StockDailyData();
		stockData.setDate("2017-01-01");
		stockData.setOpen(BigDecimal.valueOf(2.34));
		stockData.setClose(BigDecimal.valueOf(3.45));
		stockData.setLow(BigDecimal.valueOf(1.20));
		stockData.setHigh(BigDecimal.valueOf(3.5));
		stockData.setVolume(10);
		return stockData;
	}

	private StockDailyData stockData2()
	{
		StockDailyData stockData = new StockDailyData();
		stockData.setDate("2017-01-02");
		stockData.setOpen(BigDecimal.valueOf(3.52));
		stockData.setClose(BigDecimal.valueOf(4.51));
		stockData.setLow(BigDecimal.valueOf(3.25));
		stockData.setHigh(BigDecimal.valueOf(4.61));
		stockData.setVolume(100);
		return stockData;
	}
	
	private StockDailyData stockData3()
	{
		StockDailyData stockData = new StockDailyData();
		stockData.setDate("2017-01-03");
		stockData.setOpen(BigDecimal.valueOf(4.55));
		stockData.setClose(BigDecimal.valueOf(4.25));
		stockData.setLow(BigDecimal.valueOf(4.10));
		stockData.setHigh(BigDecimal.valueOf(5.20));
		stockData.setVolume(100);
		return stockData;
	}
	
	private StockDailyData stockData4()
	{
		StockDailyData stockData = new StockDailyData();
		stockData.setDate("2017-01-04");
		stockData.setOpen(BigDecimal.valueOf(4.32));
		stockData.setClose(BigDecimal.valueOf(5.25));
		stockData.setLow(BigDecimal.valueOf(4.20));
		stockData.setHigh(BigDecimal.valueOf(5.50));
		stockData.setVolume(100);
		return stockData;
	}
}
