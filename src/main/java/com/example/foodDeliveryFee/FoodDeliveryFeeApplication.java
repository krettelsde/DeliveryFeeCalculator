package com.example.foodDeliveryFee;

import com.example.foodDeliveryFee.data.WeatherDataImporter;
import com.example.foodDeliveryFee.data.WeatherDataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.xml.sax.SAXException;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.security.Provider;
import java.sql.SQLException;
@EnableScheduling
@SpringBootApplication
public class FoodDeliveryFeeApplication {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, SQLException {
		SpringApplication.run(FoodDeliveryFeeApplication.class, args);
	}

}
