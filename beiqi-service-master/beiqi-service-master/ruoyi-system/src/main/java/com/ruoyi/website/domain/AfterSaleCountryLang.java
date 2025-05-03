package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 问卷星国家配置多语言对象 website_after_sale_country_lang
 * @date 2022-07-05
 */
@Data
@ApiModel(description = "问卷星国家配置多语言对象")
public class AfterSaleCountryLang extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long countryId;

    @ApiModelProperty("国家名称")
    private String name;

    @ApiModelProperty("语种")
    private String language;

    @ApiModelProperty("问卷星链接地址")
    private String linkUrl;
}
