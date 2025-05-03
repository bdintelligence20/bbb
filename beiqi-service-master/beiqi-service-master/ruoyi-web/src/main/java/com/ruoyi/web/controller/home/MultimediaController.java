package com.ruoyi.web.controller.home;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.file.dto.FileListDTO;
import com.ruoyi.home.multimedia.domain.Multimedia;
import com.ruoyi.home.multimedia.service.MultimediaService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 多媒体中心
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("multimedia")
@AllArgsConstructor
@Validated
@Api(value = "MultimediaController", tags = "多媒体中心")
public class MultimediaController {
    private MultimediaService multimediaService;

    @GetMapping("{lang}")
    @ApiOperation("获取多媒体文件列表")
    @ApiImplicitParams(
            @ApiImplicitParam(value = "文件类型 1-图片 2-视频", name = "type", paramType = "query", dataTypeClass = Integer.class, required = true)
    )
    public AjaxResultT<TableDataInfoT<Multimedia>> page(@PathVariable String lang,
                                                        @RequestParam Integer type,
                                                        @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                        @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        TableDataInfoT<Multimedia> multimediaTableDataInfoT = multimediaService.page(type, pageNum, pageSize);
        return AjaxResultT.success(multimediaTableDataInfoT);
    }

    @GetMapping("v2/{lang}")
    @ApiOperation("获取多媒体文件列表(v2)")
    @ApiImplicitParams(
            @ApiImplicitParam(value = "文件类型 1-图片 2-视频", name = "type", paramType = "query", dataTypeClass = Integer.class, required = true)
    )
    public AjaxResultT<Map<String, List<Multimedia>>> page2(@PathVariable String lang,
                                                            @RequestParam Integer type) {
        Map<String, List<Multimedia>> stringListMap = multimediaService.page2(type);
        return AjaxResultT.success(stringListMap);
    }

    @GetMapping("v3/{lang}")
    @ApiOperation("获取多媒体文件列表(v3)")
    @ApiImplicitParams({@ApiImplicitParam(value = "文件夹路径(/image/ 或 /video/)", name = "filePath", required = true),
            @ApiImplicitParam(value = "页码", name = "pageNum", required = true),
            @ApiImplicitParam(value = "每页条数", name = "pageSize", required = true)
    })
    public AjaxResultT<Map<String, Object>> getFileList(@PathVariable String lang,
                                  @RequestParam String filePath,
                                  @RequestParam(required = false, defaultValue = "1") Long pageNum,
                                  @RequestParam(required = false, defaultValue = "10") Long pageSize) {
        return AjaxResultT.success(multimediaService.page3(filePath, pageNum, pageSize));
    }
}
