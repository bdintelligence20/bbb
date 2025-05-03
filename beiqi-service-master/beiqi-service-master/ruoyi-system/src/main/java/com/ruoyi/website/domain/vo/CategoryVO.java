package com.ruoyi.website.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "导航实体")
public class CategoryVO extends BaseEntity {

    @ApiModelProperty("导航ID")
    private Long id;

//    @ApiModelProperty("名称")
//    private String name;

    @ApiModelProperty("父导航ID")
    private Long parentId;

    @ApiModelProperty("显示顺序")
    private Integer sort;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("组件路径")
    private String component;

    @ApiModelProperty("导航状态 0=显示,1=隐藏")
    private String visible;

    @ApiModelProperty("导航状态 0=正常,1=停用")
    private String status;

    @ApiModelProperty("导航类型 0=普通目录,1=产品目录")
    private String menuType;

    @ApiModelProperty("导航图标")
    private String icon;

    @ApiModelProperty("显示位置(0 顶部 1 底部)")
    private Integer topOrBottom;

    @ApiModelProperty("导航多语言实体")
    private List<CategoryLangVO> categoryLangs;
}
