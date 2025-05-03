package com.ruoyi.file.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.file.component.FileDealComp;
import com.ruoyi.file.domain.FileBean;
import com.ruoyi.file.domain.UserFile;
import com.ruoyi.file.service.IFileService;
import com.ruoyi.file.service.IFiletransferService;
import com.ruoyi.file.service.IUserFileService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class FileTask {

    @Resource
    IFileService fileService;
    @Resource
    IUserFileService userFileService;
    @Resource
    IFiletransferService filetransferService;

    @Resource
    FileDealComp fileDealComp;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void deleteFile() {
        LambdaQueryWrapper<FileBean> fileBeanLambdaQueryWrapper = new LambdaQueryWrapper<>();
        fileBeanLambdaQueryWrapper.eq(FileBean::getPointCount, 0);
        List<FileBean> fileBeanList = fileService.list(fileBeanLambdaQueryWrapper);
        for (int i = 0; i < fileBeanList.size(); i++) {
            FileBean fileBean = fileBeanList.get(i);
            filetransferService.deleteFile(fileBean);
            fileService.removeById(fileBean.getFileId());
        }
    }

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void updateElasticSearch() {
        try {
            //elasticSearchService.deleteAll();
        } catch (Exception e) {
        }

        List<UserFile> userfileList = userFileService.list();
        for (UserFile userFile : userfileList) {
            fileDealComp.uploadESByUserFileId(userFile.getUserFileId());
        }

    }
}
