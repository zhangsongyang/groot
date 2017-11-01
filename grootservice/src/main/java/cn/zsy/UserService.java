package cn.zsy;

import cn.zsy.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll(User user);

    User getById(Integer id);

    void deleteById(Integer id);

    void save(User user);

}
