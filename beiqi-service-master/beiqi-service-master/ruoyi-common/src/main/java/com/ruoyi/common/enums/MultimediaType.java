package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 多媒体类型
 *
 * @author liuzhuoming
 */
@AllArgsConstructor
@Getter
public enum MultimediaType {
    IMAGE(1),
    VIDEO(2),
    ;
    private final Integer code;

    public static MultimediaType getByCode(Integer code) {
        if (code == null) return null;
        if (code == 1) return IMAGE;
        if (code == 2) return VIDEO;
        return null;
    }
}
