package cn.zsy.controller;

import cn.zsy.CountryService;
import cn.zsy.model.Country;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author spooky
 */
@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping
    public PageInfo<Country> getAll(Country country) {
        List<Country> countryList = countryService.getAll(country);
        return new PageInfo<Country>(countryList);
    }

    @RequestMapping(value = "/view/{id}")
    public Country view(@PathVariable Integer id) {
        Country country = countryService.getById(id);
        return country;
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelMap delete(@PathVariable Integer id) {
        ModelMap result = new ModelMap();
        countryService.deleteById(id);
        result.put("msg", "删除成功!");
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelMap save(Country country) {
        ModelMap result = new ModelMap();
        String msg = country.getId() == null ? "新增成功!" : "更新成功!";
        countryService.save(country);
        result.put("country", country);
        result.put("msg", msg);
        return result;
    }
}
