package com.buct.music.utils;

import com.buct.music.controller.dto.SongReq;
import com.buct.music.domin.Song;

import java.util.Date;

public class SongServiceUtil {
    public static Song songUtil(SongReq songReq) {
        Long id = songReq.getId();
        Long singerId = songReq.getSingerId();
        String name = songReq.getName();
        String pic = songReq.getPic();
        String url = songReq.getUrl();
        String introduction = songReq.getIntroduction();

        Song song = new Song();
        if (id != null) {
            song.setId(id);
        }
        if (singerId != null) {
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
