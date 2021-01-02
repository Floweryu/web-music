package com.buct.music.service.impl;

import com.buct.music.controller.dto.SingerReq;
import com.buct.music.dao.SingerMapper;
import com.buct.music.domin.Singer;
import com.buct.music.service.SingerService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Zhang JunFeng
 * @date 2021/1/2 10:41
 */
@Service
public class SingerServiceImpl implements SingerService {

    private final SingerMapper singerMapper;

    public SingerServiceImpl(SingerMapper singerMapper) {
        this.singerMapper = singerMapper;
    }

    // 增加歌手
    @Override
    public boolean insert(SingerReq singerReq) {
        String name = singerReq.getName().trim();
        Boolean sex = singerReq.getSex();
        String pic = singerReq.getPic().trim();
        Date birth = singerReq.getBirth();
        String location = singerReq.getLocation().trim();
        String introduction = singerReq.getIntroduction().trim();

        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(sex);
        singer.setPic(pic);
        singer.setBirth(birth);
        singer.setLocation(location);
        singer.setIntroduction(introduction);

        long timeNow = System.currentTimeMillis();
        singer.setCreateTime(timeNow);
        singer.setUpdateTime(timeNow);

        return singerMapper.insert(singer) > 0;
    }

    // 修改歌手
    @Override
    public boolean update(Singer singer) {
        return singerMapper.update(singer) > 0;
    }

    // 删除歌手
    @Override
    public boolean delete(Long id) {
        return singerMapper.delete(id) > 0;
    }

    // 根据主键查询整个对象
    @Override
    public Singer selectByPrimaryKey(Long id) {
        return singerMapper.selectByPrimaryKey(id);
    }

    // 查询所有歌手
    @Override
    public List<Singer> selectAllSinger() {
        return singerMapper.selectAllSinger();
    }

    // 根据歌手名字模糊查询
    @Override
    public List<Singer> selectByName(String name) {
        return singerMapper.selectByName(name);
    }

    // 根据男女查询
    @Override
    public List<Singer> selectBySex(Boolean sex) {
        return singerMapper.selectBySex(sex);
    }
}
