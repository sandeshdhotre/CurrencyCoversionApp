package org.javaindeapth.SpringCloudLimitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
public class LimitController {
	
	@Autowired
	Configuration configure;
	
	@RequestMapping(path = "/limit", method = RequestMethod.GET)
	public LimitData getLimit() {
		return new LimitData(configure.getMin(), configure.getMax());
	}
}
