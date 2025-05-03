package com.ruoyi.file.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.qiwen.FileUtil;
import com.ruoyi.file.component.FileDealComp;
import com.ruoyi.file.domain.FileBean;
import com.ruoyi.file.domain.TreeNode;
import com.ruoyi.file.domain.UserFile;
import com.ruoyi.file.dto.*;
import com.ruoyi.file.service.IFileService;
import com.ruoyi.file.service.IUserFileService;
import com.ruoyi.file.vo.FileListVo;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.util.TokenUtil;
import com.ruoyi.ufo.factory.UFOFactory;
import com.ruoyi.ufo.operation.rename.domain.RenameFile;
import com.ruoyi.ufo.util.PathUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.ruoyi.common.utils.file.qiwen.FileUtil.getFileExtendsByType;


/**
 * 文件接口  做逻辑删除 移动 存储
 *
 * @author thh
 * @date 2021-06-05
 */
@RestController
@Slf4j
@RequestMapping("/file")
public class FileController extends BaseController {
    @Autowired
    IFileService fileService;
    @Autowired
    ISysUserService sysUserService;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    IUserFileService userFileService;
    @Resource
    FileDealComp fileDealComp;
    @Resource
    UFOFactory ufoFactory;

    @Log(title = "创建文件", businessType = BusinessType.File)
    @PostMapping("/createfile")
    @PreAuthorize("@ss.hasPermi('file:0:add')")
    public AjaxResult createFile(@RequestBody CreateFileDTO createFileDto) {
        return createFile(createFileDto, 0L);
    }

    @Log(title = "创建文件", businessType = BusinessType.File)
    @PostMapping("/createfile/1")
    @PreAuthorize("@ss.hasPermi('file:1:add')")
    public AjaxResult createFile1(@RequestBody CreateFileDTO createFileDto) {
        return createFile(createFileDto, 1L);
    }

    private AjaxResult createFile(CreateFileDTO createFileDto, long userId) {
        List<UserFile> userFiles = userFileService.selectUserFileByNameAndPath(createFileDto.getFileName(), createFileDto.getFilePath(), userId);
        if (userFiles != null && !userFiles.isEmpty()) {
            return AjaxResult.error(("同名文件已存在"));
        }
        UserFile userFile = new UserFile();
        userFile.setUserId(userId);
        userFile.setFileName(createFileDto.getFileName());
        userFile.setFilePath(createFileDto.getFilePath());
        userFile.setDirCover(createFileDto.getDirCover());
        userFile.setDeleteFlag(0);
        userFile.setIsDir(1);
        userFile.setUploadTime(DateUtils.getTime());
        userFileService.save(userFile);
        return AjaxResult.success();
    }

    @Log(title = "文件重命名", businessType = BusinessType.File)
    @PostMapping("/renamefile")
    @PreAuthorize("@ss.hasPermi('file:0:update')")
    public AjaxResult renameFile(@RequestBody RenameFileDTO renameFileDto) {
        return renameFile(renameFileDto, 0L);
    }

    @Log(title = "文件重命名", businessType = BusinessType.File)
    @PostMapping("/renamefile/1")
    @PreAuthorize("@ss.hasPermi('file:1:update')")
    public AjaxResult renameFile1(@RequestBody RenameFileDTO renameFileDto) {
        return renameFile(renameFileDto, 1L);
    }

    private AjaxResult renameFile(RenameFileDTO renameFileDto, long userId) {
        UserFile userFile = userFileService.getById(renameFileDto.getUserFileId());
        List<UserFile> userFiles = userFileService.selectUserFileByNameAndPath(renameFileDto.getFileName(), renameFileDto.getFilePath(), userId);
        if (userFiles != null && !userFiles.isEmpty()) {
            return AjaxResult.error("同名文件已存在");
        }
        if (1 == userFile.getIsDir()) {
            LambdaUpdateWrapper<UserFile> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.set(UserFile::getFileName, renameFileDto.getFileName())
                    .eq(UserFile::getUserFileId, renameFileDto.getUserFileId());
            userFileService.update(lambdaUpdateWrapper);
            userFileService.replaceUserFilePath(userFile.getFilePath() + renameFileDto.getFileName() + "/",
                    userFile.getFilePath() + userFile.getFileName() + "/", userId);
        } else {
            FileBean file = fileService.getById(userFile.getFileId());
            if (file.getStorageType() == 1) {
                String fileUrl = file.getFileUrl();
                String newFileUrl = fileUrl.replace(userFile.getFileName(), renameFileDto.getFileName());
                RenameFile renameFile = new RenameFile();
                renameFile.setSrcName(fileUrl.substring(1));
                renameFile.setDestName(newFileUrl.substring(1));
                ufoFactory.getRenamer(file.getStorageType()).rename(renameFile);
                LambdaUpdateWrapper<FileBean> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                lambdaUpdateWrapper
                        .set(FileBean::getFileUrl, newFileUrl)
                        .eq(FileBean::getFileId, file.getFileId());
                fileService.update(lambdaUpdateWrapper);
                LambdaUpdateWrapper<UserFile> userFileLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                userFileLambdaUpdateWrapper
                        .set(UserFile::getFileName, renameFileDto.getFileName())
                        .eq(UserFile::getUserFileId, renameFileDto.getUserFileId());
                userFileService.update(userFileLambdaUpdateWrapper);
            } else {
                LambdaUpdateWrapper<UserFile> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                lambdaUpdateWrapper.set(UserFile::getFileName, renameFileDto.getFileName())
                        .eq(UserFile::getUserFileId, renameFileDto.getUserFileId());
                userFileService.update(lambdaUpdateWrapper);
            }
        }
        fileDealComp.uploadESByUserFileId(renameFileDto.getUserFileId());
        return AjaxResult.success();
    }

    @Log(title = "批量删除文件", businessType = BusinessType.File)
    @PostMapping("/batchdeletefile")
    @PreAuthorize("@ss.hasPermi('file:0:delete')")
    public AjaxResult deleteFilesByIds(@RequestBody BatchDeleteFileDTO batchDeleteFileDto) {
        return deleteFilesByIds(batchDeleteFileDto, 0L);
    }

    @Log(title = "批量删除文件", businessType = BusinessType.File)
    @PostMapping("/batchdeletefile/1")
    @PreAuthorize("@ss.hasPermi('file:1:delete')")
    public AjaxResult deleteFilesByIds1(@RequestBody BatchDeleteFileDTO batchDeleteFileDto) {
        return deleteFilesByIds(batchDeleteFileDto, 1L);
    }

    private AjaxResult deleteFilesByIds(BatchDeleteFileDTO batchDeleteFileDto, long userId) {
        List<UserFile> userFiles = JSON.parseArray(batchDeleteFileDto.getFiles(), UserFile.class);
        for (UserFile userFile : userFiles) {
            userFileService.deleteUserFile(userFile.getUserFileId(), userId);
        }
        return AjaxResult.success("批量删除文件成功");
    }

    @Log(title = "可以删除文件或者目录", businessType = BusinessType.File)
    @PostMapping("/deletefile")
    @PreAuthorize("@ss.hasPermi('file:0:delete')")
    public AjaxResult deleteFile(@RequestBody DeleteFileDTO deleteFileDto) {
        return deleteFile(deleteFileDto, 0L);
    }

    @Log(title = "可以删除文件或者目录", businessType = BusinessType.File)
    @PostMapping("/deletefile/1")
    @PreAuthorize("@ss.hasPermi('file:1:delete')")
    public AjaxResult deleteFile1(@RequestBody DeleteFileDTO deleteFileDto) {
        return deleteFile(deleteFileDto, 1L);
    }

    private AjaxResult deleteFile(DeleteFileDTO deleteFileDto, long userId) {
        userFileService.deleteUserFile(deleteFileDto.getUserFileId(), userId);
        return AjaxResult.success();
    }

    @Log(title = "文件或者目录移动", businessType = BusinessType.File)
    @PostMapping("/movefile")
    @PreAuthorize("@ss.hasPermi('file:0:update')")
    public AjaxResult moveFile(@RequestBody MoveFileDTO moveFileDto) {
        return moveFile(moveFileDto, 0L);
    }

    @Log(title = "文件或者目录移动", businessType = BusinessType.File)
    @PostMapping("/movefile/1")
    @PreAuthorize("@ss.hasPermi('file:1:update')")
    public AjaxResult moveFile1(@RequestBody MoveFileDTO moveFileDto) {
        return moveFile(moveFileDto, 1L);
    }

    private AjaxResult moveFile(MoveFileDTO moveFileDto, long userId) {
        String oldfilePath = moveFileDto.getOldFilePath();
        String newfilePath = moveFileDto.getFilePath();
        String fileName = moveFileDto.getFileName();
        String extendName = moveFileDto.getExtendName();
        userFileService.updateFilepathByFilepath(oldfilePath, newfilePath, fileName, extendName, userId);
        return AjaxResult.success();
    }

    @Log(title = "批量移动文件", businessType = BusinessType.File)
    @PostMapping("/batchmovefile")
    @PreAuthorize("@ss.hasPermi('file:0:update')")
    public AjaxResult batchMoveFile(@RequestBody BatchMoveFileDTO batchMoveFileDto) {
        return batchMoveFile(batchMoveFileDto, 0L);
    }

    @Log(title = "批量移动文件", businessType = BusinessType.File)
    @PostMapping("/batchmovefile/1")
    @PreAuthorize("@ss.hasPermi('file:1:update')")
    public AjaxResult batchMoveFile1(@RequestBody BatchMoveFileDTO batchMoveFileDto) {
        return batchMoveFile(batchMoveFileDto, 1L);
    }

    private AjaxResult batchMoveFile(BatchMoveFileDTO batchMoveFileDto, long userId) {
        String files = batchMoveFileDto.getFiles();
        String newfilePath = batchMoveFileDto.getFilePath();
        List<UserFile> fileList = JSON.parseArray(files, UserFile.class);
        for (UserFile userFile : fileList) {
            userFileService.updateFilepathByFilepath(userFile.getFilePath(), newfilePath, userFile.getFileName(), userFile.getExtendName(), userId);
        }
        return AjaxResult.success("批量移动文件成功");
    }

    @Log(title = "通过文件类型选择文件", businessType = BusinessType.File)
    @GetMapping("/selectfilebyfiletype")
    @PreAuthorize("@ss.hasPermi('file:0:list')")
    public AjaxResult selectFileByFileType(int fileType, Long currentPage, Long pageCount) {
        return selectFileByFileTypeCommon(fileType, currentPage, pageCount, 0L);
    }

    @Log(title = "通过文件类型选择文件（经销商）", businessType = BusinessType.File)
    @GetMapping("/selectfilebyfiletype/1")
    @PreAuthorize("@ss.hasPermi('file:1:list')")
    public AjaxResult selectFileByFileType1(int fileType, Long currentPage, Long pageCount) {
        return selectFileByFileTypeCommon(fileType, currentPage, pageCount, 1L);
    }

    private AjaxResult selectFileByFileTypeCommon(int fileType, Long currentPage, Long pageCount, long userId) {
        List<FileListVo> fileList;
        Long beginCount;
        Long total;

        if (pageCount == null || currentPage == null) {
            beginCount = 0L;
            pageCount = 10L;
        } else {
            beginCount = (currentPage - 1) * pageCount;
        }
        if (fileType == FileUtil.OTHER_TYPE) {
            List<String> arrList = new ArrayList<>();
            arrList.addAll(Arrays.asList(FileUtil.DOC_FILE));
            arrList.addAll(Arrays.asList(FileUtil.IMG_FILE));
            arrList.addAll(Arrays.asList(FileUtil.VIDEO_FILE));
            arrList.addAll(Arrays.asList(FileUtil.MUSIC_FILE));
            fileList = userFileService.selectFileNotInExtendNames(arrList, beginCount, pageCount, userId);
            total = userFileService.selectCountNotInExtendNames(arrList, beginCount, pageCount, userId);
        } else {
            fileList = userFileService.selectFileByExtendName(getFileExtendsByType(fileType), beginCount, pageCount, userId);
            total = userFileService.selectCountByExtendName(getFileExtendsByType(fileType), beginCount, pageCount, userId);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", fileList);
        return AjaxResult.success(map);
    }


    @Log(title = "获取文件树", businessType = BusinessType.File)
    @GetMapping("/getfiletree")
    @PreAuthorize("@ss.hasPermi('file:0:list')")
    public AjaxResult getFileTree() {
        return getFileTree(0L);
    }

    @Log(title = "获取文件树", businessType = BusinessType.File)
    @GetMapping("/getfiletree/1")
    @PreAuthorize("@ss.hasPermi('file:1:list')")
    public AjaxResult getFileTree1() {
        return getFileTree(1L);
    }

    private AjaxResult getFileTree(long userId) {
        List<UserFile> userFileList = userFileService.selectFilePathTreeByUserId(userId);
        TreeNode resultTreeNode = new TreeNode();
        resultTreeNode.setLabel("/");
        resultTreeNode.setId(0L);
        long id = 1;
        for (UserFile userFile : userFileList) {
            String filePath = userFile.getFilePath() + userFile.getFileName() + "/";
            Queue<String> queue = new LinkedList<>();
            String[] strArr = filePath.split("/");
            for (String s : strArr) {
                if (!"".equals(s) && s != null) {
                    queue.add(s);
                }
            }
            if (queue.isEmpty()) {
                continue;
            }
            resultTreeNode = fileDealComp.insertTreeNode(resultTreeNode, id++, "/", queue);
        }
        List<TreeNode> treeNodeList = resultTreeNode.getChildren();
        treeNodeList.sort((o1, o2) -> {
            long i = o1.getId() - o2.getId();
            return (int) i;
        });
        return AjaxResult.success(resultTreeNode);
    }


    @Log(title = "获取文件列表", businessType = BusinessType.File)
    @GetMapping("/getfilelist")
    @PreAuthorize("@ss.hasPermi('file:0:list')")
    public AjaxResult getFileList(FileListDTO fileListDto) {
        return getFileList1(fileListDto, 0L);
    }

    @Log(title = "获取文件列表（经销商）", businessType = BusinessType.File)
    @GetMapping("/getfilelist/1")
    @PreAuthorize("@ss.hasPermi('file:1:list')")
    public AjaxResult getFileList1(FileListDTO fileListDto) {
        return getFileList1(fileListDto, 1L);
    }

    private AjaxResult getFileList1(FileListDTO fileListDto, long userId) {
        UserFile userFile = new UserFile();
        userFile.setUserId(userId);
        List<FileListVo> fileList;
        userFile.setFilePath(PathUtil.urlDecode(fileListDto.getFilePath()));
        if (fileListDto.getCurrentPage() == null || fileListDto.getPageCount() == null) {
            fileList = userFileService.userFileList(userFile, 0L, 10L);
        } else {
            Long beginCount = (fileListDto.getCurrentPage() - 1) * fileListDto.getPageCount();
            fileList = userFileService.userFileList(userFile, beginCount, fileListDto.getPageCount());

        }
        LambdaQueryWrapper<UserFile> userFileLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userFileLambdaQueryWrapper.eq(UserFile::getUserId, userFile.getUserId())
                .eq(UserFile::getFilePath, userFile.getFilePath())
                .eq(UserFile::getDeleteFlag, 0);
        int total = userFileService.count(userFileLambdaQueryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", fileList);
        return AjaxResult.success(map);
    }

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    @Autowired
    private RedisCache redisCache;

    //@Log(title = "简历权限下载", businessType = BusinessType.File)
    @GetMapping("/uploadJoinUs/{year}/{month}/{day}/{fileName}")
    public ResponseEntity<org.springframework.core.io.Resource> joinfiledownload(@PathVariable String year, @PathVariable String month, @PathVariable String day, @PathVariable String fileName, @CookieValue(value = "Token", defaultValue = "") String token) {

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            // 解析对应的权限以及用户信息
            String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
            String userKey = Constants.LOGIN_TOKEN_KEY + uuid;
            LoginUser user = redisCache.getCacheObject(userKey);
            if (user == null) {
                throw new AccessDeniedException("用户未登录");
            }
        } catch (Exception e) {
            throw new AccessDeniedException("用户未登录");
        }
        String path = RuoYiConfig.getUploadPathByJoinUs() + "/" + year + "/" + month + "/" + day + "/" + fileName;
        try {
            Path file = Paths.get(path).resolve(path).normalize();
            org.springframework.core.io.Resource resource = new org.springframework.core.io.UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                path = RuoYiConfig.getUploadPath() + "/" + year + "/" + month + "/" + day + "/" + fileName;
                file = Paths.get(path).resolve(path).normalize();
                try {
                    resource = new org.springframework.core.io.UrlResource(file.toUri());
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Error in file path", e);
                }
                if (resource.exists() || resource.isReadable()) {
                    return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                            .body(resource);
                } else {
                    throw new RuntimeException("File not found");
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error in file path", e);
        }
    }
}
