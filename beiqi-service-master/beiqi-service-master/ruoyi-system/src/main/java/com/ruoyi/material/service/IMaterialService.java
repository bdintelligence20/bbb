package com.ruoyi.material.service;

import java.util.List;
import com.ruoyi.material.domain.Material;
import com.ruoyi.website.domain.vo.MaterialVO;

/**
 * 素材Service接口
 * 
 * @author thh
 * @date 2023-08-03
 */
public interface IMaterialService 
{
    /**
     * 查询素材
     * 
     * @param materialId 素材主键
     * @return 素材
     */
    public List<MaterialVO> selectMaterialByMaterialId(Long materialId);

    /**
     * 查询素材列表
     * 
     * @param material 素材
     * @return 素材集合
     */
    public List<Material> selectMaterialList(Material material);

    /**
     * 新增素材
     * 
     * @param material 素材
     * @return 结果
     */
    public int insertMaterial(Material material);

    /**
     * 修改素材
     * 
     * @param material 素材
     * @return 结果
     */
    public int updateMaterial(Material material);

    /**
     * 批量删除素材
     * 
     * @param materialIds 需要删除的素材主键集合
     * @return 结果
     */
    public int deleteMaterialByMaterialIds(Long[] materialIds);

    /**
     * 删除素材信息
     * 
     * @param materialId 素材主键
     * @return 结果
     */
    public int deleteMaterialByMaterialId(Long materialId);

    /**
     * 根据车型/userid素材查询列表
     *
     * @param materialVo
     * @return
     */
    List<MaterialVO> selectMaterial(MaterialVO materialVo);

}
