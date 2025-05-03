package com.ruoyi.material.mapper;

import java.util.List;
import com.ruoyi.material.domain.Material;
import com.ruoyi.website.domain.vo.MaterialVO;

/**
 * 素材Mapper接口
 * 
 * @author thh
 * @date 2023-08-03
 */
public interface MaterialMapper 
{
    /**
     * 查询素材
     * 
     * @param materialId 素材主键
     * @return 素材
     */
    public MaterialVO selectMaterialByMaterialId(Long materialId);

    public  Long[] selectMaterialByUserIdByMaterialId(Long materialId);

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
     * 删除素材
     * 
     * @param materialId 素材主键
     * @return 结果
     */
    public int deleteMaterialByMaterialId(Long materialId);

    /**
     * 批量删除素材
     * 
     * @param materialIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaterialByMaterialIds(Long[] materialIds);


    public List<MaterialVO> selectMaterial(MaterialVO materialVo);

    public List<MaterialVO> selectMaterialByStatus(MaterialVO materialVo);

    public List<Material> selectMaterialListStatus();

}
