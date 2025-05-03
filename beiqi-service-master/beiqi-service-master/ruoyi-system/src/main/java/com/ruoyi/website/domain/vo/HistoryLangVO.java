package com.ruoyi.website.domain.vo;

import com.ruoyi.website.domain.HistoryLang;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "历史")
public class HistoryLangVO extends HistoryLang {

    @ApiModelProperty("显示顺序")
    private Integer sort;
}
