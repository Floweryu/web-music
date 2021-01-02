package com.buct.music.controller;

import com.buct.music.controller.dto.SingerReq;
import com.buct.music.domin.Singer;
import com.buct.music.enums.SexEnum;
import com.buct.music.result.CodeMsg;
import com.buct.music.result.Result;
import com.buct.music.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 添加一名歌手
     * @param singerReq 歌手参数
     * @return  Result<CodeMsg>
     */
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

    /**
     * 更新歌手信息
     * @param singerReq 前端传递歌手参数
     * @return Result<CodeMsg>
     */
    @PutMapping("/singer")
    public Result<CodeMsg> updateSinger(@RequestBody SingerReq singerReq) {
        try {
            boolean flag = singerService.update(singerReq);
            log.info("修改歌手参数为: {}", singerReq);
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
     * 删除歌手信息
     * @param id    要删除的歌手id
     * @return Result<CodeMsg>
     */
    @DeleteMapping("/singer")
    public Result<CodeMsg> deleteSinger(@RequestParam Long id) {
        if (id <= 0) {
            return Result.error(400, "id should > 0 !");
        }
        try {
            boolean flag = singerService.delete(id);
            log.info("删除歌手id: {}", id);
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
     * 根据主键查询歌手信息
     * @param id    要查询的歌手id
     * @return  Result<Singer>
     */
    @GetMapping("/singer")
    public Result<Singer> getByPrimaryKey(@RequestParam Long id) {
        if (id <= 0) {
            return Result.error(400, "id should > 0 !");
        }
        try {
            Singer singer = singerService.selectByPrimaryKey(id);
            log.info("查询歌手id: {}, 歌手信息：{}", id, singer);
            return Result.success(singer);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 获取所有歌手信息
     * @return  Result<List<Singer>>
     */
    @GetMapping("/singers")
    public Result<List<Singer>> getAllSingers() {
        try {
            List<Singer> singerList = singerService.selectAllSinger();
            log.info("查询歌手: {}", singerList);
            return Result.success(singerList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据姓名获取歌手信息
     * @param name  歌手姓名
     * @return  Result<List<Singer>>
     */
    @GetMapping("/singers/name")
    public Result<List<Singer>> getSingersByName(@RequestParam String name) {
        try {
            List<Singer> singerList = singerService.selectByName(name);
            log.info("根据姓名查询歌手: {}", singerList);
            return Result.success(singerList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    @GetMapping("singers/sex")
    public Result<List<Singer>> getSingerBySex(@RequestParam Integer sex) {
        if (!sex.equals(SexEnum.MALE.getState()) && !sex.equals(SexEnum.FEMALE.getState())) {
            return Result.error(400, "sex must be 1(male) or 0(female)!");
        }
        try {
            List<Singer> singerList = singerService.selectBySex(sex);
            log.info("根据姓名查询歌手: {}", singerList);
            return Result.success(singerList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
