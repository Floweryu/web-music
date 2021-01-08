package com.buct.music.controller;

import com.buct.music.controller.dto.SongReq;
import com.buct.music.domin.Song;
import com.buct.music.result.CodeMsg;
import com.buct.music.result.Result;
import com.buct.music.service.SongService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jeffrey
 * @version 2020/01/04 10:21
 */

@RestController
@Slf4j
@RequestMapping("/admin")
public class SongController {
    private final SongService songService;

    // 构造函数
    public SongController(SongService songService) {
        this.songService = songService;
    }

    /**
     * 添加一首歌
     * @param songReq 前端传递歌曲参数
     * @return Result<CodeMsg>
     */
    @PostMapping("/songs")
    public Result<CodeMsg> addSong(@RequestBody SongReq songReq) {
        try {
            boolean flag = songService.insert(songReq);
            log.info("添加歌曲参数为{}", songReq);
            if (flag)
                return Result.success(CodeMsg.SUCCESS);
            else
                return Result.error(CodeMsg.FAILURE);
        } catch (Throwable throwable) {
            log.error("There is som+ething error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 修改特定歌曲的信息
     * @param songReq 前端传来的歌曲参数
     * @return Result<CodeMsg>
     */
    @PutMapping("/songs")
    public Result<CodeMsg> updateSong(@RequestBody SongReq songReq) {
        try {
            boolean flag = songService.update(songReq);
            log.info("修改歌曲参数为{}", songReq);
            if (flag)
                return Result.success(CodeMsg.SUCCESS);
            else
                return Result.error(CodeMsg.FAILURE);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id删除歌曲
     * @param id 前端传入的歌曲id
     * @return Result<Msg>
     */
    @DeleteMapping("/songs")
    public Result<CodeMsg> deleteSong(@RequestParam Long id) {
        if (id <= 0) {
            return Result.error(400, "id should > 0 !");
        }
        try {
            boolean flag = songService.delete(id);
            log.info("删除id为{}的歌曲", id);
            if (flag)
                return Result.success(CodeMsg.SUCCESS);
            else
                return Result.error(CodeMsg.FAILURE);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据id查找歌曲
     * @param id 歌曲id
     * @return Result<Song>
     */
    @GetMapping("/song")
    public Result<Song> getByPrimaryKey(@RequestParam Long id) {
        if (id <= 0) {
            return Result.error(400, "id should > 0 !");
        }
        try {
            Song song = songService.selectByPrimaryKey(id);
            log.info("歌曲id: {}, 歌曲信息: {}", id, song);
            return Result.success(song);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 查找所有歌曲
     * @return Result<List<Song>>
     */
    @GetMapping("/songs")
    public Result<List<Song>> getAllSongs() {
        try {
            List<Song> songList = songService.selectAllSongs();
            log.info("所有歌曲: {}", songList);
            return Result.success(songList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据歌曲名字查找歌曲
     * @param name 前端传入的歌曲名字
     * @return Result<List<Song>>
     */
    @GetMapping("/songs/name")
    public Result<List<Song>> getSongByName(@RequestParam String name) {
        try {
            List<Song> songList = songService.selectByName(name);
            log.info("根据名字查找歌曲: {}", songList);
            return Result.success(songList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 根据歌手名字查找对应歌曲
     * @param name 前端传入的歌曲名字
     * @return Result<List<Song>>
     */
    @GetMapping("/songs/singer_name")
    public Result<List<Song>> getSongBySinger(@RequestParam String name) {
        try {
            List<Song> songList = songService.selectBySinger(name);
            log.info("根据歌手名字查找歌曲: {}", songList);
            return Result.success(songList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }

    /**
     * 查找包含歌手名的所有歌曲
     * @return Result<List<SongReq>>
     */
    @GetMapping("/songs/songs_with_singer_name")
    public Result<List<SongReq>> getAllSongsWithSinger() {
        try {
            List<SongReq> songList = songService.getAllSongsWithSinger();
            log.info("所有歌曲: {}", songList);
            return Result.success(songList);
        } catch (Throwable throwable) {
            log.error("There is something error: {}", throwable.getMessage());
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
