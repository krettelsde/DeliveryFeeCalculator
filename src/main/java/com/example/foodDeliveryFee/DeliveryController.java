package com.example.foodDeliveryFee;

import com.example.foodDeliveryFee.data.WeatherData;
import com.example.foodDeliveryFee.data.WeatherDataService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    WeatherDataService weatherDataService;

    @GetMapping("/fee")
    public Map<String, Double> calculateDeliveryFee(@RequestParam String city,@RequestParam String vehicleType)  {

        // fetch the latest weather data for the specified city from the database

        WeatherData weatherData = weatherDataService.getLatestWeatherDataForStation(city);
        DeliveryService deliveryService = new DeliveryService();
//
//        // calculate the regional base fee based on the city and vehicle type
        double regionalBaseFee = deliveryService.calculateRegionalBaseFee(city, vehicleType);
//
//        // calculate the extra fees for weather conditions based on the latest weather data and vehicle type
        double extraFees = deliveryService.calculateExtraFees(weatherData, vehicleType);
//
//        // calculate the total delivery fee
        double totalFee = regionalBaseFee + extraFees;

        Map<String, Double> response = new HashMap<>();
        response.put("fee", totalFee);
//
        return response;
    }




}
