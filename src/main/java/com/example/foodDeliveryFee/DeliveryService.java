package com.example.foodDeliveryFee;

import com.example.foodDeliveryFee.data.WeatherData;

public class DeliveryService {

    public DeliveryService() {
    }

    public double calculateRegionalBaseFee(String city, String vehicleType) {

        // apply the business rules to calculate the regional base fee
        // based on the city and vehicle type
        // return the calculated regional base fee
        switch (city) {
            case "Tallinn-Harku":
                switch (vehicleType) {
                    case "Car":
                        return 4.0;
                    case "Scooter":
                        return 3.5;
                    case "Bike":
                        return 3.0;
                    default:
                        throw new IllegalArgumentException("Invalid vehicle type");
                }
            case "Tartu-Tõravere":
                switch (vehicleType) {
                    case "Car":
                        return 3.5;
                    case "Scooter":
                        return 3.0;
                    case "Bike":
                        return 2.5;
                    default:
                        throw new IllegalArgumentException("Invalid vehicle type");
                }
            case "Pärnu":
                switch (vehicleType) {
                    case "Car":
                        return 3.0;
                    case "Scooter":
                        return 2.5;
                    case "Bike":
                        return 2.0;
                    default:
                        throw new IllegalArgumentException("Invalid vehicle type");
                }
            default:
                throw new IllegalArgumentException("Invalid city");
        }
    }

    public double calculateExtraFees(WeatherData weatherData, String vehicleType) {
        // apply the business rules to calculate the extra fees for weather conditions
        // based on the latest weather data and vehicle type
        // return the calculated extra fees
        double extraFee = 0;

        // Extra fee based on air temperature
        double airTemperature = weatherData.getAirTemperature();
        if ((vehicleType.equals("Scooter") || vehicleType.equals("Bike")) && airTemperature < -10) {
            extraFee += 1;
        } else if ((vehicleType.equals("Scooter") || vehicleType.equals("Bike")) && airTemperature >= -10 && airTemperature <= 0) {
            extraFee += 0.5;
        }

        // Extra fee based on wind speed
        double windSpeed = weatherData.getWindSpeed();
        if (vehicleType.equals("Bike") && windSpeed >= 10 && windSpeed <= 20) {
            extraFee += 0.5;
        } else if (vehicleType.equals("Bike") && windSpeed > 20) {
            throw new RuntimeException("Usage of selected vehicle type is forbidden");
        }

        // Extra fee based on weather phenomenon
        String weatherPhenomenon = weatherData.getWeatherPhenomenon();
        if (vehicleType.equals("Scooter") || vehicleType.equals("Bike")) {
            if(weatherPhenomenon.toLowerCase().contains("snow") || weatherPhenomenon.toLowerCase().contains("sleet"))
                extraFee += 1;
            else if(weatherPhenomenon.toLowerCase().contains("rain"))
                extraFee+=0.5;
            else if (weatherPhenomenon.toLowerCase().contains("glaze") || weatherPhenomenon.toLowerCase().contains("hail") || weatherPhenomenon.toLowerCase().contains("thunder"))
                throw new RuntimeException("Usage of selected vehicle type is forbidden");
        }

        return extraFee;
    }
}
