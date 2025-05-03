package com.ruoyi.home.vo.testdrive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 区域 vo
 *
 * @author liuzhuoming
 */
@Data
@ApiModel(value = "AreaHomeVo", description = "区域 vo")
public class AreaHomeVo {
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("排序值")
    private Integer sort;
    @ApiModelProperty("多语言名称")
    private String name;
}
