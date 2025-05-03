package com.ruoyi.web.controller.common;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.file.service.IFiletransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.config.ServerConfig;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Api(tags = "上传&下载")
@RestController
@RequestMapping("/common")
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);



    @Resource
    IFiletransferService filetransferService;


    private static final String FILE_DELIMETER = ",";

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = RuoYiConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {   String filePath = RuoYiConfig.getUploadPath();
            String url = FileUploadUtils.upload2(filePath, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
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

    /**
     * 通用上传请求
     */
    @PostMapping("/uploadFileList")
    public AjaxResult uploadFile(List<MultipartFile> fileList) throws Exception
    {
        try
        {
            String filePath = RuoYiConfig.getUploadPath();
            List< Map<String, Object>> uploadFileList = new ArrayList<>();
            for (MultipartFile file : fileList) {
                String url = FileUploadUtils.upload2(filePath, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
                Map<String, Object> fileData = new HashMap<>();
                fileData.put("fileName", FilenameUtils.getName(url));
                fileData.put("fileOldName", file.getOriginalFilename());
                fileData.put("filesize", file.getSize());
                fileData.put("url", url);
                uploadFileList.add(fileData);
            }
            return AjaxResult.success(uploadFileList);
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }


    /**
     * 通用上传请求（多个）
     */
    @PostMapping("/uploads")
    public AjaxResult uploadFiles(List<MultipartFile> files) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            List<String> urls = new ArrayList<String>();
            List<String> fileNames = new ArrayList<String>();
            List<String> newFileNames = new ArrayList<String>();
            List<String> originalFilenames = new ArrayList<String>();
            for (MultipartFile file : files)
            {
                // 上传并返回新文件名称
                String url = FileUploadUtils.upload2(filePath, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
                urls.add(url);
                fileNames.add(file.getName());
                newFileNames.add(FileUtils.getName(url));
                originalFilenames.add(file.getOriginalFilename());
            }
            AjaxResult ajax = AjaxResult.success();
            ajax.put("urls", StringUtils.join(urls, FILE_DELIMETER));
            ajax.put("fileNames", StringUtils.join(fileNames, FILE_DELIMETER));
            ajax.put("newFileNames", StringUtils.join(newFileNames, FILE_DELIMETER));
            ajax.put("originalFilenames", StringUtils.join(originalFilenames, FILE_DELIMETER));
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }


    /**
     * 通用上传请求
     */
    @ApiOperation("图片上传")
    @Log(title = "图片上传", businessType = BusinessType.UPDATE)
    @PostMapping("/uploadImage")
    public AjaxResult uploadImage(MultipartFile file){
        try
        {
            if (!file.isEmpty())
            {
                String imageUrl = FileUploadUtils.upload2(RuoYiConfig.getImagePath(), file, MimeTypeUtils.IMAGE_EXTENSION);
                BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
                int width = bufferedImage.getWidth();
                int height = bufferedImage.getHeight();
                AjaxResult ajax = AjaxResult.success();
                ajax.put("url", imageUrl);
                ajax.put("fileName", imageUrl);
                ajax.put("newFileName", FileUtils.getName(imageUrl));
                ajax.put("originalFilename", file.getOriginalFilename());
                ajax.put("aspectRatio", width + "/" + height);
                return ajax;
            }
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }


    /**
     * 本地资源通用下载
     */
    @GetMapping("/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        try
        {
            if (!FileUtils.checkAllowDownload(resource))
            {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = RuoYiConfig.getProfile();
            // 数据库资源地址
//            String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
            String downloadPath = localPath + resource;
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 根据url下载图片
     * @param imagePath
     * @return
     */
    @GetMapping("/downloadImage")
    //@Log(title = "url下载图片", businessType = BusinessType.UPDATE)
    public AjaxResult downloadImage(String imagePath, String batch) throws Exception{
        return filetransferService.downloadImage(imagePath, batch);
    }

    /**
     * 根据url下载图片
     * @param imagePaths
     * @return
     */
    @PostMapping("/downloadImages")
    @Log(title = "批量url下载图片", businessType = BusinessType.UPDATE)
    public AjaxResult downloadImages(@RequestBody List<String> imagePaths ) throws Exception{
        String baseDir = RuoYiConfig.getImagePath();
        List<String> imageUrls = new ArrayList<>();
        for (String imagePath : imagePaths) {
            String imageUrl = FileUploadUtils.downloadImage(baseDir, imagePath);
            imageUrls.add(imageUrl);
        }
        AjaxResult ajax = AjaxResult.success();
        ajax.put("url", imageUrls);
        return ajax;
    }
}
