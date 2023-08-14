package com.capitalone.stock.pricing.service;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.capitalone.stock.pricing.model.DataTable;
import com.capitalone.stock.pricing.model.DataTableContainer;

@Component
public class StockPriceApiImpl implements StockPriceApi {
	//Values taken from application.properties file
	@Value("${apiKey}")
	String apiKey;

	@Value("${apiUrl}")
	String apiUrl; // https://www.quandl.com/api/v3/datatables

	@Autowired
	RestTemplate restTemplate;

	@Override
	public DataTable getStockData(List<String> stockSymbols, LocalDate start, LocalDate end) {
		String stockSymbolsJoined = String.join(",", stockSymbols);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String startString = start.format(formatter);
		String endString = end.format(formatter);
		
		String requestUrl = String.format(
				"%s/WIKI/PRICES.json?ticker=%s&date.gte=%s&date.lte=%s&qopts.columns=%s&api_key=%s", 
				apiUrl,
				stockSymbolsJoined, 
				startString, 
				endString, 
				"ticker,date,open,close,low,high,volume",
				apiKey);

		URI uri = UriComponentsBuilder.fromUriString(requestUrl).build().toUri();
		ResponseEntity<DataTableContainer> response = restTemplate.getForEntity(uri, DataTableContainer.class);

		if (response.getBody() == null) {
			return null;
		}

		DataTable result = response.getBody().getDatatable();
		return result;
	}
}
