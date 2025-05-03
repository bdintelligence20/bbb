package com.ruoyi.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlacklistIntercept {

    private String ip;

    private Long time;

    private int value;

    private String method;


    public BlacklistIntercept(){
        this.method = "com.ruoyi.web.controller.home.TestDriveController-insert";
    }
}
