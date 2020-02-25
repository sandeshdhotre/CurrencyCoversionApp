package org.javaindeapth;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class CurrencyConversionController {

	Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);
	
	@Autowired
	CurrencyExchangeProxy proxy;
	
	@RequestMapping(path = "currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	@HystrixCommand(fallbackMethod = "fallbackConvertCurrency", 
	commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
	})
	public CurrencyConversionBean convertCurrency(@PathVariable("from") String from,
			@PathVariable("to") String to,
			@PathVariable("quantity") String quantity) {
		
		logger.info("Start");
		
		Map<String, String> uriVariables = new HashMap<String, String>();
		
		CurrencyConversionBean bean = proxy.getExchangeValue(from, to);
		BigDecimal temp = new BigDecimal(quantity);
		bean.setQuatity(temp);
		bean.setExchangeAmount(temp.multiply(bean.getConversionRate()));
		
		return bean;
	}
	
	public CurrencyConversionBean fallbackConvertCurrency(@PathVariable("from") String from,
			@PathVariable("to") String to,
			@PathVariable("quantity") String quantity) {
		
		CurrencyConversionBean bean = new CurrencyConversionBean();
		bean.setId(1L);
		bean.setConversionRate(new BigDecimal(0));
		bean.setExchangeAmount(new BigDecimal(0));
		bean.setFrom(from);
		bean.setTo(to);
		bean.setQuatity(new BigDecimal(0));
		return bean;
	}
}
