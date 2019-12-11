package com.telegram.service;

import com.telegram.entity.City;

import java.util.List;

public interface CityService {
    City findCityByName(String cityName);

    List<City> findAll();

    void deleteCity(City city);

    City updateCity(City city);

    City addNewCity(City city);
}
