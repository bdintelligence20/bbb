package com.ruoyi.web.controller.material;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.material.service.IMaterialLogService;
import com.ruoyi.website.domain.vo.MaterialLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * materialLogController
 * 
 * @author thh
 * @date 2023-11-15
 */
@RestController
@RequestMapping("/system/log")
public class MaterialLogController extends BaseController
{
    @Autowired
    private IMaterialLogService materialLogService;

    /**
     * 查询materialLog列表
     */
    @PreAuthorize("@ss.hasPermi('system:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaterialLogVO materialLog)
    {
        startPage();
        List<MaterialLogVO> list = materialLogService.selectMaterialLogList(materialLog);
        return getDataTable(list);
    }

    /**
     * 导出materialLog列表
     */
    @PreAuthorize("@ss.hasPermi('system:log:export')")
    @Log(title = "materialLog", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaterialLogVO materialLog)
    {
        List<MaterialLogVO> list = materialLogService.selectMaterialLogList(materialLog);
        ExcelUtil<MaterialLogVO> util = new ExcelUtil<MaterialLogVO>(MaterialLogVO.class);
        util.exportExcel(response, list, "materialLog数据");
    }

    /**
     * 获取materialLog详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(materialLogService.selectMaterialLogById(id));
    }
}
