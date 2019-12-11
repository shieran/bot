package com.telegram.repository;

import com.telegram.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityInfoRepository extends JpaRepository<Info, Long> {
    List<Info> findAllByCityId(Long id);
    Info findInfoById(Long id);
}
