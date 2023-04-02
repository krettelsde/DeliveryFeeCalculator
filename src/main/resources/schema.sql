CREATE TABLE IF NOT EXISTS weather_data (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    station_name VARCHAR(255),
    wmo_code VARCHAR(255),
    air_temperature DOUBLE,
    wind_speed DOUBLE,
    weather_phenomenon VARCHAR(255),
    timestamp VARCHAR(255)
);

