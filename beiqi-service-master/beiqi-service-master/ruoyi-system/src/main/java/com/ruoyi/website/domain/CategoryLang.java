package com.ruoyi.website.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 导航多语言对象 website_category_lang
 * 
 * @author thh
 * @date 2022-06-01
 */
@Data
@ApiModel(description = "多语言导航实体")
public class CategoryLang extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 导航ID */
    private Long id;

    @ApiModelProperty("导航ID")
    private Long categoryId;

    /** 导航名称 */
    @ApiModelProperty("导航名称")
    @Excel(name = "导航名称")
    private String name;

    /** 父菜单ID */
    @ApiModelProperty("父导航ID")
    @Excel(name = "父导航ID")
    private Long parentId;

    /** 显示顺序 */
    @ApiModelProperty("显示顺序")
    @Excel(name = "显示顺序")
    private Integer sort;

    /** 路由地址 */
    @ApiModelProperty("路由地址")
    @Excel(name = "路由地址")
    private String path;

    /** 组件路径 */
    @ApiModelProperty("组件路径")
    @Excel(name = "组件路径")
    private String component;

    /** 菜单状态（0显示 1隐藏） */
    @ApiModelProperty("导航状态 0=显示,1=隐藏")
    @Excel(name = "导航状态", readConverterExp = "0=显示,1=隐藏")
    private String visible;

    /** 菜单状态（0正常 1停用） */
    @ApiModelProperty("导航状态 0=正常,1=停用")
    @Excel(name = "导航状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 菜单类型（0普通目录 1产品目录） */
    @ApiModelProperty("导航类型 0=普通目录,1=产品目录,2新闻目录")
    @Excel(name = "导航类型", readConverterExp = "导航类型 0=普通目录,1=产品目录,2新闻目录")
    private String menuType;

    /** 语种 */
    @ApiModelProperty("语种")
    @Excel(name = "语种")
    private String language;

    /** 菜单图标 */
    @ApiModelProperty("导航图标")
    @Excel(name = "导航图标")
    private String icon;

    @ApiModelProperty("显示位置(0 顶部 1 底部 2共用)")
    private Integer topOrBottom;

    /** 逻辑删除 1已删除， 0未删除 */
    @ApiModelProperty("逻辑删除 1已删除， 0未删除")
    private String delFlag;

}
