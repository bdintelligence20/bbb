package com.ruoyi.website.domain.vo;

import com.ruoyi.website.domain.WebsiteBanner;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "banner实体")
@Data
public class WebSiteBannerVO extends WebsiteBanner {

    @ApiModelProperty("语言")
    private String language;
}
