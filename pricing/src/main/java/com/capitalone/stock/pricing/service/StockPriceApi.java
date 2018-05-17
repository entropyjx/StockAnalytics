package com.capitalone.stock.pricing.service;

import java.time.LocalDate;
import java.util.List;

import com.capitalone.stock.pricing.model.DataTable;

public interface StockPriceApi {

	public DataTable getStockClosingPrices(List<String> stockSymbols, LocalDate start, LocalDate end);

}
