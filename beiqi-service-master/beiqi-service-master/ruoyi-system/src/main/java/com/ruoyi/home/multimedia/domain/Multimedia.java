package com.ruoyi.home.multimedia.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 多媒体（图片、视频等
 *
 * @author liuzhuoming
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Multimedia {
    @ApiModelProperty("多媒体文件地址")
    private String url;
    @ApiModelProperty("多媒体文件标题")
    private String title;
    @ApiModelProperty("上传时间")
    private String uploadTime;
}
