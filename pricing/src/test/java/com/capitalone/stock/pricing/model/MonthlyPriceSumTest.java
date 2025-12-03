package com.capitalone.stock.pricing.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class MonthlyPriceSumTest {

	@Test
	public void emptyConstructor() {
		MonthlyPriceSum sum = new MonthlyPriceSum();
		BigDecimal all = sum.getOpenSum().add(sum.getCloseSum()).add(BigDecimal.valueOf(sum.getCount()));
		assertEquals(BigDecimal.ZERO, all);
	}

	@Test
	public void addOpenClosePrices_Once() {
		BigDecimal open = BigDecimal.valueOf(1.2);
		BigDecimal close = BigDecimal.valueOf(2.4);
		MonthlyPriceSum sum = new MonthlyPriceSum();
		sum.addOpenClosePrices(open, close);

		assertEquals(open, sum.getOpenSum());
		assertEquals(close, sum.getCloseSum());
	}

	@Test
	public void addOpenClosePrices_Many() {
		BigDecimal open1 = BigDecimal.valueOf(1.2);
		BigDecimal close1 = BigDecimal.valueOf(2.4);
		BigDecimal open2 = BigDecimal.valueOf(2.6);
		BigDecimal close2 = BigDecimal.valueOf(3.4);
		BigDecimal open3 = BigDecimal.valueOf(3.5);
		BigDecimal close3 = BigDecimal.valueOf(5.4);
		MonthlyPriceSum sum = new MonthlyPriceSum();
		sum.addOpenClosePrices(open1, close1);
		sum.addOpenClosePrices(open2, close2);
		sum.addOpenClosePrices(open3, close3);
	
		BigDecimal expectedOpen = open1.add(open2).add(open3);
		BigDecimal expectedClose = close1.add(close2).add(close3);
		
		assertEquals(expectedOpen, sum.getOpenSum());
		assertEquals(expectedClose, sum.getCloseSum());
	
		BigDecimal expectedAvgClose = BigDecimal.valueOf(3.73);
		BigDecimal expectedAvgOpen = BigDecimal.valueOf(2.43);
		
		assertEquals(expectedAvgClose,sum.getAverageClose());
		assertEquals(expectedAvgOpen,sum.getAverageOpen());
	}

}
