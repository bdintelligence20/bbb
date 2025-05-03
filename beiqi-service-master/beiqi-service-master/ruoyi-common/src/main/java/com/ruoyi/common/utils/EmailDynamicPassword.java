package com.ruoyi.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.*;

@Slf4j
@Component
public class EmailDynamicPassword {

    @Value("${spring.datasource.druid.master.url}")
    private String url;

    @Value("${spring.datasource.druid.master.username}")
    private String username;

    @Value("${spring.datasource.druid.master.password}")
    private String password;

    /**
     * 若依项目结构问题,common包内无法使用mybatis,因此使用原生jdbc方式查询数据库
     * @return
     */
    public String getPassword() {
        String query = "SELECT dict_value FROM sys_dict_data WHERE dict_label = 'send_email_password' ORDER BY dict_sort" ;
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            log.info("从字典中读取send_email_password失败");
        }
        return null;
    }
}

