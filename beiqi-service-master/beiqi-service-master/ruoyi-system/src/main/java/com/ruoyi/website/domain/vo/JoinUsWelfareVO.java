package com.ruoyi.website.domain.vo;


import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("招聘福利实体")
public class JoinUsWelfareVO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("福利ID")
    private Long welfareLangId;

    @ApiModelProperty("显示顺序")
    private Integer sort;

    @ApiModelProperty("是否展示 0=显示,1=隐藏")
    private Integer status;

    @ApiModelProperty("标题")
    @Excel(name = "标题")
    private String name;

    @ApiModelProperty("图片地址")
    private String imageUrl;

    @ApiModelProperty("语种")
    private String language;

    @ApiModelProperty("创建时间")
    private Date createTime;


}
