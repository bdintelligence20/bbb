package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 车型特性类型
 *
 * @author liuzhuoming
 */
@AllArgsConstructor
@Getter
public enum VehicleCharacteristicType {
    TEXT_BLOCK_LIST("text_block_list"),
    IMG_AND_TEXT("img_and_text"),
    GALLERY("gallery"),
    IMG_AND_TEXT_LIST("img_and_text_list"),
    ;
    private final String name;
}
