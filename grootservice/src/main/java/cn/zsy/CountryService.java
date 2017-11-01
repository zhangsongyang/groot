package cn.zsy;


import cn.zsy.model.Country;

import java.util.List;

public interface CountryService {

    List<Country> getAll(Country country);

    Country getById(Integer id);

    void deleteById(Integer id);

    void save(Country country);

}
