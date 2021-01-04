package com.buct.music.controller;

import com.buct.music.controller.dto.UserReq;
import com.buct.music.domin.Singer;
import com.buct.music.domin.User;
import com.buct.music.result.CodeMsg;
import com.buct.music.result.Result;
import com.buct.music.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 通过主键id查找用户
     * @param id
     * @return
     */
    @GetMapping("/user")
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

    /**
     * 添加用户
     * @param req
     * @return
     */
    @PostMapping("/user")
    public Result<CodeMsg> addUser(@RequestBody UserReq req){
        try {
            boolean flag = userService.insert(req);
            log.info("添加用户参数为: {}", req);
            if (flag) {
                return Result.success(CodeMsg.SUCCESS);
            } else {
                return Result.error(CodeMsg.FAILURE);
            }
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改用户
     * @param req
     * @return
     */
    @PutMapping("/user")
    public Result<CodeMsg> updateUser(@RequestBody UserReq req){
        try {
            boolean flag = userService.update(req);
            log.info("修改用户参数为: {}", req);
            if (flag) {
                return Result.success(CodeMsg.SUCCESS);
            } else {
                return Result.error(CodeMsg.FAILURE);
            }
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/user")
    public Result<CodeMsg> deleteUser(@RequestParam Long id){
        if (id <= 0) {
            return Result.error(400, "id should > 0 !");
        }
        try {
            boolean flag = userService.delete(id);
            log.info("删除用户id: {}", id);
            if (flag) {
                return Result.success(CodeMsg.SUCCESS);
            }else {
                return Result.error(CodeMsg.FAILURE);
            }
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 查找所有用户
     * @return
     */
    @GetMapping("/users")
    public Result<List<User>> selectAllUsers(){
        try {
            List<User> users = userService.selectAllUsers();
            log.info("查询用户: {}", users);
            return Result.success(users);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 通过姓名查找用户
     * @param username
     * @return
     */
    @GetMapping("/users/username")
    public Result<List<User>> selectUserByName(@RequestParam String username){
        try {
            List<User> users = userService.selectUserByName(username);
            log.info("根据姓名查询用户: {}", users);
            return Result.success(users);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }



}
