package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 售后简介列多语言对象 website_after_sale_intro_lang
 * 
 * @author thh
 * @date 2022-06-10
 */
@Data
@ApiModel("售后简介列多语言")
public class AfterSaleIntroLang extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @ApiModelProperty("ID")
    private Long id;

    /** 简介ID */
    @ApiModelProperty("简介ID")
    private Long saleIntroId;

    /** 一级标题 */
    @ApiModelProperty("一级标题")
    private String title;

    /** 一级描述 */
    @ApiModelProperty("一级描述")
    private String titleDescribe;

    /** 二级标题 */
    @ApiModelProperty("二级标题")
    private String subTitle;

    /** 二级描述 */
    @ApiModelProperty("二级描述")
    private String subTitleDescribe;

    /** 图片地址 */
    @ApiModelProperty("图片地址")
    private String imageUrl;

    /** 语种 */
    @ApiModelProperty("语种")
    private String language;
}
