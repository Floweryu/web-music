package com.buct.music.service;

import com.buct.music.domin.User;

/**
 * @author JeffCar
 * @date 1/3/2021 - 4:47 PM
 */
public interface UserService {

    //根据id查找用户
    User selectUserById(Long id);
}
