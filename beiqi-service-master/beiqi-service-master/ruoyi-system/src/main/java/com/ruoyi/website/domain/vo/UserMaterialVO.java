package com.ruoyi.website.domain.vo;

import lombok.Data;

@Data
public class UserMaterialVO {

    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long userId;

    /** 素材ID */
    private Long materialId;

    /** 新素材ID */
    private Long newMaterialId;
}
