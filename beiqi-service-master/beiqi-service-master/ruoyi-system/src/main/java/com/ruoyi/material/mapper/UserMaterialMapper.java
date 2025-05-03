package com.ruoyi.material.mapper;

import java.util.List;
import com.ruoyi.material.domain.UserMaterial;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.website.domain.vo.UserMaterialVO;
import org.apache.ibatis.annotations.Param;

/**
 * 用户和素材关联Mapper接口
 * 
 * @author thh
 * @date 2023-08-03
 */
public interface UserMaterialMapper 
{
    /**
     * 查询用户和素材关联
     * 
     * @param userId 用户和素材关联主键
     * @return 用户和素材关联
     */
    public UserMaterial selectUserMaterialByUserId(Long userId);

    /**
     * 查询用户和素材关联列表
     * 
     * @param userMaterial 用户和素材关联
     * @return 用户和素材关联集合
     */
    public List<UserMaterial> selectUserMaterialList(UserMaterial userMaterial);

    /**
     * 新增用户和素材关联
     * 
     * @param userMaterial 用户和素材关联
     * @return 结果
     */
    public int insertUserMaterial(UserMaterial userMaterial);

    /**
     * 修改用户和素材关联
     * 
     * @param userMaterialVO 用户和素材关联
     * @return 结果
     */
    public int updateUserMaterial(UserMaterialVO userMaterialVO);

    /**
     * 删除用户和素材关联
     * 
     * @param userId 用户和素材关联主键
     * @return 结果
     */
    public int deleteUserMaterialByUserId(Long userId);

    /**
     * 批量删除用户和素材关联
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserMaterialByUserIds(Long[] userIds);

    public int batchUserMaterial(List<UserMaterial> userMaterials);

    public int deleteAllByUserIdByMaterialId(@Param("materialId") Long[] materialIds,@Param("userId") Long userId);

    public int deleteByMaterialId(Long materialId);
}
