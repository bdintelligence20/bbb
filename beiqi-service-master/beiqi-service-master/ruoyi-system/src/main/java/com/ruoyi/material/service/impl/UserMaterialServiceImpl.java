package com.ruoyi.material.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.website.domain.vo.UserMaterialVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.material.mapper.UserMaterialMapper;
import com.ruoyi.material.domain.UserMaterial;
import com.ruoyi.material.service.IUserMaterialService;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户和素材关联Service业务层处理
 * 
 * @author thh
 * @date 2023-08-03
 */
@Service
public class UserMaterialServiceImpl implements IUserMaterialService 
{
    @Autowired
    private UserMaterialMapper userMaterialMapper;

    /**
     * 查询用户和素材关联
     * 
     * @param userId 用户和素材关联主键
     * @return 用户和素材关联
     */
    @Override
    public UserMaterial selectUserMaterialByUserId(Long userId)
    {
        return userMaterialMapper.selectUserMaterialByUserId(userId);
    }

    /**
     * 查询用户和素材关联列表
     * 
     * @param userMaterial 用户和素材关联
     * @return 用户和素材关联
     */
    @Override
    public List<UserMaterial> selectUserMaterialList(UserMaterial userMaterial)
    {
        return userMaterialMapper.selectUserMaterialList(userMaterial);
    }

    /**
     * 新增用户和素材关联
     * 
     * @param userMaterial 用户和素材关联
     * @return 结果
     */
    @Override
    public int insertUserMaterial(UserMaterial userMaterial)
    {
        return userMaterialMapper.insertUserMaterial(userMaterial);
    }

    /**
     * 修改用户和素材关联
     * 
     * @param userMaterialVO 用户和素材关联
     * @return 结果
     */
    @Override
    public int updateUserMaterial(UserMaterialVO userMaterialVO)
    {
        return userMaterialMapper.updateUserMaterial(userMaterialVO);
    }

    /**
     * 批量删除用户和素材关联
     * 
     * @param userIds 需要删除的用户和素材关联主键
     * @return 结果
     */
    @Override
    public int deleteUserMaterialByUserIds(Long[] userIds)
    {
        return userMaterialMapper.deleteUserMaterialByUserIds(userIds);
    }

    /**
     * 删除用户和素材关联信息
     * 
     * @param userId 用户和素材关联主键
     * @return 结果
     */
    @Override
    public int deleteUserMaterialByUserId(Long userId)
    {
        return userMaterialMapper.deleteUserMaterialByUserId(userId);
    }


    public int insertMaterialUsers(Long materialId, Long[] userIds)
    {
        // 新增用户与素材管理
        List<UserMaterial> list = new ArrayList<UserMaterial>();
        for (Long userId : userIds)
        {
            UserMaterial ur = new UserMaterial();
            ur.setUserId(userId);
            ur.setMaterialId(materialId);
            list.add(ur);
        }
        return userMaterialMapper.batchUserMaterial(list);
    }

    public int insertMaterialsUsers(Long[] materialIds,Long[] userIds) {
        List<UserMaterial> list = new ArrayList<UserMaterial>();
        for (Long userId : userIds) {
            for (Long materialId : materialIds) {
                UserMaterial ur = new UserMaterial();
                ur.setUserId(userId);
                ur.setMaterialId(materialId);
                list.add(ur);
            }
        }
        return userMaterialMapper.batchUserMaterial(list);
    }

    @Override
    public int deleteAllByUserIdByMaterialId(Long[] materialIds, Long userId) {

        return userMaterialMapper.deleteAllByUserIdByMaterialId(materialIds,userId);
    }
}
