package com.telegram.controller;

import com.telegram.entity.City;
import com.telegram.entity.Info;
import com.telegram.service.CityInfoService;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/info")
public class CityInfoController {

    final private CityInfoService cityInfoService;

    public CityInfoController(CityInfoService cityInfoService) {
        this.cityInfoService = cityInfoService;
    }

    @GetMapping
    public List<Info> getAllInfoAboutTheCity(City city) {
        return cityInfoService.findAllInfoForCity(city.getName());
    }

    @GetMapping("all")
    public List<Info> getAllInfo() {
        return cityInfoService.findAll();
    }

    @PostMapping
    public Info getInfoById(@RequestBody Info info) {
        return cityInfoService.findInfoById(info.getId());
    }

    @PostMapping("/add")
    public Info createNewInfo(@RequestBody Info info) {
        return cityInfoService.addInfo(info);
    }

    @DeleteMapping
    public Response deleteInfo(@RequestBody Info info) {
        cityInfoService.deleteInfo(info);
        return Response.status(Response.Status.OK).build();
    }

    @PutMapping
    public Info updateInfo(@RequestBody Info info) {
        return cityInfoService.updateInfo(info);
    }

}
