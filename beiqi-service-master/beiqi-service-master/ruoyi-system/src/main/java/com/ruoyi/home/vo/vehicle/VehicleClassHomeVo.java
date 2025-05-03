package com.ruoyi.home.vo.vehicle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 车辆型号 vo
 *
 * @author liuzhuoming
 */
@Data
@ApiModel(value = "VehicleClassHomeVo", description = "车辆型号 vo")
public class VehicleClassHomeVo {
    @ApiModelProperty("图片链接（用于车型列表")
    private String imgUrl;

    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("副标题")
    private String subTitle;
    @ApiModelProperty("详情")
    private String desc;

    @ApiModelProperty("颜色列表")
    private List<VehicleColorHomeVo> colors;

    @ApiModelProperty("特性列表")
    private List<VehicleCharacteristicHomeVo> characteristics;

    @ApiModelProperty("车型配置 PDF 文件链接")
    private String specificationsPDFUrl;
    @ApiModelProperty("车型配置标题")
    private String specificationsPDFTitle;
    @ApiModelProperty("车型配置详情")
    private String specificationsPDFDesc;

    @ApiModelProperty("VR 预览链接")
    private String vrViewUrl;

    @ApiModelProperty("相册图片链接集合")
    private List<String> galleryImgUrls;
}
