package cn.skio.car_lease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CarLeaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarLeaseApplication.class, args);
	}
}
