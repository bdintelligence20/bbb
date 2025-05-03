package com.ruoyi.website.domain.vo;

import com.ruoyi.website.domain.News;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "新闻添加实体")
public class NewsLangVO extends News {

    @ApiModelProperty("新闻内容")
    private String content;

    @ApiModelProperty("语种")
    private String language;
}
