package com.ruoyi.website.domain.vo;

import com.ruoyi.website.domain.WebSiteCategory;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "导航下拉树")
@Data
public class WebSiteCategoryPO extends WebSiteCategory {

    @ApiModelProperty("子导航")
    private List<WebSiteCategoryPO> children  = new ArrayList<>();
}
