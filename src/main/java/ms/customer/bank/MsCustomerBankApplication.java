package ms.customer.bank;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@OpenAPIDefinition(info = @Info(
		title = "Ms Customer Service",
		version = "1.0",
		description = "documentacion de servicios para Customers"
))
public class MsCustomerBankApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MsCustomerBankApplication.class, args);
	}

}
