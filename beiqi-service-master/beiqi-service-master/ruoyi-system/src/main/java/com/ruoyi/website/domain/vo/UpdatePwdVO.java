package com.ruoyi.website.domain.vo;

import lombok.Data;

@Data
public class UpdatePwdVO {

    private String newPassword;

    private String repassword;

    private String email;

    private String code;
}
