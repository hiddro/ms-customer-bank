package ms.customer.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsCustomerBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCustomerBankApplication.class, args);
	}

}
