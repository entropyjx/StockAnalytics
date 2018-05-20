package com.capitalone.stock.pricing.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class MonthlyPriceSum {

	BigDecimal openSum;
	BigDecimal closeSum;
	long count;

	public MonthlyPriceSum() {
		openSum = BigDecimal.ZERO;
		closeSum = BigDecimal.ZERO;
		count = 0;
	}

	public MonthlyPriceSum(BigDecimal open, BigDecimal close) {
		this.openSum = open;
		this.closeSum = close;
		this.count = 1;
	}

	public void addOpenClosePrices(BigDecimal open, BigDecimal close) {
		openSum = openSum.add(open);
		closeSum = closeSum.add(close);
		count++;
	}

	@JsonIgnore
	public BigDecimal getAverageClose() {
		return closeSum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);
	}

	@JsonIgnore
	public BigDecimal getAverageOpen() {
		return openSum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);
	}
}
