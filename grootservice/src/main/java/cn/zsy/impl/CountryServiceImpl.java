package cn.zsy.impl;


import cn.zsy.CountryService;
import cn.zsy.mapper.CountryMapper;
import cn.zsy.model.Country;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * @author spooky
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryMapper countryMapper;

    @Override
    public List<Country> getAll(Country country) {
        if (country.getPage() != null && country.getRows() != null) {
            PageHelper.startPage(country.getPage(), country.getRows());
        }
        Example example = new Example(Country.class);
        Example.Criteria criteria = example.createCriteria();
        if (country.getCountryname() != null && country.getCountryname().length() > 0) {
            criteria.andLike("countryname", "%" + country.getCountryname() + "%");
        }
        if (country.getCountrycode() != null && country.getCountrycode().length() > 0) {
            criteria.andLike("countrycode", "%" + country.getCountrycode() + "%");
        }
        return countryMapper.selectByExample(example);
    }

    @Override
    public Country getById(Integer id) {
        return countryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        countryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(Country country) {
        if (country.getId() != null) {
            countryMapper.updateByPrimaryKey(country);
        } else {
            countryMapper.insert(country);
        }
    }

}
