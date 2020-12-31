package com.buct.music.controller;

import com.buct.music.result.CodeMsg;
import com.buct.music.result.Result;
import com.buct.music.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhang JunFeng
 * @date 2020/12/31 13:47
 */

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login/status")
    public Result<CodeMsg> loginStatus(@RequestParam String username, @RequestParam String password) {
        try {
            boolean flag = adminService.verifyPassword(username, password);
            log.info("参数信息为：username: {}, password: {}", username, password);
            if (flag) {
                return Result.success(0, "login success");
            }
        } catch (Throwable throwable) {
            log.error("参数信息为:username: {}, password: {}", username, password);
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        return Result.error(CodeMsg.LOGIN_FAILURE);
    }
}
