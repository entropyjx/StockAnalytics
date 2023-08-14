package com.capitalone.stock.pricing.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class StockDailyDataTest {

	@Test
	public void getYearMonth() {
		StockDailyData data = new StockDailyData();
		data.setDate("2017-01-03");
		assertEquals("2017-01",data.getYearMonth());
	}

}
