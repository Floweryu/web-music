package com.buct.music.dao;

import com.buct.music.domin.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author JeffCar
 * @date 1/3/2021 - 3:39 PM
 */
@Mapper
@Repository
public interface UserMapper {

    //添加用户
    int insert(User user);

    //删除用户
    int delete(User user);

    //修改用户
    int update(User user);

    //根据主键查询用户
    User selectUserById(Long id);
}
