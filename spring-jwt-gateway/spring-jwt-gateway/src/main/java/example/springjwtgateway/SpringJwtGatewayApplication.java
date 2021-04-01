package example.springjwtgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJwtGatewayApplication {

	//jwt verification, dynamic routing
	public static void main(String[] args) {
		SpringApplication.run(SpringJwtGatewayApplication.class, args);
	}

}
