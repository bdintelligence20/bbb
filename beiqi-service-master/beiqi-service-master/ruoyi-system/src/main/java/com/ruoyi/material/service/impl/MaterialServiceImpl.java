package com.ruoyi.material.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ruoyi.material.domain.UserMaterial;
import com.ruoyi.material.mapper.MaterialNewLabelMapper;
import com.ruoyi.material.mapper.MaterialTypeNewLabelMapper;
import com.ruoyi.material.mapper.UserMaterialMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.website.domain.vo.MaterialNewLabelVO;
import com.ruoyi.website.domain.vo.MaterialVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.material.mapper.MaterialMapper;
import com.ruoyi.material.domain.Material;
import com.ruoyi.material.service.IMaterialService;

/**
 * 素材Service业务层处理
 *
 * @author thh
 * @date 2023-08-03
 */
@Service
public class MaterialServiceImpl implements IMaterialService
{
    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private UserMaterialMapper userMaterialMapper;

    @Autowired
    private MaterialNewLabelMapper materialNewLabelMapper;

    @Autowired
    private MaterialTypeNewLabelMapper materialTypeNewLabelMapper;



    /**
     * 查询素材
     *
     * @param materialId 素材主键
     * @return 素材
     */
    @Override
    public List<MaterialVO> selectMaterialByMaterialId(Long materialId)
    {
        List<MaterialVO> list = new ArrayList<>();
        MaterialVO material = materialMapper.selectMaterialByMaterialId(materialId);
        Long[] userIdByMaterialId = materialMapper.selectMaterialByUserIdByMaterialId(materialId);
        material.setUserIds(userIdByMaterialId);
        list.add(material);
        return list;
    }

    /**
     * 查询素材列表
     *
     * @param material 素材
     * @return 素材
     */
    @Override
    public List<Material> selectMaterialList(Material material)
    {
        return materialMapper.selectMaterialList(material);
    }

    /**
     * 新增素材
     *
     * @param material 素材
     * @return 结果
     */
    @Override
    public int insertMaterial(Material material)
    {

        int i = materialMapper.insertMaterial(material);

        if (material.getStatus().equals("1")){
            List<UserMaterial> list = new ArrayList<UserMaterial>();
            List<MaterialNewLabelVO> materialNewLabelVOList = new ArrayList<>();
            for (Long userId : material.getUserIds())
            {
                MaterialNewLabelVO materialNewLabelVO = new MaterialNewLabelVO();
                UserMaterial ur = new UserMaterial();
                ur.setUserId(userId);
                ur.setMaterialId(material.getMaterialId());
                materialNewLabelVO.setModel(material.getModel());
                materialNewLabelVO.setType(material.getType());
                materialNewLabelVO.setUserId(userId);
                list.add(ur);
                materialNewLabelVOList.add(materialNewLabelVO);
                materialTypeNewLabelMapper.delectTypeNewLabels(materialNewLabelVO);
            }
            userMaterialMapper.batchUserMaterial(list);
        }else {
            materialTypeNewLabelMapper.delectTypeNewLabel(material);
        }
        return i;
    }


    /**
     * 修改素材
     *
     * @param material 素材
     * @return 结果
     */
    @Override
    public int updateMaterial(Material material) {
        int i = materialMapper.updateMaterial(material);
        if (material.getStatus().equals("1")) {
            List<UserMaterial> list = new ArrayList<UserMaterial>();
            for (Long userId : material.getUserIds())
            {
                UserMaterial ur = new UserMaterial();
                ur.setUserId(userId);
                ur.setMaterialId(material.getMaterialId());
                list.add(ur);
                MaterialNewLabelVO materialNewLabelVO = new MaterialNewLabelVO();
                materialNewLabelVO.setModel(material.getModel());
                materialNewLabelVO.setType(material.getType());
                materialNewLabelVO.setUserId(userId);
                materialTypeNewLabelMapper.delectTypeNewLabels(materialNewLabelVO);
            }
            userMaterialMapper.deleteByMaterialId(material.getMaterialId());
            userMaterialMapper.batchUserMaterial(list);
        }
        return i;
    }
        /**
         * 批量删除素材
         *
         * @param materialIds 需要删除的素材主键
         * @return 结果
         */
        @Override
        public int deleteMaterialByMaterialIds(Long[] materialIds)
        {
            return materialMapper.deleteMaterialByMaterialIds(materialIds);
        }

        /**
         * 删除素材信息
         *
         * @param materialId 素材主键
         * @return 结果
         */
        @Override
        public int deleteMaterialByMaterialId(Long materialId){
            return materialMapper.deleteMaterialByMaterialId(materialId);
        }

        /**
        * 根据车型/userid素材查询列表
        */
        @Override
        public List<MaterialVO> selectMaterial(MaterialVO materialVo) {
            List<MaterialVO> list = new ArrayList<>();
            final List<MaterialVO> list1 = materialMapper.selectMaterialByStatus(materialVo);
            final List<MaterialNewLabelVO> materialNewLabelVOS = materialNewLabelMapper.selectNewLabel(materialVo);
            for (MaterialVO materialVO:list1) {
                for (MaterialNewLabelVO materialNewLabelVO:materialNewLabelVOS) {
                    if(materialVO.getMaterialId().equals(materialNewLabelVO.getMaterialId())) {
                        materialVO.setNewLabelFlag(materialNewLabelVO.getNewLabelFlag());
                    }
                }
            }
            list.addAll(list1);
            final List<MaterialVO> list2 = materialMapper.selectMaterial(materialVo);
            for (MaterialVO materialVO:list2) {
                for (MaterialNewLabelVO materialNewLabelVO:materialNewLabelVOS) {
                    if(materialVO.getMaterialId().equals(materialNewLabelVO.getMaterialId())) {
                        materialVO.setNewLabelFlag(materialNewLabelVO.getNewLabelFlag());
                    }
                }
            }
            list.addAll(list2);
            list = list.parallelStream().sorted(Comparator.comparing(MaterialVO::getNewLabelFlag)).collect(Collectors.toList());
            return list;
        }

}
