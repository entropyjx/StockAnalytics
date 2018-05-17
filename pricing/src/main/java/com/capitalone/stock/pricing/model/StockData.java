package com.capitalone.stock.pricing.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockData {
	String date;
	BigDecimal open, close, low, high;
	double volume;
	
	public String getYearMonth()
	{
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
		return date.substring(0, 7);
		//return date.format(formatter);
	}
}
