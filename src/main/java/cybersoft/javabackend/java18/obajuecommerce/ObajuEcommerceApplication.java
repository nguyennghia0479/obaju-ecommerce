package cybersoft.javabackend.java18.obajuecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ObajuEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObajuEcommerceApplication.class, args);
	}

}
