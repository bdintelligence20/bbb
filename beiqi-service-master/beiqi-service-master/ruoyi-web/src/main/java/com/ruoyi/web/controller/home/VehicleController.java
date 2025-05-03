package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.VehicleCharacteristicType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.home.vehicle.domain.*;
import com.ruoyi.home.vehicle.service.*;
import com.ruoyi.home.vo.vehicle.*;
import com.ruoyi.website.service.LangService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 车辆型号
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("vehicle")
@Validated
@AllArgsConstructor
@Api(value = "VehicleController", tags = "车辆（类型|型号")
public class VehicleController {
    private VehicleClassService vehicleClassService;
    private VehicleTypeService vehicleTypeService;
    private LangService langService;
    private VehicleCharacteristicGalleryDetailService vehicleCharacteristicGalleryDetailService;
    private VehicleCharacteristicImgAndTextService vehicleCharacteristicImgAndTextService;
    private VehicleCharacteristicTextBlockService vehicleCharacteristicTextBlockService;
    private VehicleColorService vehicleColorService;
    private VehicleGalleryService vehicleGalleryService;
    private VehicleSpecificationImgAndTextService vehicleSpecificationImgAndTextService;

    @GetMapping("class/{lang}")
    @ApiOperation("获取对应语言的车辆类型列表（包含车辆型号列表")
    public AjaxResultT<List<VehicleTypeHomeVo>> typeList(@PathVariable String lang,
                                                         @RequestParam(required = false) Integer typeId,
                                                         @RequestParam(required = false, defaultValue = "false") Boolean presentation) {
        boolean isAllowedLangCode = langService.isAllowedLangCode(lang);
        if (!isAllowedLangCode) return AjaxResultT.error("不被允许的语言类型：" + lang);
        List<VehicleType> vehicleTypes = vehicleTypeService.list(presentation);

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

                    List<VehicleClassSummaryHomeVo> vehicleClassSummaryHomeVos = vehicleClassService.listClass(lang, vehicleType.getId(), presentation);
                    vehicleTypeHomeVo.setVehicleClassSummaries(vehicleClassSummaryHomeVos);

                    return vehicleTypeHomeVo;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(VehicleTypeHomeVo::getSort))
                .collect(Collectors.toList());
        return AjaxResultT.success(vehicleTypeHomeVos);
    }

    @GetMapping("class/detail/{lang}")
    @ApiOperation("获取对应语言的车辆型号详情")
    public AjaxResultT<VehicleClassHomeVo> classDetail(@RequestParam Integer id, @PathVariable String lang) {
        boolean isAllowedLangCode = langService.isAllowedLangCode(lang);
        if (!isAllowedLangCode) return AjaxResultT.error("Not Support Language：" + lang);
        VehicleClassHomeVo vehicleClassHomeVo = vehicleClassService.getByLang(id, lang);
        if (vehicleClassHomeVo == null) return AjaxResultT.error("Vehicle Class Not Exist");

        //特性
        //特性-图文轮播
        VehicleSpecificationImgAndTextListHomeVo vehicleSpecificationImgAndTextListHomeVo = new VehicleSpecificationImgAndTextListHomeVo();
        List<VehicleSpecificationImgAndTextHomeVo> vehicleSpecificationImgAndTextHomeVos = vehicleSpecificationImgAndTextService.listByLang(id, lang);
        if (!vehicleSpecificationImgAndTextHomeVos.isEmpty()) {
            vehicleSpecificationImgAndTextListHomeVo.setSort(vehicleSpecificationImgAndTextHomeVos.get(0).getSort());
            vehicleSpecificationImgAndTextListHomeVo.setList(vehicleSpecificationImgAndTextHomeVos);
            vehicleSpecificationImgAndTextListHomeVo.setType(VehicleCharacteristicType.IMG_AND_TEXT_LIST.getName());
        } else {
            vehicleSpecificationImgAndTextListHomeVo = null;
        }
        //特性-文本行
        VehicleCharacteristicTextListHomeVo vehicleCharacteristicTextListHomeVo = new VehicleCharacteristicTextListHomeVo();
        List<VehicleCharacteristicTextBlockHomeVo> vehicleCharacteristicTextBlockHomeVos = vehicleCharacteristicTextBlockService.listByLang(id, lang);
        if (!vehicleCharacteristicTextBlockHomeVos.isEmpty()) {
            vehicleCharacteristicTextListHomeVo.setSort(vehicleCharacteristicTextBlockHomeVos.get(0).getSort());
            vehicleCharacteristicTextListHomeVo.setList(vehicleCharacteristicTextBlockHomeVos);
            vehicleCharacteristicTextListHomeVo.setType(VehicleCharacteristicType.TEXT_BLOCK_LIST.getName());
        } else {
            vehicleCharacteristicTextListHomeVo = null;
        }
        //特性-相册
        VehicleCharacteristicGalleryDetailHomeVo vehicleCharacteristicGalleryDetailHomeVos = vehicleCharacteristicGalleryDetailService.getByLang(id, lang);
        //特性-图文
        List<VehicleCharacteristicImgAndTextHomeVo> vehicleCharacteristicImgAndTextHomeVos = vehicleCharacteristicImgAndTextService.listByLang(id, lang);
        //特性集合
        List<VehicleCharacteristicHomeVo> vehicleCharacteristicHomeVos = new ArrayList<>();
        if (vehicleSpecificationImgAndTextListHomeVo != null) {
            vehicleCharacteristicHomeVos.add(vehicleSpecificationImgAndTextListHomeVo);
        }
        if (vehicleCharacteristicTextListHomeVo != null) {
            vehicleCharacteristicHomeVos.add(vehicleCharacteristicTextListHomeVo);
        }
        if (vehicleCharacteristicGalleryDetailHomeVos != null) {
            vehicleCharacteristicHomeVos.add(vehicleCharacteristicGalleryDetailHomeVos);
        }
        vehicleCharacteristicHomeVos.addAll(vehicleCharacteristicImgAndTextHomeVos);
        vehicleCharacteristicHomeVos = vehicleCharacteristicHomeVos.stream()
                .sorted(Comparator.comparingInt(VehicleCharacteristicHomeVo::getSort))
                .collect(Collectors.toList());
        vehicleClassHomeVo.setCharacteristics(vehicleCharacteristicHomeVos);

        //颜色
        List<VehicleColor> vehicleColors = vehicleColorService.list(id);
        List<VehicleColorHomeVo> vehicleColorHomeVos = vehicleColors.stream()
                .map(vehicleColor -> {
                    VehicleColorHomeVo vehicleColorHomeVo = new VehicleColorHomeVo();
                    vehicleColorHomeVo.setSort(vehicleColor.getSort());
                    vehicleColorHomeVo.setImgUrl(vehicleColor.getImgUrl());
                    vehicleColorHomeVo.setRgbCode(vehicleColor.getRgbCode());
                    return vehicleColorHomeVo;
                })
                .sorted(Comparator.comparingInt(VehicleColorHomeVo::getSort))
                .collect(Collectors.toList());
        vehicleClassHomeVo.setColors(vehicleColorHomeVos);

        //相册
        List<VehicleGallery> vehicleGalleries = vehicleGalleryService.list(id);
        List<String> vehicleGalleryImgUrls = vehicleGalleries.stream()
                .sorted(Comparator.comparingInt(VehicleGallery::getSort))
                .map(VehicleGallery::getImgUrl)
                .filter(StringUtils::isNotBlank)
                .map(vehicleGallery -> vehicleGallery.split(","))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        vehicleClassHomeVo.setGalleryImgUrls(vehicleGalleryImgUrls);
        return AjaxResultT.success(vehicleClassHomeVo);
    }


    @GetMapping
    @ApiOperation("获取车辆型号列表")
    @Log(title = "获取车辆型号列表", businessType = BusinessType.OTHER)
    public AjaxResultT<List<VehicleClass>> list(@RequestParam(required = false) Integer typeId) {
        List<VehicleClass> vehicleClasses = vehicleClassService.listByTypeId(typeId);
        vehicleClasses.sort(Comparator.comparingInt(VehicleClass::getSort));
        return AjaxResultT.success(vehicleClasses);
    }
}
