package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 售后服务项对象 website_after_sale_item
 * 
 * @author thh
 * @date 2022-06-10
 */
@Data
@ApiModel("售后简介列多语言")
public class AfterSaleItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("显示顺序")
    private Integer sort;

    @ApiModelProperty("是否展示（0显示 1隐藏）")
    private Integer status;


}
