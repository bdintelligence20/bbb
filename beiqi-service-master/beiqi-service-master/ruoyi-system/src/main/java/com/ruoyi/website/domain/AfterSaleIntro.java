package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 售后简介列对象 website_after_sale_intro
 * 
 * @author thh
 * @date 2022-06-10
 */
@Data
@ApiModel("售后简介列")
public class AfterSaleIntro extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @ApiModelProperty("ID")
    private Long id;

    /** 显示顺序 */
    @ApiModelProperty("显示顺序")
    private Integer sort;

    /** 是否展示（0显示 1隐藏） */
    @ApiModelProperty("是否展示（0显示 1隐藏）")
    private Integer status;

}
