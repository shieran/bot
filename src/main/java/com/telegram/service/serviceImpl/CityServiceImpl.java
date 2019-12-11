package com.telegram.service.serviceImpl;

import com.telegram.entity.City;
import com.telegram.repository.CityRepository;
import com.telegram.service.CityService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City findCityByName(String cityName) {
        City cityFromBd = cityRepository.findCityByName(cityName);
        if (cityFromBd == null) {
            throw new EntityNotFoundException(cityName + " not found");
        }
        return cityFromBd;
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public void deleteCity(City city) {
        cityRepository.delete(city);
    }

    @Override
    public City updateCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City addNewCity(City city) {
        City cityFromDb = cityRepository.findCityByName(city.getName());
        if (cityFromDb != null) {
            throw new EntityExistsException(city.getName() + " already exist");
        }
        return cityRepository.save(city);
    }
}
