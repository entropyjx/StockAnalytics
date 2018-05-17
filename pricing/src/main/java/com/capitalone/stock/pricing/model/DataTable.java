package com.capitalone.stock.pricing.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class DataTable {
	//ticker,date,open,close,low,high,volume
	public static final int TICKER_COL = 0;
	public static final int DATE_COL = 1;
	public static final int OPEN_COL = 2;
	public static final int CLOSE_COL = 3;
	public static final int LOW_COL = 4;
	public static final int HIGH_COL = 5;
	public static final int VOLUME_COL = 6;
	@Getter @Setter List<List<Object>> data;
}
