package com.ruoyi.index.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName SearchParamVO
 * @Description 搜索实体类
 * @Author thh
 * @Date 20:52 2021/10/14
 **/
@Data
@ApiModel(description = "关键字搜索实体")
public class SearchParamVO {
    /**
     * 关键字
     */
    @ApiModelProperty("关键字")
    private String keyword;

    @ApiModelProperty("语种")
    private String l;



//    private String tableName;
//
//    private String author;
//
//    private Map<String, Object> params;
}
