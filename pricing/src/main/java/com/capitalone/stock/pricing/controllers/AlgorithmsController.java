package com.capitalone.stock.pricing.controllers;

import java.util.ArrayList;
import java.util.List;

import com.capitalone.stock.pricing.model.display.StockPricingData;
import com.capitalone.stock.pricing.service.StockPriceApi;
import com.capitalone.stock.pricing.service.StockPriceCalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlgorithmsController {

	@Autowired
    StockPriceApi stockPriceApi;

    @Autowired
    StockPriceCalculator stockPriceCalculator;

    @RequestMapping(method = {
            RequestMethod.GET }, value = "algorithms/sort", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<String>> calculate(
            @RequestParam(value = "values", required = false) List<String> values)
	{
        List<String> result = new ArrayList<String>();
        if(values==null)
        {
            result.add("hello");
            result.add("world") ;
        }
        return new ResponseEntity<List<String>>(result, HttpStatus.OK);
    }
}