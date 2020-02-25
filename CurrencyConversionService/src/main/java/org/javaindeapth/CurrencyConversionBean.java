package org.javaindeapth;

import java.math.BigDecimal;

public class CurrencyConversionBean {

	private Long id;
	
	private String from;
	
	private String to;
	
	private BigDecimal conversionRate;
	
	private BigDecimal quatity;
	
	private BigDecimal exchangeAmount;
	
	public CurrencyConversionBean() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(BigDecimal conversionRate) {
		this.conversionRate = conversionRate;
	}

	public BigDecimal getQuatity() {
		return quatity;
	}

	public void setQuatity(BigDecimal quatity) {
		this.quatity = quatity;
	}

	public BigDecimal getExchangeAmount() {
		return exchangeAmount;
	}

	public void setExchangeAmount(BigDecimal exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}
}
