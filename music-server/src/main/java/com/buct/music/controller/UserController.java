package com.buct.music.controller;

import com.buct.music.domin.Singer;
import com.buct.music.domin.User;
import com.buct.music.result.CodeMsg;
import com.buct.music.result.Result;
import com.buct.music.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JeffCar
 * @date 1/3/2021 - 4:36 PM
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/users")
    public Result<User> getByPrimaryKey(@RequestParam Long id){
        if (id <= 0) {
            return Result.error(400, "id should > 0 !");
        }
        try {
            User user = userService.selectUserById(id);
            log.info("查询用户id: {}, 用户信息：{}", id, user);
            return Result.success(user);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
