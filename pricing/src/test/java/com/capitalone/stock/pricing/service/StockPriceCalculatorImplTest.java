package com.capitalone.stock.pricing.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.capitalone.stock.pricing.model.DataTable;
import com.capitalone.stock.pricing.model.DataTableContainer;
import com.capitalone.stock.pricing.model.StockDailyData;
import com.capitalone.stock.pricing.model.StockStatistics;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StockPriceCalculatorImplTest {

	StockPriceCalculatorImpl calculator;

	@Before
	public void init()
	{
		calculator = new StockPriceCalculatorImpl();
	}

	@Test
	public void convertToCurrency() {
		BigDecimal value = new BigDecimal("103.234");
		String expected = "$103.23";

		String actual = calculator.convertToCurrency(value);

		assertEquals(expected, actual);
	}
	
	@Test
	public void getStockDailyData() {
		List<Object> row = new ArrayList<>();

		String date = "2017-01-03";
		row.add("MSFT");
		row.add(date);
		//Open
		Double open = Double.valueOf(50.12);
		row.add(open);
		//Close
		Double close = Double.valueOf(52.34);
		row.add(close);
		//Low
		Double low = Double.valueOf(48.54);
		row.add(low);
		//High
		Double high = Double.valueOf(53.12);
		row.add(high);
		//Volume
		Double volume = Double.valueOf(3241.0);
		row.add(volume);
		
		StockDailyData expected = StockDailyData.builder().date(date).open(BigDecimal.valueOf(open))
				.close(BigDecimal.valueOf(close))
				.high(BigDecimal.valueOf(high))
				.low(BigDecimal.valueOf(low))
				.volume(volume).build();
		
		
		StockDailyData actual = calculator.getStockDailyData(row);
		assertEquals(actual,expected);
	}
	
	@Test
	public void getStockStatistics() throws JsonParseException, JsonMappingException, IOException
	{
		String testData = "{\"datatable\":{\"data\":[[\"COF\",\"2017-01-03\",88.55,88.87,87.79,89.6,3441067.0],[\"COF\",\"2017-01-04\",89.13,90.3,89.13,90.77,2630905.0],[\"GOOGL\",\"2017-01-03\",800.62,808.01,796.89,811.435,1959033.0],[\"GOOGL\",\"2017-01-04\",809.89,807.77,804.11,813.43,1515339.0],[\"MSFT\",\"2017-01-03\",62.79,62.58,62.125,62.84,20694101.0],[\"MSFT\",\"2017-01-04\",62.48,62.3,62.12,62.75,21339969.0]]}}{\"datatable\":{\"data\":[[\"COF\",\"2017-01-03\",88.55,88.87,87.79,89.6,3441067.0],[\"COF\",\"2017-01-04\",89.13,90.3,89.13,90.77,2630905.0],[\"GOOGL\",\"2017-01-03\",800.62,808.01,796.89,811.435,1959033.0],[\"GOOGL\",\"2017-01-04\",809.89,807.77,804.11,813.43,1515339.0],[\"MSFT\",\"2017-01-03\",62.79,62.58,62.125,62.84,20694101.0],[\"MSFT\",\"2017-01-04\",62.48,62.3,62.12,62.75,21339969.0]]}}";
		ObjectMapper mapper = new ObjectMapper();
		DataTable dataTable = mapper.readValue(testData, DataTableContainer.class).getDatatable();
		List<StockStatistics> actualList = calculator.getStockStatistics(dataTable);
		String expectedjson = "[{\"stockSymbol\":\"MSFT\",\"stockDataPoints\":[{\"date\":\"2017-01-03\",\"open\":62.79,\"close\":62.58,\"low\":62.125,\"high\":62.84,\"volume\":2.0694101E7},{\"date\":\"2017-01-04\",\"open\":62.48,\"close\":62.3,\"low\":62.12,\"high\":62.75,\"volume\":2.1339969E7}],\"maxDailyProfit\":0.715,\"maxDailyProfitDate\":\"2017-01-03\",\"busyDays\":[\"2017-01-04\"],\"volumeSum\":4.203407E7,\"days\":2,\"losingDaysCount\":2,\"monthlySums\":{\"2017-01\":{\"openSum\":125.27,\"closeSum\":124.88,\"count\":2}}},{\"stockSymbol\":\"GOOGL\",\"stockDataPoints\":[{\"date\":\"2017-01-03\",\"open\":800.62,\"close\":808.01,\"low\":796.89,\"high\":811.435,\"volume\":1959033.0},{\"date\":\"2017-01-04\",\"open\":809.89,\"close\":807.77,\"low\":804.11,\"high\":813.43,\"volume\":1515339.0}],\"maxDailyProfit\":14.545,\"maxDailyProfitDate\":\"2017-01-03\",\"busyDays\":[\"2017-01-03\"],\"volumeSum\":3474372.0,\"days\":2,\"losingDaysCount\":1,\"monthlySums\":{\"2017-01\":{\"openSum\":1610.51,\"closeSum\":1615.78,\"count\":2}}},{\"stockSymbol\":\"COF\",\"stockDataPoints\":[{\"date\":\"2017-01-03\",\"open\":88.55,\"close\":88.87,\"low\":87.79,\"high\":89.6,\"volume\":3441067.0},{\"date\":\"2017-01-04\",\"open\":89.13,\"close\":90.3,\"low\":89.13,\"high\":90.77,\"volume\":2630905.0}],\"maxDailyProfit\":1.81,\"maxDailyProfitDate\":\"2017-01-03\",\"busyDays\":[\"2017-01-03\"],\"volumeSum\":6071972.0,\"days\":2,\"losingDaysCount\":0,\"monthlySums\":{\"2017-01\":{\"openSum\":177.68,\"closeSum\":179.17,\"count\":2}}}]";
		StockStatistics[] expected = mapper.readValue(expectedjson,  StockStatistics[].class);	
		Assert.assertArrayEquals(expected, actualList.toArray());
	}

}
