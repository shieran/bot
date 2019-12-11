package com.telegram.service;

import com.telegram.entity.Info;

import java.util.List;

public interface CityInfoService {
    List<Info> findAllInfoForCity(String cityName);

    void deleteInfo(Info info);

    Info addInfo(Info info);

    Info updateInfo(Info info);

    Info findInfoById(Long id);

    List<Info> findAll();
}
