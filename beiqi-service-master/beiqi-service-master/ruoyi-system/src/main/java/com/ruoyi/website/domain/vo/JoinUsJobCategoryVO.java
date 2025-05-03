package com.ruoyi.website.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("招聘工作类型添加实体")
public class JoinUsJobCategoryVO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("显示顺序")
    private Integer sort;

    @ApiModelProperty("语是否展示种 0=显示,1=隐藏")
    private Integer status;

    @ApiModelProperty("工作类型ID")
    private Long jobCategoryLangId;

    @ApiModelProperty("语种")
    private String name;

    @ApiModelProperty("语种")
    private String language;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
