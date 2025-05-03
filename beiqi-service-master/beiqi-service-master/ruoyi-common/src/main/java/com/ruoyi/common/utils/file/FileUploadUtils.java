package com.ruoyi.common.utils.file;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.Objects;
import com.ruoyi.common.utils.uuid.Seq;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.file.FileNameLengthLimitExceededException;
import com.ruoyi.common.exception.file.FileSizeLimitExceededException;
import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;

import static com.ibm.icu.impl.Assert.fail;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * 文件上传工具类
 *
 * @author ruoyi
 */
public class FileUploadUtils
{
    /**
     * 默认大小 1024M
     */
    public static final long DEFAULT_MAX_SIZE = 1024 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 默认上传的地址
     */
    private static String defaultBaseDir = RuoYiConfig.getProfile();

    public static void setDefaultBaseDir(String defaultBaseDir)
    {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    public static String getDefaultBaseDir()
    {
        return defaultBaseDir;
    }

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(MultipartFile file) throws IOException
    {
        try
        {
            return upload(getDefaultBaseDir(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(String baseDir, MultipartFile file) throws IOException
    {
        try
        {
            return upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }


    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param url 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String uploadByUrl(String baseDir, String url) throws IOException
    {
        if (StringUtils.isEmpty(url)){
            return "";
        }
        String extension = FilenameUtils.getExtension(url);
        File dirFile = new File(baseDir);
        if (!dirFile.exists()){
            dirFile.mkdirs();
        }
        String fileName = IdUtils.fastUUID() + "." + extension;
        String filePath = baseDir + "/" +  DateUtils.datePath() + "/" + fileName;
        FileUtils.download(url, filePath);
        File file = new File(filePath);
        if (file.exists()) {
            int dirLastIndex = RuoYiConfig.getProfile().length() + 1;
            String currentPath = StringUtils.substring(filePath, dirLastIndex);
            return "/" + currentPath;
        } else {
            return "";
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @param allowedExtension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException 比如读写文件出错时
     * @throws InvalidExtensionException 文件校验异常
     */
    public static final String upload(String baseDir, MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException,
            InvalidExtensionException
    {
        int fileNameLength = Objects.requireNonNull(file.getOriginalFilename()).length();
        if (fileNameLength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file, allowedExtension);

        String fileName = extractFilename(file);

        String absPath = getAbsoluteFile(baseDir, fileName).getAbsolutePath();
        file.transferTo(Paths.get(absPath));
        return  getPathFileName(baseDir, fileName);
    }

    /**
     * 文件上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @param allowedExtension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException 比如读写文件出错时
     * @throws InvalidExtensionException 文件校验异常
     */
    public static final String upload2(String baseDir, MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException,
            InvalidExtensionException
    {
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }
        assertAllowed(file, allowedExtension);
        String fileName = "";
        if (RuoYiConfig.getMovieVideoPath().equals(baseDir)) {
            fileName = extractFilename2(file);
        }else {
            fileName = extractFilename(file);
        }
        String absPath = getAbsoluteFile(baseDir, fileName).getAbsolutePath();
        file.transferTo(Paths.get(absPath));
        int dirLastIndex = RuoYiConfig.getProfile().length() + 1;
        String currentDir = StringUtils.substring(baseDir, dirLastIndex);
        return "/" + currentDir + "/" + fileName;
    }

    public static final String downloadImage(String baseDir,String imagePath) throws Exception{

        int dirLastIndex = RuoYiConfig.getProfile().length() + 1;
        String currentDir = StringUtils.substring(baseDir, dirLastIndex);
        int lastSlashIndex = imagePath.lastIndexOf('/');
        String filename = imagePath.substring(lastSlashIndex + 1);
        filename = DateUtils.datePath() + "/" + filename;
        String absolutePath = getAbsoluteFile(baseDir, filename).getAbsolutePath();
        try {
            download(absolutePath, imagePath);
        } catch (IOException e) {
            // 处理下载异常，例如打印错误信息或者抛出自定义的异常
            e.printStackTrace();
            throw new Exception("下载图片失败：" + imagePath + e.getMessage());
        }
        return "/" + currentDir + "/" + filename;
    }

    public static void download(String absolutePath, String durl) throws Exception {
        FileOutputStream outStream = null;
        InputStream is = null;
        try {
            URL url = new URL(durl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(300 * 1000);
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new Exception("图片 URL 不可用：" + durl);
            }
            is = conn.getInputStream();

            File imageFile = new File(absolutePath);
            outStream = new FileOutputStream(imageFile);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("下载图片失败：" + durl + e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 编码文件名
     */
    public static final String extractFilename(MultipartFile file)
    {
        return StringUtils.format("{}/{}_{}.{}", DateUtils.datePath(),
                FilenameUtils.getBaseName(file.getOriginalFilename()), Seq.getId(Seq.uploadSeqType), getExtension(file));
    }

    /**
     * 编码文件名
     */
    public static final String extractFilename2(MultipartFile file)
    {
        String uuid = IdUtils.fastUUID().replace("-","");
        return DateUtils.datePath() + "/" + uuid + "/" + uuid + "." + getExtension(file);
    }

    /**
     * 编码文件名
     */
    public static final String extractFilename(File file)
    {
        String extension = FilenameUtils.getExtension(file.getAbsolutePath());
        return DateUtils.datePath() + "/" + IdUtils.fastUUID() + "." + extension;
    }

    /**
     * 编码文件名
     */
    public static final String extractFilename2(File file)
    {
        String extension = FilenameUtils.getExtension(file.getAbsolutePath());
        String uuid = IdUtils.fastUUID().replace("-","");
        String fileName = DateUtils.datePath() + "/" + uuid + "/" + uuid + "." + extension;
        return fileName;
    }

    public  static final File getAbsoluteFile(String uploadDir, String fileName) throws IOException
    {
        File desc = new File(uploadDir + File.separator + fileName);

        if (!desc.exists()) {
            if (!desc.getParentFile().exists()) {
                desc.getParentFile().mkdirs();
            }
        }
        return desc;
    }

    public static final String getPathFileName(String uploadDir, String fileName) throws IOException
    {
        int dirLastIndex = RuoYiConfig.getProfile().length() + 1;
        String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
//        String pathFileName = Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
        String pathFileName = "/" + currentDir + "/" + fileName;
        return pathFileName;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws InvalidExtensionException
     */
    public static final void assertAllowed(MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, InvalidExtensionException
    {
        long size = file.getSize();
        if (size > DEFAULT_MAX_SIZE)
        {
            throw new FileSizeLimitExceededException(DEFAULT_MAX_SIZE / 1024 / 1024);
        }

        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension))
        {
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION)
            {
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
                        fileName);
            }
            else if (allowedExtension == MimeTypeUtils.FLASH_EXTENSION)
            {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        fileName);
            }
            else if (allowedExtension == MimeTypeUtils.MEDIA_EXTENSION)
            {
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
                        fileName);
            }
            else
            {
                throw new InvalidExtensionException(allowedExtension, extension, fileName);
            }
        }

    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension)
    {
        for (String str : allowedExtension)
        {
            if (str.equalsIgnoreCase(extension))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static final String getExtension(MultipartFile file)
    {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension))
        {
            extension =  MimeTypeUtils.getExtension(Objects.requireNonNull(file.getContentType()));
        }
        return extension;
    }
}
