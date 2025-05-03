package com.ruoyi.system.util;

import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Component
public class SendMailUtil  {
    @Resource
    private SysUserMapper usermapper;
    /**
     * 解决平常类无法注入Mapper导致空指针错误问题
     */
    public static SendMailUtil sendMailUtil;
    @PostConstruct
    public void init(){
        sendMailUtil = this;
        sendMailUtil.usermapper = this.usermapper;
    }

    /**
     * 检查密码和重复密码是否一致
     * @param password
     * @param RepeatPassword
     * @return
     */
    public boolean CheckPassword(String password,String RepeatPassword){
        return Objects.equals(password, RepeatPassword);
    }
    /**
     * 判断邮箱格式是否正确
     * @param email
     * @return false或者true
     */

    public  boolean isEmail(String email) {
        if (email == null || email.length() < 1 || email.length() > 256) {
            return false;
        }
        Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        return pattern.matcher(email).matches();
    }


    /**生成随机的六位验证码*/
    public StringBuilder CreateCode() {
        String dates = "0123456789";
        StringBuilder code = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            int index = r.nextInt(dates.length());
            char c = dates.charAt(index);
            code.append(c);
        }
        return code;
    }
}

