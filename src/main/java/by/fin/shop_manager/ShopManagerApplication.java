package by.fin.shop_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories
@EnableTransactionManagement
@EnableFeignClients
@SpringBootApplication
public class ShopManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopManagerApplication.class, args);
	}

}
