package com.buct.music.service.impl;

import com.buct.music.controller.dto.SongReq;
import com.buct.music.domin.Song;
import com.buct.music.service.SongService;
import com.buct.music.utils.SongServiceUtil;
import org.springframework.stereotype.Service;
import com.buct.music.dao.SongMapper;

import java.util.List;

/**
 * @author Jeffrey
 * @version 1.0 2021/01/04 11:03
 */

@Service
public class SongServiceImpl implements SongService {
    private final SongMapper songMapper;

    // 构造函数
    public SongServiceImpl(SongMapper songMapper) {
        this.songMapper = songMapper;
    }

    @Override
    public boolean insert(SongReq songReq) {
        Song song = SongServiceUtil.songUtil(songReq);
        long timeNow = System.currentTimeMillis();
        song.setCreateTime(timeNow);
        song.setUpdateTime(timeNow);
        return songMapper.insert(song) > 0;
    }

    @Override
    public boolean update(SongReq songReq) {
        Song song = SongServiceUtil.songUtil(songReq);
        long timeNow = System.currentTimeMillis();
        song.setUpdateTime(timeNow);
        return songMapper.update(song) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return songMapper.delete(id) > 0;
    }

    @Override
    public Song selectByPrimaryKey(Long id) {
        return songMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Song> selectAllSongs() {
        return songMapper.selectAllSongs();
    }

    @Override
    public List<Song> selectByName(String name) {
        String likeName = name.trim();
        return songMapper.selectByName("%" + likeName + "%");
    }

    @Override
    public List<Song> selectBySinger(String name) {
        String likeName = name.trim();
        return songMapper.selectBySinger("%" + likeName + "%");
    }
}
