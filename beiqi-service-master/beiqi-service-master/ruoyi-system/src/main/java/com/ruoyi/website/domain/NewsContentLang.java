package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新闻内容多语言对象 website_news_content_lang
 * 
 * @author thh
 * @date 2022-06-01
 */
@ApiModel(description = "新闻实体多语言")
public class NewsContentLang extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("新闻内容ID")
    private Long newsContentId;

    /** 文章内容 */
    @ApiModelProperty("文章内容")
    @Excel(name = "文章内容")
    private String content;

    /** 语种 */
    @ApiModelProperty("语种")
    @Excel(name = "语种")
    private String language;

    /** 逻辑删除 1已删除， 0未删除 */
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getContent() 
    {
        return content;
    }
    public void setLanguage(String language) 
    {
        this.language = language;
    }

    public String getLanguage() 
    {
        return language;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public Long getNewsContentId() {
        return newsContentId;
    }

    public void setNewsContentId(Long newsContentId) {
        this.newsContentId = newsContentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId()).append("newsContentId", getNewsContentId())
            .append("content", getContent())
            .append("language", getLanguage())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .toString();
    }
}
