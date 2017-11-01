package cn.zsy.controller;

import cn.zsy.UserService;
import cn.zsy.model.User;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/arch")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public PageInfo<User> getAll(User user) {
        List<User> userList = userService.getAll(user);
        return new PageInfo<User>(userList);
    }

    @RequestMapping(value = "/view/{id}")
    public User view(@PathVariable Integer id) {
        User user = userService.getById(id);
        return user;
    }


    @RequestMapping(value = "/delete/{id}")
    public ModelMap delete(@PathVariable Integer id) {
        ModelMap result = new ModelMap();
        userService.deleteById(id);
        result.put("msg", "删除成功!");
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelMap save(@RequestBody User user) {
        ModelMap result = new ModelMap();
        String msg = user.getId() == null ? "新增成功!" : "更新成功!";
        userService.save(user);
        result.put("user", user);
        result.put("msg", msg);
        return result;
    }


}
