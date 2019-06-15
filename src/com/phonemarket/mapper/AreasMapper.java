package com.phonemarket.mapper;

import com.phonemarket.entity.Areas;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreasMapper {
    List<Areas> findAreasByCityId(String cityId);

    Areas findAreaByAreaName(String name, String cityId);

    Areas findAreaById(String id);
}
