package com.buct.music.service.impl;

import com.buct.music.dao.UserMapper;
import com.buct.music.domin.User;
import com.buct.music.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author JeffCar
 * @date 1/3/2021 - 4:48 PM
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    public UserServiceImpl(UserMapper userMapper){
        this.userMapper =userMapper;
    }


    @Override
    public User selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }
}
