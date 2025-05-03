package com.ruoyi.material.service;

import java.util.List;
import com.ruoyi.material.domain.UserMaterial;
import com.ruoyi.website.domain.vo.UserMaterialVO;

/**
 * 用户和素材关联Service接口
 * 
 * @author thh
 * @date 2023-08-03
 */
public interface IUserMaterialService 
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
     * 批量删除用户和素材关联
     * 
     * @param userIds 需要删除的用户和素材关联主键集合
     * @return 结果
     */
    public int deleteUserMaterialByUserIds(Long[] userIds);

    /**
     * 删除用户和素材关联信息
     * 
     * @param userId 用户和素材关联主键
     * @return 结果
     */
    public int deleteUserMaterialByUserId(Long userId);

    /**
     * 批量选择用户
     *
     * @param materialId 素材ID
     * @param userIds 用户和素材关联
     * @return 结果
     */
    public int insertMaterialUsers(Long materialId, Long[] userIds);

    public int insertMaterialsUsers(Long[] materialIds, Long[] userIds);

    public int deleteAllByUserIdByMaterialId(Long[] materialIds, Long userId);

}
