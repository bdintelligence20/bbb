package com.ruoyi.web.controller.material;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.home.vehicle.domain.VehicleType;
import com.ruoyi.home.vehicle.domain.VehicleTypeLang;
import com.ruoyi.home.vehicle.service.VehicleClassService;
import com.ruoyi.home.vehicle.service.VehicleTypeService;
import com.ruoyi.home.vo.vehicle.VehicleClassSummaryHomeVo;
import com.ruoyi.home.vo.vehicle.VehicleTypeHomeVo;
import com.ruoyi.material.service.IMaterialNewLabelServiceImpl;
import com.ruoyi.material.service.IMaterialService;
import com.ruoyi.website.domain.vo.MaterialNewLabelVO;
import com.ruoyi.website.domain.vo.MaterialVO;
import com.ruoyi.website.service.LangService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 素材Controller
 * 
 * @author thh
 * @date 2023-08-03
 */
@RestController
@RequestMapping("/dealer-download/system/material")
public class MaterialController extends BaseController
{
    @Autowired
    private IMaterialService materialService;

    @Autowired
    private LangService langService;

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @Autowired
    private VehicleClassService vehicleClassService;

    @Autowired
    private IMaterialNewLabelServiceImpl materialNewLabelService;


    /**
     * 根据车型/userid素材查询列表
     */
    //@PreAuthorize("@ss.hasRole('visiter')")

    @ApiOperation("根据车型/userid素材查询列表")
    @GetMapping("/getAllMaterialList")
    public AjaxResult getAllMaterialList(MaterialVO materialVo) {
        List<MaterialVO> list = materialService.selectMaterial(materialVo);
        return AjaxResult.success(list);
    }

    @ApiOperation("素材NEW标")
    @PostMapping("/newLabel")
    public AjaxResult insertNewLabelVO(@RequestBody MaterialNewLabelVO materialNewLabelVO) {
        return toAjax(materialNewLabelService.insertNewLabelVO(materialNewLabelVO));
    }

    @GetMapping("/getPyteNewLabel")
    public AjaxResult getPyteNewLabel(MaterialNewLabelVO materialNewLabelVO) {
        return AjaxResult.success(materialNewLabelService.selectTypeNewLabel(materialNewLabelVO));
    }

    /**
     * 消除new标
     * @param materialNewLabelVO
     * @return
     */
    @PostMapping("/PyteNewLabel")
    public AjaxResult insertPyteNewLabelVO(@RequestBody MaterialNewLabelVO materialNewLabelVO) {
        return AjaxResult.success(materialNewLabelService.insertTypeNewLabelVO(materialNewLabelVO));
    }

    @GetMapping("/getPyteNewLabels")
    public AjaxResult getPyteNewLabels(MaterialNewLabelVO materialNewLabelVO) {
        return AjaxResult.success(materialNewLabelService.selectTypeNewLabelS(materialNewLabelVO));
    }

    @GetMapping("class/{lang}")
    @ApiOperation("获取对应语言的车辆类型列表（包含车辆型号列表")
    public AjaxResultT<List<VehicleTypeHomeVo>> typeList(@PathVariable String lang,
                                                         @RequestParam(required = false) Integer typeId,
                                                         MaterialVO materialVo,
                                                         @RequestParam(required = false, defaultValue = "false") Boolean presentation) {
        boolean isAllowedLangCode = langService.isAllowedLangCode(lang);
        if (!isAllowedLangCode) return AjaxResultT.error("不被允许的语言类型：" + lang);
        List<VehicleType> vehicleTypes = vehicleTypeService.list(presentation);
        List<MaterialVO> list = materialService.selectMaterial(materialVo);

        if (typeId != null) {
            vehicleTypes = vehicleTypes.stream()
                    .filter(vehicleType -> Objects.equals(typeId, vehicleType.getId()))
                    .collect(Collectors.toList());
        }

        vehicleTypes.sort(Comparator.comparingInt(VehicleType::getSort));
        List<VehicleTypeHomeVo> vehicleTypeHomeVos = vehicleTypes.stream()
                .map(vehicleType -> {
                    List<VehicleTypeLang> langs = vehicleType.getLangs();
                    VehicleTypeLang vehicleTypeLang = null;
                    for (VehicleTypeLang vehicleTypeLangIterator : langs) {
                        if (Objects.equals(vehicleTypeLangIterator.getLang(), lang)) {
                            vehicleTypeLang = vehicleTypeLangIterator;
                            break;
                        }
                    }
                    if (vehicleTypeLang == null) return null;
                    VehicleTypeHomeVo vehicleTypeHomeVo = new VehicleTypeHomeVo();
                    vehicleTypeHomeVo.setId(vehicleType.getId());
                    vehicleTypeHomeVo.setSort(vehicleType.getSort());
                    vehicleTypeHomeVo.setName(vehicleTypeLang.getName());
                    List<VehicleClassSummaryHomeVo> homeVos = new ArrayList<>();
                    List<VehicleClassSummaryHomeVo> vehicleClassSummaryHomeVos = vehicleClassService.listClass(lang, vehicleType.getId(), presentation);
                    for (MaterialVO materialVO:list) {
                        for (VehicleClassSummaryHomeVo vehicleClassSummaryHomeVo :vehicleClassSummaryHomeVos) {
                            if (materialVO.getModel().equals(vehicleClassSummaryHomeVo.getId().toString())){
                                homeVos.add(vehicleClassSummaryHomeVo);
                            }
                        }
                    }
                    homeVos = homeVos.stream().sorted(Comparator.comparingInt(VehicleClassSummaryHomeVo::getSort)).distinct().collect(Collectors.toList());
                    vehicleTypeHomeVo.setVehicleClassSummaries(homeVos);
                    return vehicleTypeHomeVo;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(VehicleTypeHomeVo::getSort))
                .collect(Collectors.toList());
        Iterator<VehicleTypeHomeVo> iterator = vehicleTypeHomeVos.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getVehicleClassSummaries().isEmpty()) {
                iterator.remove();
            }
        }
        return AjaxResultT.success(vehicleTypeHomeVos);
    }
}
