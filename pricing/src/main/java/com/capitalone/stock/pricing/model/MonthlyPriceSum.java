package com.capitalone.stock.pricing.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Data;

@Data
public class MonthlyPriceSum {
	
	BigDecimal openSum;
	BigDecimal closeSum;
	long count;
	
	public MonthlyPriceSum(BigDecimal open, BigDecimal close)
	{
		this.openSum = open;
		this.closeSum = close;
		this.count = 1;
	}
	
	public void addOpenClosePrices(BigDecimal open, BigDecimal close)
	{
		openSum = openSum.add(open);
		closeSum = closeSum.add(close);
		count++;
	}
	
	public BigDecimal getAverageClose()
	{
		return closeSum.divide(BigDecimal.valueOf(count),RoundingMode.HALF_UP);
	}
	
	public BigDecimal getAverageOpen()
	{
		return openSum.divide(BigDecimal.valueOf(count),RoundingMode.HALF_UP);
	}
}
