package com.capitalone.stock.pricing.controllers;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capitalone.stock.pricing.model.DataTable;
import com.capitalone.stock.pricing.model.StockStatistics;
import com.capitalone.stock.pricing.model.display.StockPricingData;
import com.capitalone.stock.pricing.model.display.StockPricingDataMapper;
import com.capitalone.stock.pricing.service.StockPriceApi;
import com.capitalone.stock.pricing.service.StockPriceCalculator;

@RestController
public class MonthlyAveragesController {

	@Autowired
	StockPriceApi stockPriceApi;
	
	@Autowired
	StockPriceCalculator stockPriceCalculator;
	
	@RequestMapping(method = {RequestMethod.GET}, value = "monthly-average", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<StockPricingData>> getStockStatistics(
			@RequestParam(value="stocks", required=true) List<String> stockSymbols, 
			@RequestParam(value="month-start", required=true) String monthStart, 
			@RequestParam(value="month-end", required=true) String monthEnd)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
	
		LocalDate start = YearMonth.parse(monthStart, formatter).atDay(1);
		LocalDate end = YearMonth.parse(monthEnd, formatter).atDay(1);
		DataTable dataTable = stockPriceApi.getStockData(stockSymbols, start, end);
		List<StockStatistics> stocks  = stockPriceCalculator.getStockStatistics(dataTable);
		
		List<StockPricingData> mapped = StockPricingDataMapper.map(stocks);
		
		return new ResponseEntity<List<StockPricingData>>(mapped, HttpStatus.OK);
	}
	
	@RequestMapping(method = {RequestMethod.GET}, value = "coding-exercise", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<StockPricingData>> getResultsForCodingExercise(){

		List<String> stockSymbols = new ArrayList<>();
		stockSymbols.add("COF");
		stockSymbols.add("GOOGL");
		stockSymbols.add("MSFT");
		
		return getStockStatistics(stockSymbols,"2017-01","2017-06");
	} 
	
}
