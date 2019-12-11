package com.telegram.controller;

import com.telegram.entity.City;
import com.telegram.service.CityService;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/all")
    public List<City> getAllCity() {
        return cityService.findAll();
    }

    @PostMapping
    public City getCityByName(@RequestBody City city) {
        return cityService.findCityByName(city.getName());
    }

    @PostMapping("/add")
    public City createNewCity(@RequestBody City city) {
        return cityService.addNewCity(city);
    }

    @DeleteMapping
    public Response deleteCity(@RequestBody City city) {
        cityService.deleteCity(city);
        return Response.status(Response.Status.OK).build();
    }

    @PutMapping
    public City updateCity(@RequestBody City city) {
        return cityService.updateCity(city);
    }

}
