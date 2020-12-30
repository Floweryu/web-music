package com.buct.music.result;

import lombok.Data;

/**
 * @author Zhang JunFeng
 * @date 2020/12/30 21:23
 */
@Data
public class CodeMsg {
    private int code;
    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500, "Server exception");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
