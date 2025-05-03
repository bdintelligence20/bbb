package com.ruoyi.website.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 历史多语言对象 website_history_lang
 * 
 * @author thh
 * @date 2022-06-09
 */
@Data
@ApiModel(description = "历史多语言实体")
public class HistoryLang extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("历史ID")
    private Long historyId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("图片链接")
    private String imageUrl;

    @ApiModelProperty("描述")
    private String describe;

    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    @ApiModelProperty("语种")
    private String language;

    @ApiModelProperty("显示顺序")
    private Integer sort;

}
