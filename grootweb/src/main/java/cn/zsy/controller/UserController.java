package cn.zsy.controller;

import cn.zsy.UserService;
import cn.zsy.model.User;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "用户信息", description = "用户信息")
@RestController
@RequestMapping("/arch")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查看所有用户", notes = "查看所有用户信息")
    @RequestMapping(method = RequestMethod.POST)
    public PageInfo<User> getAll(User user) {
        List<User> userList = userService.getAll(user);
        return new PageInfo<User>(userList);
    }

    @ApiOperation(value = "查看一个用户", notes = "查看一个用户信息", response = User.class)
    @RequestMapping(value = "/view/{id}", method = RequestMethod.POST)
    public User view(@PathVariable Integer id) {
        User user = userService.getById(id);
        return user;
    }

    @ApiOperation(value = "删除用户", notes = "删除用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ModelMap delete(@PathVariable Integer id) {
        ModelMap result = new ModelMap();
        userService.deleteById(id);
        result.put("msg", "删除成功!");
        return result;
    }

    @ApiOperation(value = "保存或更新用户", notes = "保存或更新用户信息")
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
