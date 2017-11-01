package cn.zsy;

import cn.zsy.model.City;

import java.util.List;

public interface CityService {

    List<City> getAll(City city);

    City getById(Integer id);

    void deleteById(Integer id);

    void save(City country);
}
