package com.ruoyi.home.multimedia.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.common.enums.MultimediaType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.qiwen.FileUtil;
import com.ruoyi.file.domain.FileBean;
import com.ruoyi.file.domain.UserFile;
import com.ruoyi.file.mapper.FileMapper;
import com.ruoyi.file.mapper.UserFileMapper;
import com.ruoyi.file.service.IUserFileService;
import com.ruoyi.file.vo.FileListVo;
import com.ruoyi.home.multimedia.domain.Multimedia;
import com.ruoyi.home.multimedia.domain.MultimediaBo;
import com.ruoyi.ufo.util.PathUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 多媒体 service
 *
 * @author liuzhuoming
 */
@Service
@AllArgsConstructor
public class MultimediaServiceImpl implements MultimediaService {
    private UserFileMapper userFileMapper;
    private FileMapper fileMapper;
    private IUserFileService userFileService;

    @Override
    public TableDataInfoT<Multimedia> page(Integer type, Integer pageNum, Integer pageSize) {
        List<MultimediaBo> multimediaList = list(type);
        //因为网盘文件的类型不确定性和重复性（不同用户同一文件的存储采取的是 id 复制的方式），在这里采取逻辑分页
        int count = multimediaList.size();
        List<Multimedia> multimediaListResult = multimediaList.stream()
                .map(multimediaBo -> {
                    Multimedia multimedia = new Multimedia();
                    multimedia.setUrl(multimediaBo.getUrl());
                    multimedia.setTitle(multimediaBo.getTitle());
                    multimedia.setUploadTime(multimediaBo.getUploadTime());
                    return multimedia;
                })
                .skip((long) pageSize * (pageNum - 1))
                .limit(pageSize)
                .collect(Collectors.toList());
        return new TableDataInfoT<>(count, multimediaListResult);
    }

    @Override
    public Map<String, List<Multimedia>> page2(Integer type) {
        List<MultimediaBo> multimediaList = list(type);
        Map<String, List<MultimediaBo>> stringListMap = multimediaList.stream()
                .collect(Collectors.groupingBy(MultimediaBo::getDirName));

        Map<String, List<Multimedia>> multimediaMap = new HashMap<>();
        for (Map.Entry<String, List<MultimediaBo>> multimediaBoEntry : stringListMap.entrySet()) {
            String dirName = multimediaBoEntry.getKey();
            List<MultimediaBo> multimediaBoList = multimediaBoEntry.getValue();
            List<Multimedia> multimediaListNew = multimediaBoList.stream()
                    .map(multimediaBo -> {
                        Multimedia multimedia = new Multimedia();
                        multimedia.setUrl(multimediaBo.getUrl());
                        multimedia.setTitle(multimediaBo.getTitle());
                        multimedia.setUploadTime(multimediaBo.getUploadTime());
                        return multimedia;
                    })
                    .collect(Collectors.toList());
            multimediaMap.put(dirName, multimediaListNew);
        }

        return multimediaMap;
    }

    @Override
    public Map<String, Object> page3(String filePath, Long pageNum, Long pageSize) {
        UserFile userFile = new UserFile();
        userFile.setUserId(0L);
        userFile.setFilePath(PathUtil.urlDecode(filePath));
        Long beginCount = (pageNum - 1) * pageSize;
        List<FileListVo> fileList = userFileService.userFileList(userFile, beginCount, pageSize);

        LambdaQueryWrapper<UserFile> userFileLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userFileLambdaQueryWrapper.eq(UserFile::getUserId, userFile.getUserId())
                .eq(UserFile::getFilePath, userFile.getFilePath())
                .eq(UserFile::getDeleteFlag, 0);
        int total = userFileService.count(userFileLambdaQueryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("list", fileList);
        return map;
    }

    private List<MultimediaBo> list(Integer type) {
        MultimediaType multimediaType = MultimediaType.getByCode(type);
        if (multimediaType == null) return Collections.emptyList();

        Object[] fileSuffixes = null;
        switch (multimediaType) {
            case IMAGE:
                fileSuffixes = FileUtil.IMG_FILE;
                break;
            case VIDEO:
                fileSuffixes = FileUtil.VIDEO_FILE;
                break;
            default:
        }
        if (fileSuffixes == null) return Collections.emptyList();

        LambdaQueryWrapper<UserFile> userFileLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userFileLambdaQueryWrapper
                .eq(UserFile::getDeleteFlag, 0)
                .in(UserFile::getExtendName, fileSuffixes);

        List<UserFile> userFileList = userFileMapper.selectList(userFileLambdaQueryWrapper);

        //已存在的文件 id 集合（多用户文件去重
        Set<Long> existFileIdSet = new HashSet<>();
        return userFileList.stream()
                .sorted(Comparator.comparingLong(UserFile::getFileId).reversed())
                //根据文件 id 去重，避免多用户操作导致单个文件在列表被查询出来多次
                .filter(userFile -> {
                    if (existFileIdSet.contains(userFile.getFileId())) {
                        return false;
                    } else {
                        existFileIdSet.add(userFile.getFileId());
                        return true;
                    }
                })
                .map(userFile -> {
                    LambdaQueryWrapper<FileBean> fileBeanLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    fileBeanLambdaQueryWrapper.eq(FileBean::getFileId, userFile.getFileId());
                    FileBean fileBean = fileMapper.selectOne(fileBeanLambdaQueryWrapper);
                    MultimediaBo multimedia = new MultimediaBo();
                    multimedia.setUrl(fileBean.getFileUrl());
                    multimedia.setTitle(userFile.getFileName());
                    multimedia.setUploadTime(userFile.getUploadTime());

                    if (StringUtils.isNotEmpty(userFile.getFilePath()) && !Objects.equals("/", userFile.getFilePath())) {
                        String dirName = userFile.getFilePath().substring(1, userFile.getFilePath().length() - 1);
                        multimedia.setDirName(dirName);
                    } else {
                        multimedia.setDirName("/");
                    }

                    return multimedia;
                })
                .collect(Collectors.toList());
    }
}
