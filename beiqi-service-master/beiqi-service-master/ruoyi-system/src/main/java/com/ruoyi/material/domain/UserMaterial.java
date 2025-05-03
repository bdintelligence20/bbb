package com.ruoyi.material.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户和素材关联对象 sys_user_material
 * 
 * @author thh
 * @date 2023-08-03
 */
public class UserMaterial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long userId;

    /** 素材ID */
    private Long materialId;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setMaterialId(Long materialId)
    {
        this.materialId = materialId;
    }

    public Long getMaterialId()
    {
        return materialId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("materialId", getMaterialId())
            .toString();
    }
}
