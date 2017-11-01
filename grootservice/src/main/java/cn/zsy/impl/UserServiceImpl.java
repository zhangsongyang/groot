package cn.zsy.impl;

import cn.zsy.UserService;
import cn.zsy.mapper.UserMapper;
import cn.zsy.model.User;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getAll(User user) {
        if (user.getPage() != null && user.getRows() != null) {
            PageHelper.startPage(user.getPage(), user.getRows());
        }
        return userMapper.selectAll();
    }

    @Override
    public User getById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(User user) {
        if (user.getId() != null) {
            userMapper.updateByPrimaryKey(user);
        } else {
            userMapper.insert(user);
        }
    }

}
