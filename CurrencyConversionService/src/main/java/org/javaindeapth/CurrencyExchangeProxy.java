package org.javaindeapth;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(name = "currencyExchangeService", url = "http://localhost:9000")//Original Feign Client without ribbon or Zuul
//@FeignClient(name = "currency-exchange-service")	//This was needed for Zuul as we were calling currency exchange service using Eureka however the value changed to zuul server
@FeignClient(name = "netflix-zuul-api-gatway-server") 	//This is to inform Feign Client to call Zuul and ask zuul to call service
//@RibbonClient(name= "currency-exchange-service")	//This is used for client side load balancing using 
public interface CurrencyExchangeProxy {
	
	@RequestMapping(path = "/currency-exchange-service/currency-exchange/from/{from}/to/{to}") //This is a different url for Zuul client we 
	//URL = https://zuulserver:zuulport/{application_name}/url_configured_in_controller
	//@RequestMapping(path = "/currency-exchange/from/{from}/to/{to}") //original url directly contact the ConversionService by Ribbon Client it gets list of server instance using Eureka
	public CurrencyConversionBean getExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
