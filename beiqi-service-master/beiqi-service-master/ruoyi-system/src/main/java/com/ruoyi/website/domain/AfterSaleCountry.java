package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 问卷星国家配置对象 website_after_sale_country
 * @date 2022-07-05
 */
@Data
@ApiModel(description = "卷星国家配置对象")
public class AfterSaleCountry extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("是否展示（0显示 1隐藏）")
    private Integer status;
}
