package com.capitalone.stock.pricing.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDailyData {
	String date;
	BigDecimal open, close, low, high;
	double volume;
	
	@JsonIgnore
	public String getYearMonth()
	{
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
		return date.substring(0, 7);
		//return date.format(formatter);
	}
}
