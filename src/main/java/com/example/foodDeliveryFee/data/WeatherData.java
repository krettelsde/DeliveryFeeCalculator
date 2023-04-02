package com.example.foodDeliveryFee.data;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "weather_data")
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "station_name")
    private String stationName;

    @Column(name = "wmo_code")
    private String wmoCode;

    @Column(name = "air_temperature")
    private Double airTemperature;

    @Column(name = "wind_speed")
    private Double windSpeed;

    @Column(name = "weather_phenomenon")
    private String weatherPhenomenon;

    @Column(name = "timestamp")
    private String timestamp;

    public WeatherData(String stationName, String wmoCode, Double airTemperature, Double windSpeed, String weatherPhenomenon, String timestamp) {
        this.stationName = stationName;
        this.wmoCode = wmoCode;
        this.airTemperature = airTemperature;
        this.windSpeed = windSpeed;
        this.weatherPhenomenon = weatherPhenomenon;
        this.timestamp = timestamp;
    }

    public WeatherData() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getWmoCode() {
        return wmoCode;
    }

    public void setWmoCode(String wmoCode) {
        this.wmoCode = wmoCode;
    }

    public Double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(Double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWeatherPhenomenon() {
        return weatherPhenomenon;
    }

    public void setWeatherPhenomenon(String weatherPhenomenon) {
        this.weatherPhenomenon = weatherPhenomenon;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "id=" + id +
                ", stationName='" + stationName + '\'' +
                ", wmoCode='" + wmoCode + '\'' +
                ", airTemperature=" + airTemperature +
                ", windSpeed=" + windSpeed +
                ", weatherPhenomenon='" + weatherPhenomenon + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    // Getters and setters
}
