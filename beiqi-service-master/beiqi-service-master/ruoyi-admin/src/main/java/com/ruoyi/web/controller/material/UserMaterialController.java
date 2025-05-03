package com.ruoyi.web.controller.material;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.website.domain.vo.UserMaterialVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.material.domain.UserMaterial;
import com.ruoyi.material.service.IUserMaterialService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户和素材关联Controller
 * 
 * @author thh
 * @date 2023-08-03
 */
@RestController
@RequestMapping("/material/usermaterial")
public class UserMaterialController extends BaseController
{
    @Autowired
    private IUserMaterialService userMaterialService;

    /**
     * 查询用户和素材关联列表
     */
    @GetMapping("/list")
    public TableDataInfo list(UserMaterial userMaterial)
    {
        startPage();
        List<UserMaterial> list = userMaterialService.selectUserMaterialList(userMaterial);
        return getDataTable(list);
    }

    /**
     * 导出用户和素材关联列表
     */

    @Log(title = "用户和素材关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserMaterial userMaterial)
    {
        List<UserMaterial> list = userMaterialService.selectUserMaterialList(userMaterial);
        ExcelUtil<UserMaterial> util = new ExcelUtil<UserMaterial>(UserMaterial.class);
        util.exportExcel(response, list, "用户和素材关联数据");
    }

    /**
     * 获取用户和素材关联详细信息
     */

    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(userMaterialService.selectUserMaterialByUserId(userId));
    }

    /**
     * 新增用户和素材关联
     */

    @Log(title = "用户和素材关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserMaterial userMaterial)
    {
        return toAjax(userMaterialService.insertUserMaterial(userMaterial));
    }

    /**
     * 修改用户和素材关联
     */

    @Log(title = "用户和素材关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserMaterialVO userMaterialVO)
    {
        return toAjax(userMaterialService.updateUserMaterial(userMaterialVO));
    }

    /**
     * 删除用户和素材关联
     */

    @Log(title = "用户和素材关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(userMaterialService.deleteUserMaterialByUserIds(userIds));
    }


    /**
     * 批量增加用户和素材关联
     */
    @PostMapping("/insertAll")
    public AjaxResult insertMaterialUsers(Long[] materialIds, Long[] userIds)
    {
        return toAjax(userMaterialService.insertMaterialsUsers(materialIds, userIds));
    }

    /**
     * 批量删除用户和素材关联
     */
    @DeleteMapping("/deleteByUserIdByMaterialId")
    public AjaxResult delete(Long[] materialIds, Long userId) {
        return toAjax(userMaterialService.deleteAllByUserIdByMaterialId(materialIds, userId));
    }


}
