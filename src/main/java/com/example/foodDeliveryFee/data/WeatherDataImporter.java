package com.example.foodDeliveryFee.data;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;

import java.io.StringReader;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
@Component
public class WeatherDataImporter {

    @Autowired
    WeatherDataService weatherDataService;

    private static final String TALLINN_HARKU_STATION_CODE = "26038";
    private static final String TARTU_TORAVARE_STATION_CODE = "26242";
    private static final String PARNU_STATION_CODE = "41803";
    private static final String WEATHER_DATA_URL = "https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php";


    @PostConstruct
    public void init() throws SQLException, IOException, ParserConfigurationException, SAXException {
        importWeatherData();
    }

    @Scheduled(cron = "0 15 * * * *")
    public void importWeatherData() throws IOException, SAXException, ParserConfigurationException, SQLException {
        // Make HTTP request to weather portal and parse XML response
        // Insert weather data into weather data table using JDBC

        RestTemplate restTemplate = new RestTemplate();
        String xmlString = restTemplate.getForObject(WEATHER_DATA_URL, String.class);

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("observations");
            Node node = nodeList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element observation = (Element) node;
                String timestamp = observation.getAttribute("timestamp");
                NodeList stations = observation.getElementsByTagName("station");
                for (int j = 0; j < stations.getLength(); j++) {
                    Element station = (Element) stations.item(j);
                    String wmo = station.getElementsByTagName("wmocode").item(0).getTextContent();
                    if (wmo.equals(TALLINN_HARKU_STATION_CODE) || wmo.equals(TARTU_TORAVARE_STATION_CODE) || wmo.equals(PARNU_STATION_CODE)) {
                        String name = station.getElementsByTagName("name").item(0).getTextContent();
                        Double windspeed = Double.valueOf(station.getElementsByTagName("windspeed").item(0).getTextContent());
                        Double airtemperature = Double.valueOf(station.getElementsByTagName("airtemperature").item(0).getTextContent());
                        String phenomenon = station.getElementsByTagName("phenomenon").item(0).getTextContent();

                        WeatherData weatherData = new WeatherData(name, wmo, airtemperature, windspeed, phenomenon, timestamp);
                        weatherDataService.saveWeatherData(weatherData);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
