package com.capitalone.stock.pricing.model.display;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonthlyPriceAverage {
	String yearMonth;
	String averageOpen;
	String averageClose;
}
