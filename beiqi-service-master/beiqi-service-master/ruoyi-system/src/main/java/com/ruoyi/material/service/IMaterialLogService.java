package com.ruoyi.material.service;

import java.util.List;
import com.ruoyi.system.domain.MaterialLog;
import com.ruoyi.website.domain.vo.MaterialLogVO;

/**
 * materialLogService接口
 * 
 * @author thh
 * @date 2023-11-15
 */
public interface IMaterialLogService 
{
    /**
     * 查询materialLog
     * 
     * @param id materialLog主键
     * @return materialLog
     */
    public MaterialLogVO selectMaterialLogById(Long id);

    /**
     * 查询materialLog列表
     * 
     * @param materialLog materialLog
     * @return materialLog集合
     */
    public List<MaterialLogVO> selectMaterialLogList(MaterialLogVO materialLog);

    /**
     * 新增materialLog
     * 
     * @param materialLog materialLog
     * @return 结果
     */
    public int insertMaterialLog(MaterialLog materialLog);

    /**
     * 修改materialLog
     * 
     * @param materialLog materialLog
     * @return 结果
     */
    public int updateMaterialLog(MaterialLog materialLog);

    /**
     * 批量删除materialLog
     * 
     * @param ids 需要删除的materialLog主键集合
     * @return 结果
     */
    public int deleteMaterialLogByIds(Long[] ids);

    /**
     * 删除materialLog信息
     * 
     * @param id materialLog主键
     * @return 结果
     */
    public int deleteMaterialLogById(Long id);
}
