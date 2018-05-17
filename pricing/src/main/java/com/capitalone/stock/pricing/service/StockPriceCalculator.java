package com.capitalone.stock.pricing.service;

import java.util.List;

import com.capitalone.stock.pricing.model.DataTable;
import com.capitalone.stock.pricing.model.StockStatistics;

public interface StockPriceCalculator {
	public List<StockStatistics>  getStockStatistics(DataTable dataTable);
}
