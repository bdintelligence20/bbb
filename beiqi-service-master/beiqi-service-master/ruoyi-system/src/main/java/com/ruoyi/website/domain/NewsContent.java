package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新闻内容对象 website_news_content
 * 
 * @author thh
 * @date 2022-06-01
 */
@ApiModel(description = "新闻内容")
public class NewsContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @ApiModelProperty("ID")
    private Long id;

    /** 导航ID */
    @ApiModelProperty("新闻ID")
    @Excel(name = "新闻ID")
    private Long newsId;

    /** 文章内容 */
    @ApiModelProperty("文章内容")
    @Excel(name = "文章内容")
    private String content;

    /** 逻辑删除 1已删除， 0未删除 */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNewsId(Long newsId) 
    {
        this.newsId = newsId;
    }

    public Long getNewsId() 
    {
        return newsId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("newsId", getNewsId())
            .append("content", getContent())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .toString();
    }
}
