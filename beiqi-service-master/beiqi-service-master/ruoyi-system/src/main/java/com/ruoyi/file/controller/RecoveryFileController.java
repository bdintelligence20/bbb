package com.ruoyi.file.controller;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.file.domain.RecoveryFile;
import com.ruoyi.file.domain.UserFile;
import com.ruoyi.file.dto.BatchDeleteRecoveryFileDTO;
import com.ruoyi.file.dto.DeleteRecoveryFileDTO;
import com.ruoyi.file.dto.RestoreFileDTO;
import com.ruoyi.file.service.IRecoveryFileService;
import com.ruoyi.file.service.IUserFileService;
import com.ruoyi.file.vo.RecoveryFileListVo;
import com.ruoyi.system.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

// description 文件删除后会进入回收站，该接口主要是对回收站文件进行管理
@RestController
@Slf4j
@RequestMapping("/recoveryfile")
public class RecoveryFileController extends BaseController {
    @Resource
    IRecoveryFileService recoveryFileService;
    @Resource
    IUserFileService userFileService;
    @Autowired
    TokenUtil tokenUtil;

    @Log(title = "删除回收文件", businessType = BusinessType.File)
    @RequestMapping(value = "/deleterecoveryfile", method = RequestMethod.POST)
    @PreAuthorize("@ss.hasPermi('file:0:delete')")
    public AjaxResult deleteRecoveryFile(@RequestBody DeleteRecoveryFileDTO deleteRecoveryFileDTO) {
        return deleteRecoveryFile(deleteRecoveryFileDTO, 0L);
    }

    @Log(title = "删除回收文件", businessType = BusinessType.File)
    @RequestMapping(value = "/deleterecoveryfile/1", method = RequestMethod.POST)
    @PreAuthorize("@ss.hasPermi('file:1:delete')")
    public AjaxResult deleteRecoveryFile1(@RequestBody DeleteRecoveryFileDTO deleteRecoveryFileDTO) {
        return deleteRecoveryFile(deleteRecoveryFileDTO, 1L);
    }

    private AjaxResult deleteRecoveryFile(DeleteRecoveryFileDTO deleteRecoveryFileDTO, long userId) {
        RecoveryFile recoveryFile = recoveryFileService.getById(deleteRecoveryFileDTO.getRecoveryFileId());
        UserFile userFile = userFileService.getById(recoveryFile.getUserFileId());
        if (userFile.getUserId() != userId) {
            return AjaxResult.error("无权限");
        }
        recoveryFileService.deleteRecoveryFile(userFile);
        recoveryFileService.removeById(deleteRecoveryFileDTO.getRecoveryFileId());
        return AjaxResult.success("删除成功");
    }

    @PostMapping(value = "/batchdelete")
    @Log(title = "批量删除回收文件", businessType = BusinessType.File)
    @PreAuthorize("@ss.hasPermi('file:0:delete')")
    @ResponseBody
    public AjaxResult batchDeleteRecoveryFile(@RequestBody BatchDeleteRecoveryFileDTO batchDeleteRecoveryFileDTO) {
        return batchDeleteRecoveryFile(batchDeleteRecoveryFileDTO, 0L);
    }

    @PostMapping(value = "/batchdelete/1")
    @Log(title = "批量删除回收文件", businessType = BusinessType.File)
    @PreAuthorize("@ss.hasPermi('file:1:delete')")
    @ResponseBody
    public AjaxResult batchDeleteRecoveryFile1(@RequestBody BatchDeleteRecoveryFileDTO batchDeleteRecoveryFileDTO) {
        return batchDeleteRecoveryFile(batchDeleteRecoveryFileDTO, 1L);
    }

    private AjaxResult batchDeleteRecoveryFile(BatchDeleteRecoveryFileDTO batchDeleteRecoveryFileDTO, long userId) {
        List<RecoveryFile> recoveryFileList = JSON.parseArray(batchDeleteRecoveryFileDTO.getRecoveryFileIds(), RecoveryFile.class);
        for (RecoveryFile recoveryFile : recoveryFileList) {
            RecoveryFile recoveryFile1 = recoveryFileService.getById(recoveryFile.getRecoveryFileId());
            UserFile userFile = userFileService.getById(recoveryFile1.getUserFileId());
            if (userFile.getUserId() != userId) {
                return AjaxResult.error("无权限");
            }
            recoveryFileService.deleteRecoveryFile(userFile);
            recoveryFileService.removeById(recoveryFile.getRecoveryFileId());
        }
        return AjaxResult.success("批量删除成功");
    }

    @Log(title = "回收文件列表", businessType = BusinessType.File)
    @PostMapping("/list")
    @PreAuthorize("@ss.hasPermi('file:0:list')")
    @ResponseBody
    public TableDataInfo getRecoveryFileList() {
        return getRecoveryFileList(0L);
    }

    @Log(title = "回收文件列表", businessType = BusinessType.File)
    @PostMapping("/list/1")
    @PreAuthorize("@ss.hasPermi('file:1:list')")
    @ResponseBody
    public TableDataInfo getRecoveryFileList1() {
        return getRecoveryFileList(1L);
    }

    private TableDataInfo getRecoveryFileList(long userId) {
        startPage();
        List<RecoveryFileListVo> recoveryFileList = recoveryFileService.selectRecoveryFileList(userId);
        return getDataTable(recoveryFileList);
    }

    @PostMapping(value = "/restorefile")
    @Log(title = "还原文件", businessType = BusinessType.File)
    @PreAuthorize("@ss.hasPermi('file:0:update')")
    public AjaxResult restoreFile(@RequestBody RestoreFileDTO restoreFileDto) {
        return restoreFile(restoreFileDto, 0L);
    }

    @PostMapping(value = "/restorefile/1")
    @Log(title = "还原文件", businessType = BusinessType.File)
    @PreAuthorize("@ss.hasPermi('file:1:update')")
    public AjaxResult restoreFile1(@RequestBody RestoreFileDTO restoreFileDto) {
        return restoreFile(restoreFileDto, 1L);
    }

    private AjaxResult restoreFile(RestoreFileDTO restoreFileDto, long userId) {
        recoveryFileService.restorefile(restoreFileDto.getDeleteBatchNum(), restoreFileDto.getFilePath(), userId);
        return AjaxResult.success("还原成功！");
    }
}









