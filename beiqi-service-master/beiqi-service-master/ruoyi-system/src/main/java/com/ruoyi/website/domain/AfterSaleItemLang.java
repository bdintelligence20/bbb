package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 售后服务项多语言对象 website_after_sale_item_lang
 * 
 * @author thh
 * @date 2022-06-10
 */
@Data
@ApiModel("售后服务项多语言")
public class AfterSaleItemLang extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @ApiModelProperty("ID")
    private Long id;

    /** 简介ID */
    @ApiModelProperty("简介ID")
    private Long saleItemId;

    /** 标题 */
    @ApiModelProperty("标题")
    private String name;

    /** 图片地址 */
    @ApiModelProperty("图片地址")
    private String imageUrl;

    /** 语种 */
    @ApiModelProperty("语种")
    private String language;

}
