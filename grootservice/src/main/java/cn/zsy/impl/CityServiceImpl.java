package cn.zsy.impl;


import cn.zsy.CityService;
import cn.zsy.mapper.CityMapper;
import cn.zsy.model.City;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author spooky
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<City> getAll(City city) {
        if (city.getPage() != null && city.getRows() != null) {
            PageHelper.startPage(city.getPage(), city.getRows());
        }
        return cityMapper.selectAll();
    }

    @Override
    public City getById(Integer id) {
        return cityMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        cityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(City country) {
        if (country.getId() != null) {
            cityMapper.updateByPrimaryKey(country);
        } else {
            cityMapper.insert(country);
        }
    }

}
