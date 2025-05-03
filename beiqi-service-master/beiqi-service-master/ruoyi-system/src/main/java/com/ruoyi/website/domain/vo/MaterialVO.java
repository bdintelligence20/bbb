package com.ruoyi.website.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.material.domain.Material;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MaterialVO extends Material {
    private static final long serialVersionUID = 1L;

    /** 素材ID */
    private Long materialId;

    /** 素材类型 */
    @Excel(name = "素材类型")
    private String type;

    /** 车型 */
    @Excel(name = "车型")
    private String model;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String fileType;

    /** 图片类型 */
    @Excel(name = "图片类型")
    private String materialPicsType;

    /** 下载链接 */
    @Excel(name = "下载链接")
    private String downloadLink;

    /** 百度网盘下载链接 */
    @Excel(name = "百度网盘下载链接")
    private String baiduNetdisk;

    /** 时间 */
    @Excel(name = "时间")
    private String date;

    /** 内容介绍 */
    @Excel(name = "内容介绍")
    private String content;

    /** 文件格式 */
    @Excel(name = "文件格式")
    private String format;

    /** 市场 */
    @Excel(name = "市场")
    private String market;

    /** 数量 */
    @Excel(name = "数量")
    private Long qty;

    /** 第一张截图 */
    @Excel(name = "第一张截图")
    private String firstScreenshot;

    /** 第二张截图 */
    @Excel(name = "第二张截图")
    private String secondScreenshot;

    /** 第三张截图 */
    @Excel(name = "第三张截图")
    private String thirdScreenshot;

    /** 更多图片 */
    @Excel(name = "更多图片")
    private String viewMore;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    /** 状态(0公开 1私密) */
    @Excel(name = "状态", readConverterExp = "0=公开,1=私密")
    private String status;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("图片类型")
    private String picsType;

    @ApiModelProperty("new标 0为展示 1为隐藏")
    private String newLabelFlag;

    private Long[] userIds;
}
