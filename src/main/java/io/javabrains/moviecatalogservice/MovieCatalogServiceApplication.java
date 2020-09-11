package io.javabrains.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class MovieCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		//tropos gia timeouts sta services
		/*HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(3000);*/
		return new RestTemplate();
	}
}
/* Circuit braker
Me th swsta paraemtropoihsh ousiastika elegxei ta time outs (afou exoume orisei ton xrono)
kai ta errors sta teleutaia N requests poy exoun ginei pros ta web services. Meta apo enan
sugkekrimeno arithmo diakoptei th sundesh me to "argo" service mexri na epanelthei. To pote
tha thewrisei o mhxanismos oti to service exei epanelthei parametropoieitai.
 */

/* Fallbacks
Mhxanismos pou lamvanei xwra afou exei energopoihthei o Circuit braker gia kapoio service.
Douleia tou einai na stelnei to katallhlo responce ston user pou exei zhthsei to service to opoio
proswrina exei apokopei.
Periptwseis: throw an error, return a fallback default response, return previous saved responses
 */