package com.capitalone.stock.pricing.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.capitalone.stock.pricing.model.DataTable;
import com.capitalone.stock.pricing.model.DataTableContainer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StockPriceApiImplTest {

	final String apiUrl = "http://localhost:8080";
	
	StockPriceApiImpl service = new StockPriceApiImpl();
	RestTemplate restTemplate;
	
	@Captor ArgumentCaptor<URI> uriCaptor = ArgumentCaptor.forClass(URI.class);
	@Captor ArgumentCaptor<Class<DataTableContainer>> classCaptor;
	
	@Before
	public void init()
	{
		restTemplate = mock(RestTemplate.class);
				
		ReflectionTestUtils.setField(service,  "restTemplate", restTemplate);
		ReflectionTestUtils.setField(service, "apiUrl", apiUrl);
		ReflectionTestUtils.setField(service, "apiKey", "XYZ");	
	}
	
	public void setReturn(Object body)
	{
		ResponseEntity<Object> result = new ResponseEntity<>(body, HttpStatus.OK);
		
		when(
			restTemplate.getForEntity(uriCaptor.capture(), Mockito.any())
				).thenReturn(result);

	}
	
	// @Test
	// public void getStockData_VerifyApiCall() {
		
	// 	setReturn(null);
		
	// 	List<String> stocks = new ArrayList<>();
	// 	stocks.add("MSFT");
	// 	stocks.add("AAPL");
	// 	DataTable dataTable = service.getStockData(stocks, LocalDate.parse("2017-01-01"), LocalDate.parse("2017-02-01"));
	// 	URI value = uriCaptor.getValue();
	// 	String expectedUrl = apiUrl + "/WIKI/PRICES.json?ticker=MSFT,AAPL&date.gte=2017-01-01&date.lte=2017-02-01&qopts.columns=ticker,date,open,close,low,high,volume&api_key=XYZ";
	// 	assertEquals(expectedUrl,value.toString());
	// 	assertTrue(dataTable==null);
	// }
	
	// @Test
	// public void getStockData_DataTableReturned() throws JsonParseException, JsonMappingException, IOException {
	// 	String testData = "{\"datatable\":{\"data\":[[\"COF\",\"2017-01-03\",88.55,88.87,87.79,89.6,3441067.0],[\"COF\",\"2017-01-04\",89.13,90.3,89.13,90.77,2630905.0],[\"GOOGL\",\"2017-01-03\",800.62,808.01,796.89,811.435,1959033.0],[\"GOOGL\",\"2017-01-04\",809.89,807.77,804.11,813.43,1515339.0],[\"MSFT\",\"2017-01-03\",62.79,62.58,62.125,62.84,20694101.0],[\"MSFT\",\"2017-01-04\",62.48,62.3,62.12,62.75,21339969.0]]}}{\"datatable\":{\"data\":[[\"COF\",\"2017-01-03\",88.55,88.87,87.79,89.6,3441067.0],[\"COF\",\"2017-01-04\",89.13,90.3,89.13,90.77,2630905.0],[\"GOOGL\",\"2017-01-03\",800.62,808.01,796.89,811.435,1959033.0],[\"GOOGL\",\"2017-01-04\",809.89,807.77,804.11,813.43,1515339.0],[\"MSFT\",\"2017-01-03\",62.79,62.58,62.125,62.84,20694101.0],[\"MSFT\",\"2017-01-04\",62.48,62.3,62.12,62.75,21339969.0]]}}";
	// 	ObjectMapper mapper = new ObjectMapper();
	// 	DataTableContainer body = mapper.readValue(testData, DataTableContainer.class);
	
	// 	setReturn(body);
		
	// 	List<String> stocks = new ArrayList<>();
	// 	stocks.add("COF");
	// 	stocks.add("GOOG");
	// 	stocks.add("MSFT");
	// 	DataTable dataTable = service.getStockData(stocks, LocalDate.parse("2017-01-03"),  LocalDate.parse("2017-01-04"));
	// 	assertEquals(body.getDatatable(),dataTable);
	// }

}
