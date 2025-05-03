package com.ruoyi.web.controller.website;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.website.domain.JoinUsArea;
import com.ruoyi.website.domain.JoinUsMail;
import com.ruoyi.website.domain.vo.JobTitleListVO;
import com.ruoyi.website.domain.vo.JoinUsMailVO;
import com.ruoyi.website.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Api(tags = "招聘")
@RestController
@RequestMapping("/website/join")
public class JoinUsController extends BaseController {
    @Autowired
    private IJoinUsMailService joinUsMailService;

    @Autowired
    private IJoinUsAreaService joinUsAreaService;

    @Autowired
    private IJoinUsJobCategoryService joinUsJobCategoryService;

    @Autowired
    private IJoinUsJobTitleService joinUsJobTitleService;

    @Autowired
    private IJoinUsWelfareService joinUsWelfareService;


    @ApiImplicitParams({@ApiImplicitParam(name = "l", value = "语种(path传参/{l})", required = true)})
    @ApiOperation("邮件发送")
    @PostMapping("/add/{l}")
    public AjaxResult add(@PathVariable("l") String l,@Validated @RequestBody JoinUsMailVO usMail, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return AjaxResult.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return toAjax(joinUsMailService.insertJoinUsMail(usMail));
    }

    @ApiOperation("招聘区域")
    @ApiImplicitParams({@ApiImplicitParam(name = "l", value = "语种(path传参/{l})", required = true)})
    @GetMapping("/area/{l}")
    public AjaxResult getJoinUsArea(@PathVariable("l") String l) {
        return AjaxResult.success(joinUsAreaService.getJoinUsAreaList(l));
    }

    @ApiOperation("工作类型")
    @ApiImplicitParams({@ApiImplicitParam(name = "l", value = "语种(path传参/{l})", required = true)})
    @GetMapping("/jcategory/{l}")
    public AjaxResult getJoinUsJobCategory(@PathVariable("l") String l) {
        return AjaxResult.success(joinUsJobCategoryService.getJoinUsJobCategoryList(l));
    }

    @ApiOperation("岗位列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "l", value = "语种(path传参/{l})"),
            @ApiImplicitParam(name = "areaId", value = "工作地点", paramType = "query"),
            @ApiImplicitParam(name = "categoryId", value = "工作类型", paramType = "query")
    })
    @GetMapping("/jlist/{l}")
    public TableDataInfo getJob(@PathVariable("l") String l, Long areaId, Long categoryId) {
        startPage();
        List<JobTitleListVO> list = joinUsJobTitleService.getPage(l, areaId, categoryId);
        return getDataTable(list);
    }

    @ApiOperation("岗位详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "l", value = "语种(path传参/{l})", required = true),
            @ApiImplicitParam(name = "id", value = "列表返回的id", paramType = "query", required = true)
    })
    @GetMapping("/jdetail/{l}")
    public AjaxResult getJobDetail(@PathVariable("l") String l,@RequestParam("id") Long id) {
        return AjaxResult.success(joinUsJobTitleService.getJobDetail(l, id));
    }

    @ApiOperation("福利配置列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "l", value = "语种(path传参/{l})", required = true)})
    @GetMapping("/jwelfare/{l}")
    public AjaxResult getWelfare(@PathVariable("l") String l) {
        return AjaxResult.success(joinUsWelfareService.getWelfare(l));
    }

    @ApiOperation("简历上传接口")
    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {   String filePath = RuoYiConfig.getUploadPathByJoinUs();
            String url = FileUploadUtils.upload(filePath, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
            url = url.replace("uploadBiographicalNotes", "uploadJoinUs");
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", url);
            ajax.put("newFileName", FileUtils.getName(url));
            ajax.put("originalFilename", file.getOriginalFilename());
            ajax.put("url", url);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }
}
