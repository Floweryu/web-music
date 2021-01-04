package com.buct.music.enums;

/**
 * 文件路径枚举类
 * @author Zhang JunFeng
 * @date 2021/1/3 12:05
 */
public enum FilePathEnum {
    IMG_PATH("img"),
    SINGER_PIC("singerPic"),
    SINGER_PIC_PATH(IMG_PATH, SINGER_PIC);

    private final String path;

    FilePathEnum(String path) {
        this.path = path;
    }

    FilePathEnum(FilePathEnum firstPath, FilePathEnum secondPath) {
        this.path = "/" + firstPath.getPath() + "/" + secondPath.getPath() + "/";
    }

    public String getPath() {
        return this.path;
    }
}
