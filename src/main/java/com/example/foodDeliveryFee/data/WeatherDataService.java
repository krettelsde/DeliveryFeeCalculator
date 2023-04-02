package com.example.foodDeliveryFee.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherDataService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void addWeather(WeatherData weatherData) {
        entityManager.persist(weatherData);
    }

    public List<WeatherData> getAllWeatherData() {
        return weatherDataRepository.findAll();
    }

    public WeatherData getLatestWeatherDataForStation(String stationName) {
        WeatherData returnWeatherData= null;
        for (WeatherData allWeatherDatum : getAllWeatherData()) {
            if (allWeatherDatum.getStationName().equals(stationName)) {
                if (returnWeatherData == null) {
                    returnWeatherData = allWeatherDatum;
                } else if (Integer.valueOf(allWeatherDatum.getTimestamp()) > Integer.valueOf(returnWeatherData.getTimestamp())) {
                    returnWeatherData = allWeatherDatum;
                }
            }
        }
        return returnWeatherData;
    }

    public WeatherData saveWeatherData(WeatherData weatherData) {
        return weatherDataRepository.save(weatherData);
    }

    public void deleteWeatherData(Long id) {
        weatherDataRepository.deleteById(id);
    }
}
