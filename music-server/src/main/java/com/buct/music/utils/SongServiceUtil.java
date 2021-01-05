package com.buct.music.utils;

import com.buct.music.controller.dto.SongReq;
import com.buct.music.domin.Song;

public class SongServiceUtil {
    public static Song songUtil(SongReq songReq) {
        Long id = songReq.getId();
        String name = songReq.getName();
        String singerName = songReq.getSingerName();
        String pic = songReq.getPic();
        String url = songReq.getUrl();
        String introduction = songReq.getIntroduction();

        Song song = new Song();
        if (id != null) {
            song.setId(id);
        }
        if (singerName != null) {
            // 先要根据歌手姓名调用歌手id;
            Long singerId = Long.valueOf(255);  // 临时代码，暂时补充业务层逻辑
            song.setSingerId(singerId);
        }
        if (name != null) {
            song.setName(name.trim());
        }
        if (pic != null) {
            song.setPic(pic.trim());
        }
        if (url != null) {
            song.setUrl(url.trim());
        }
        if (introduction != null) {
            song.setIntroduction(introduction.trim());
        }
        return song;
    }
}
