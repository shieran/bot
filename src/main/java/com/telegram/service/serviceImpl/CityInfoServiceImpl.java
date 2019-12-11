package com.telegram.service.serviceImpl;

import com.telegram.entity.Info;
import com.telegram.repository.CityInfoRepository;
import com.telegram.service.CityInfoService;
import com.telegram.service.CityService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CityInfoServiceImpl implements CityInfoService {

    private final CityInfoRepository cityInfoRepository;
    private final CityService cityService;

    public CityInfoServiceImpl(CityInfoRepository cityInfoRepository, CityService cityService) {
        this.cityInfoRepository = cityInfoRepository;
        this.cityService = cityService;
    }

    @Override
    public List<Info> findAllInfoForCity(String cityName) {
        Long id = cityService.findCityByName(cityName).getId();
        return cityInfoRepository.findAllByCityId(id);
    }

    @Override
    public void deleteInfo(Info info) {
        cityInfoRepository.delete(info);
    }

    @Override
    public Info addInfo(Info info) {
        return cityInfoRepository.save(info);
    }

    @Override
    public Info updateInfo(Info info) {
        return cityInfoRepository.save(info);
    }

    @Override
    public Info findInfoById(Long id) {
        Info infoFromDb = cityInfoRepository.findInfoById(id);
        if (infoFromDb == null) {
            throw new EntityNotFoundException("info not found");
        }
        return infoFromDb;
    }

    @Override
    public List<Info> findAll() {
        return cityInfoRepository.findAll();
    }
}
