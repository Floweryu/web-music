package com.buct.music.controller;

import com.buct.music.controller.dto.SingerReq;
import com.buct.music.result.CodeMsg;
import com.buct.music.result.Result;
import com.buct.music.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhang JunFeng
 * @date 2021/1/2 10:50
 */
@RestController
@Slf4j
@RequestMapping("/admin")
public class SingerController {
    private final SingerService singerService;

    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }

    @PostMapping("/singer")
    public Result<CodeMsg> addSinger(@RequestBody SingerReq singerReq) {
        try {
            boolean flag = singerService.insert(singerReq);
            log.info("添加歌手参数为: {}", singerReq);
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
}
