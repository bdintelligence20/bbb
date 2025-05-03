package com.ruoyi.web.controller.material;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.material.domain.Material;
import com.ruoyi.material.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 素材Controller
 * 
 * @author thh
 * @date 2023-08-03
 */
@RestController
@RequestMapping("/system/material")
public class MaterialController extends BaseController
{
    @Autowired
    private IMaterialService materialService;

    /**
     * 查询素材列表
     */

    @PreAuthorize("@ss.hasPermi('system:material:list')")
    @GetMapping("/list")
    public TableDataInfo list(Material material)
    {
        startPage();
        List<Material> list = materialService.selectMaterialList(material);
        return getDataTable(list);
    }

    /**
     * 导出素材列表
     */
    @PreAuthorize("@ss.hasPermi('system:material:export')")
    @Log(title = "素材", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Material material)
    {
        List<Material> list = materialService.selectMaterialList(material);
        ExcelUtil<Material> util = new ExcelUtil<Material>(Material.class);
        util.exportExcel(response, list, "素材数据");
    }

    /**
     * 获取素材详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:material:query')")
    @GetMapping(value = "/{materialId}")
    public AjaxResult getInfo(@PathVariable("materialId") Long materialId)
    {
        return AjaxResult.success(materialService.selectMaterialByMaterialId(materialId));
    }

    /**
     * 新增素材
     */
    @PreAuthorize("@ss.hasPermi('system:material:add')")
    @Log(title = "素材", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Material material)
    {
        return toAjax(materialService.insertMaterial(material));
    }

    /**
     * 修改素材
     */
    @PreAuthorize("@ss.hasPermi('system:material:edit')")
    @Log(title = "素材", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Material material)
    {
        return toAjax(materialService.updateMaterial(material));
    }

    /**
     * 删除素材
     */
    @PreAuthorize("@ss.hasPermi('system:material:remove')")
    @Log(title = "素材", businessType = BusinessType.DELETE)
	@DeleteMapping("/{materialIds}")
    public AjaxResult remove(@PathVariable Long[] materialIds)
    {
        return toAjax(materialService.deleteMaterialByMaterialIds(materialIds));
    }

}
