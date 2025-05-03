package com.ruoyi.website.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
@ApiModel(description = "卷星国家配置对象")
public class AfterSaleCountryLangVO {

    private Long id;

    @ApiModelProperty("是否展示（0显示 1隐藏）")
    private Integer status;

    @ApiModelProperty("多语言表Id")
    private Long countryLangId;

    @ApiModelProperty("国家名称")
    private String name;

    @ApiModelProperty("语种")
    private String language;

    @ApiModelProperty("问卷星链接地址")
    private String linkUrl;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
