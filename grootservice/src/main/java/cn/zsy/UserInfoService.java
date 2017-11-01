package cn.zsy;


import cn.zsy.model.UserInfo;

import java.util.List;

public interface UserInfoService {

    List<UserInfo> getAll(UserInfo UserInfo);

    UserInfo getById(Integer id);

    void deleteById(Integer id);

    void save(UserInfo country);
}
