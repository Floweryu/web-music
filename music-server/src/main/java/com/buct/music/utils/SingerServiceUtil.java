package com.buct.music.utils;

import com.buct.music.controller.dto.SingerReq;
import com.buct.music.domin.Singer;

import java.util.Date;

/**
 * @author Zhang JunFeng
 * @date 2021/1/2 16:04
 */
public class SingerServiceUtil {
    public static Singer singerUtil(SingerReq singerReq) {
        Long id = singerReq.getId();


        
        Date birth = singerReq.getBirth();
        String location = singerReq.getLocation().trim();
        String introduction = singerReq.getIntroduction().trim();

        Singer singer = new Singer();
        if (id != null) {
            singer.setId(id);
        }
        singer.setName(name);
        singer.setSex(sex);
        singer.setPic(pic);
        singer.setBirth(birth);
        singer.setLocation(location);
        singer.setIntroduction(introduction);

        long timeNow = System.currentTimeMillis();
        singer.setCreateTime(timeNow);
        singer.setUpdateTime(timeNow);
        return singer;
    }
}
