package com.buct.music.enums;

/**
 * 性别枚举类
 * @author Zhang JunFeng
 * @date 2021/1/2 17:44
 */
public enum SexEnum {
    MALE(1),
    FEMALE(0);

    private final Integer state;

    SexEnum(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return this.state;
    }
}
