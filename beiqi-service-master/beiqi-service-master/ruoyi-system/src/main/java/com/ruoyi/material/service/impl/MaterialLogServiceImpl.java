package com.ruoyi.material.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.material.mapper.MaterialLogMapper;
import com.ruoyi.material.service.IMaterialLogService;
import com.ruoyi.website.domain.vo.MaterialLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.MaterialLog;


/**
 * materialLogService业务层处理
 * 
 * @author thh
 * @date 2023-11-15
 */
@Service
public class MaterialLogServiceImpl implements IMaterialLogService
{
    @Autowired
    private MaterialLogMapper materialLogMapper;

    /**
     * 查询materialLog
     * 
     * @param id materialLog主键
     * @return materialLog
     */
    @Override
    public MaterialLogVO selectMaterialLogById(Long id)
    {
        return materialLogMapper.selectMaterialLogById(id);
    }

    /**
     * 查询materialLog列表
     * 
     * @param materialLog materialLog
     * @return materialLog
     */
    @Override
    public List<MaterialLogVO> selectMaterialLogList(MaterialLogVO materialLog)
    {
        return materialLogMapper.selectMaterialLogList(materialLog);
    }

    /**
     * 新增materialLog
     * 
     * @param materialLog materialLog
     * @return 结果
     */
    @Override
    public int insertMaterialLog(MaterialLog materialLog)
    {
        materialLog.setCreateTime(DateUtils.getNowDate());
        return materialLogMapper.insertMaterialLog(materialLog);
    }

    /**
     * 修改materialLog
     * 
     * @param materialLog materialLog
     * @return 结果
     */
    @Override
    public int updateMaterialLog(MaterialLog materialLog)
    {
        return materialLogMapper.updateMaterialLog(materialLog);
    }

    /**
     * 批量删除materialLog
     * 
     * @param ids 需要删除的materialLog主键
     * @return 结果
     */
    @Override
    public int deleteMaterialLogByIds(Long[] ids)
    {
        return materialLogMapper.deleteMaterialLogByIds(ids);
    }

    /**
     * 删除materialLog信息
     * 
     * @param id materialLog主键
     * @return 结果
     */
    @Override
    public int deleteMaterialLogById(Long id)
    {
        return materialLogMapper.deleteMaterialLogById(id);
    }
}
