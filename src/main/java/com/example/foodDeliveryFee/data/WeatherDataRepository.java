package com.example.foodDeliveryFee.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    WeatherData findTopByStationNameOrderByTimestampDesc(String stationName);
}
