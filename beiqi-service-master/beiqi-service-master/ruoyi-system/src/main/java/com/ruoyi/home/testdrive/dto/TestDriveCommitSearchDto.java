package com.ruoyi.home.testdrive.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 试驾预约申请搜索条件
 *
 * @author liuzhuoming
 */
@Data
public class TestDriveCommitSearchDto {
    @ApiModelProperty("区域 ID")
    private Integer areaId;
    @ApiModelProperty("语言类型")
    private String lang;

    @ApiModelProperty("预约人姓名")
    private String name;
    @ApiModelProperty("性别")
    private String gender;
    @ApiModelProperty("手机号码")
    private String telephone;
    @ApiModelProperty("邮箱")
    private String mailbox;

    @ApiModelProperty("试驾车型 ID")
    private Integer testDriveVehicleClassId;

    @ApiModelProperty("申请状态 0-已提交 1-申请通过 2-申请被拒")
    private Integer status;

    @ApiModelProperty("创建时间-开始")
    private Date createTimeStart;
    @ApiModelProperty("创建时间-结束")
    private Date createTimeEnd;
    @ApiModelProperty("更新时间-开始")
    private Date updateTimeStart;
    @ApiModelProperty("更新时间-结束")
    private Date updateTimeEnd;
}
