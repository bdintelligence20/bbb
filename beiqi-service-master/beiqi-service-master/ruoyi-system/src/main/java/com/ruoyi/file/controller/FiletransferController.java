package com.ruoyi.file.controller;


import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.common.utils.file.qiwen.FileUtil;
import com.ruoyi.file.domain.FileBean;
import com.ruoyi.file.domain.StorageBean;
import com.ruoyi.file.domain.UserFile;
import com.ruoyi.file.dto.DownloadFileDTO;
import com.ruoyi.file.dto.PreviewDTO;
import com.ruoyi.file.dto.UploadFileDTO;
import com.ruoyi.file.service.IFileService;
import com.ruoyi.file.service.IFiletransferService;
import com.ruoyi.file.service.IStorageService;
import com.ruoyi.file.service.IUserFileService;
import com.ruoyi.file.vo.FileListVo;
import com.ruoyi.file.vo.UploadFileVo;
import com.ruoyi.system.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * @author King
 * @Desc 该接口为文件传输接口
 */
@Slf4j
@RestController
@RequestMapping("/filetransfer")
public class FiletransferController extends BaseController {
    @Resource
    IFiletransferService filetransferService;
    @Resource
    IFileService fileService;
    @Resource
    IUserFileService userFileService;
    @Resource
    IStorageService storageService;
    @Autowired
    TokenUtil tokenUtil;

    /**
     * 校验文件MD5判断文件是否存在，如果存在直接上传成功并返回skipUpload=true，如果不存在返回skipUpload=false需要再次调用该接口的POST方法
     */
    @GetMapping("/uploadfile")
    @PreAuthorize("@ss.hasPermi('file:0:add')")
    @Log(title = "极速上传,校验文件MD5判断文件是否存在", businessType = BusinessType.File)
    public AjaxResult uploadFileSpeed(UploadFileDTO uploadFileDto) {
        return uploadFileSpeed(uploadFileDto, 0L);
    }

    @GetMapping("/uploadfile/1")
    @PreAuthorize("@ss.hasPermi('file:1:add')")
    @Log(title = "极速上传,校验文件MD5判断文件是否存在", businessType = BusinessType.File)
    public AjaxResult uploadFileSpeed1(UploadFileDTO uploadFileDto) {
        return uploadFileSpeed(uploadFileDto, 1L);
    }


    private AjaxResult uploadFileSpeed(UploadFileDTO uploadFileDto, long userId) {
        boolean isCheckSuccess = storageService.checkStorage(userId, uploadFileDto.getTotalSize());
        if (!isCheckSuccess) {
            return AjaxResult.error("存储空间不足");
        }

        String fileType = FileUtil.getFileExtendName(uploadFileDto.getFilename());
        List<String> supportFiles = new ArrayList<>();
        supportFiles.addAll(Arrays.asList(FileUtil.VIDEO_FILE));
        supportFiles.addAll(Arrays.asList(FileUtil.IMG_FILE));
        if (!supportFiles.contains(fileType.toLowerCase(Locale.ROOT))) {
            return AjaxResult.error("不支持的扩展名！目前支持的扩展名列表：" + supportFiles);
        }

        UploadFileVo uploadFileVo = new UploadFileVo();
        Map<String, Object> param = new HashMap<>();
        param.put("identifier", uploadFileDto.getIdentifier());
        synchronized (FiletransferController.class) {
            List<FileBean> list = fileService.listByMap(param);
            if (list != null && !list.isEmpty()) {
                FileBean file = list.get(0);
                UserFile userFile = new UserFile();
                userFile.setUserId(userId);
                userFile.setFilePath(uploadFileDto.getFilePath());
                String fileName = uploadFileDto.getFilename();
                userFile.setFileName(FileUtil.getFileNameNotExtend(fileName));
                userFile.setExtendName(FileUtil.getFileExtendName(fileName));
                List<FileListVo> userFileList = userFileService.userFileList(userFile, null, null);
                if (userFileList.isEmpty()) {
                    userFile.setIsDir(0);
                    userFile.setUploadTime(DateUtils.getTime());
                    userFile.setFileId(file.getFileId());
                    userFileService.save(userFile);
                    fileService.increaseFilePointCount(file.getFileId());
                }
                uploadFileVo.setSkipUpload(true);
            } else {
                uploadFileVo.setSkipUpload(false);
            }
        }
        return AjaxResult.success(uploadFileVo);
    }

    @PostMapping("/uploadfile")
    @PreAuthorize("@ss.hasPermi('file:0:add')")
    public AjaxResult uploadFile(HttpServletRequest request, UploadFileDTO uploadFileDto) {
        return uploadFile(request, uploadFileDto, 0L);
    }

    @PostMapping("/uploadfile/1")
    @PreAuthorize("@ss.hasPermi('file:1:add')")
    public AjaxResult uploadFile1(HttpServletRequest request, UploadFileDTO uploadFileDto) {
        return uploadFile(request, uploadFileDto, 1L);
    }

    private AjaxResult uploadFile(HttpServletRequest request, UploadFileDTO uploadFileDto, long userId) {
        String fileType = FileUtil.getFileExtendName(uploadFileDto.getFilename());
        List<String> supportFiles = new ArrayList<>();
        supportFiles.addAll(Arrays.asList(FileUtil.VIDEO_FILE));
        supportFiles.addAll(Arrays.asList(FileUtil.IMG_FILE));
        if (!supportFiles.contains(fileType.toLowerCase(Locale.ROOT))) {
            return AjaxResult.error("不支持的扩展名！目前支持的扩展名列表：" + supportFiles);
        }
        filetransferService.uploadFile(request, uploadFileDto, userId);
        UploadFileVo uploadFileVo = new UploadFileVo();
        return AjaxResult.success(uploadFileVo);
    }

    @Log(title = "下载文件", businessType = BusinessType.File)
    @GetMapping(value = "/downloadfile")
    //@PreAuthorize("@ss.hasPermi('file:0:get')")
    public void downloadFile(HttpServletResponse httpServletResponse, DownloadFileDTO downloadFileDTO) {
        downloadFile(httpServletResponse, downloadFileDTO, 0L);
    }

    @Log(title = "下载文件", businessType = BusinessType.File)
    @GetMapping(value = "/downloadfile/1")
    @PreAuthorize("@ss.hasPermi('file:1:get')")
    public void downloadFile1(HttpServletResponse httpServletResponse, DownloadFileDTO downloadFileDTO) {
        downloadFile(httpServletResponse, downloadFileDTO, 1L);
    }

    private void downloadFile(HttpServletResponse httpServletResponse, DownloadFileDTO downloadFileDTO, long userId) {
        httpServletResponse.setContentType("application/force-download");// 设置强制下载不打开
        UserFile userFile = userFileService.getById(downloadFileDTO.getUserFileId());
        if (!userFile.getUserId().equals(userId)) {
            return;
        }
        String fileName = "";
        if (userFile.getIsDir() == 1) {
            fileName = userFile.getFileName() + ".zip";
        } else {
            fileName = userFile.getFileName() + "." + userFile.getExtendName();

        }
        fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        httpServletResponse.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
        filetransferService.downloadFile(httpServletResponse, downloadFileDTO);
    }

    @GetMapping("/preview")
//    @PreAuthorize("@ss.hasPermi('file:0:get')")
    public void preview(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, PreviewDTO previewDTO) {
        preview(httpServletRequest, httpServletResponse, previewDTO, 0L);
    }

    @GetMapping("/preview/1")
//    @PreAuthorize("@ss.hasPermi('file:1:get')")
    public void preview1(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, PreviewDTO previewDTO) {
        preview(httpServletRequest, httpServletResponse, previewDTO, 1L);
    }

    private void preview(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, PreviewDTO previewDTO, long userId) {
        UserFile userFile = userFileService.getById(previewDTO.getUserFileId());
        if (!userFile.getUserId().equals(userId)) {
            return;
        }
        FileBean fileBean = fileService.getById(userFile.getFileId());
        String mime = MimeTypeUtils.getMime(userFile.getExtendName());
        httpServletResponse.setHeader("Content-Type", mime);
        String rangeString = httpServletRequest.getHeader("Range");//如果是video标签发起的请求就不会为null
        if (StringUtils.isNotEmpty(rangeString)) {
            long range = Long.parseLong(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
            httpServletResponse.setContentLength(Math.toIntExact(fileBean.getFileSize()));
            httpServletResponse.setHeader("Content-Range", String.valueOf(range + (Math.toIntExact(fileBean.getFileSize()) - 1)));
        }
        httpServletResponse.setHeader("Accept-Ranges", "bytes");
        String fileName = userFile.getFileName() + "." + userFile.getExtendName();
        fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        httpServletResponse.addHeader("Content-Disposition", "fileName=" + fileName);// 设置文件名
        try {
            filetransferService.previewFile(httpServletResponse, previewDTO);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("该异常忽略不做处理：" + e);
        }
    }

    @Log(title = "获取存储信息", businessType = BusinessType.File)
    @GetMapping("/getstorage")
    public AjaxResult getStorage() {
        StorageBean storage = new StorageBean();
        storage.setStorageSize(0L);
        storage.setTotalStorageSize(0L);
        return AjaxResult.success(storage);
    }
}
