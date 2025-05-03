package com.ruoyi.website.domain.vo;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "导航多语言实体")
public class CategoryLangVO extends BaseEntity {

    @ApiModelProperty("导航多语言ID")
    private Long id;

    @ApiModelProperty("语种")
    private String language;

    @ApiModelProperty("导航名称")
    private String name;
}
