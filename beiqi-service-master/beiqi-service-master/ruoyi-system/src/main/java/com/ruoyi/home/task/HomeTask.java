package com.ruoyi.home.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.config.EmailConfig;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.EmailService;
import com.ruoyi.home.contact.domain.ContactUs;
import com.ruoyi.home.contact.domain.CountryCodes;
import com.ruoyi.home.contact.mapper.CountryCodesMapper;
import com.ruoyi.home.contact.service.ContactUsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 主页相关定时任务
 *
 * @author liuzhuoming
 */
@Component
@AllArgsConstructor
@Slf4j
public class HomeTask {
    private ContactUsService contactUsService;
    private EmailService emailService;
    private EmailConfig emailConfig;
    private CountryCodesMapper countryCodesMapper;

    /**
     * 每天上午八点发送上一天八点到今日八点的消息到对应邮箱
     * 改为每周一八点发送前一周的数据
     */
    public void sendContactUsEmail() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        EmailConfig.ContactUsConfig contactUsConfig = emailConfig.getContactUs();
        if (contactUsConfig.isEnabled()) {
            String fromEmail = contactUsConfig.getFrom();
            String toEmail = contactUsConfig.getTo();
            Date endTime = new Date();
            Date startTime = DateUtils.addDays(endTime, -7);
            String dateStr = DateUtils.parseDateToStr("yyyy/MM/dd", startTime);
            List<ContactUs> contactUses = contactUsService.list(startTime, endTime);

            //没消息则不发送邮件
            if (contactUses.isEmpty()) return;

            //组装区域信息
            LambdaQueryWrapper<CountryCodes> contactUsLambdaQueryWrapper = new LambdaQueryWrapper<>();
            contactUsLambdaQueryWrapper.in(true, CountryCodes::getCountryAlpha2, contactUses.stream().map(ContactUs::getCountryIsoCode).distinct().collect(Collectors.toList()));
            List<CountryCodes> countryCodes = countryCodesMapper.selectList(contactUsLambdaQueryWrapper);
            List<Map<String, String>> contactUseMaps = new ArrayList<>();
            List<Map<String, String>> otherContactUseMaps = new ArrayList<>();
            for (ContactUs contactUs : contactUses) {
                Map<String, String> contactUseMap = new HashMap<>();
                contactUseMap.put("createTime", sdf.format(contactUs.getCreateTime()));
                contactUseMap.put("gender", contactUs.getGender());
                contactUseMap.put("name", contactUs.getName());
                contactUseMap.put("country", contactUs.getCountry());
                contactUseMap.put("telephone", contactUs.getTelephone());
                contactUseMap.put("email", contactUs.getEmail());
                contactUseMap.put("msgType", contactUs.getMsgType());
                contactUseMap.put("msg", contactUs.getMsg());
                contactUseMap.put("vin", contactUs.getVin());

                Optional<CountryCodes> first = countryCodes.stream().filter(ccs -> ccs.getCountryAlpha2().equals(contactUs.getCountryIsoCode())).findFirst();
                if (first.isPresent()) {
                    CountryCodes ccs = first.get();
                    contactUseMap.put("area", ccs.getArea());
                    contactUseMap.put("countryName", ccs.getCountryName());
                    contactUseMaps.add(contactUseMap);
                } else {
                    contactUseMap.put("area", "其他");
                    contactUseMap.put("countryName", "未知");
                    otherContactUseMaps.add(contactUseMap);
                }
            }

            contactUseMaps.sort(Comparator
                    .comparing((Map<String, String> map) -> map.get("area"))
                    .thenComparing(map -> map.get("countryName"))
                    .thenComparing(map -> map.get("msgType"))
            );

            otherContactUseMaps.sort(Comparator
                    .comparing((Map<String, String> map) -> map.get("area"))
                    .thenComparing(map -> map.get("countryName"))
                    .thenComparing(map -> map.get("msgType"))
            );

            contactUseMaps.addAll(otherContactUseMaps);

            Map<String, Object> kvs = new HashMap<>();
            //kvs.put("contactUses", contactUses);
            kvs.put("number", contactUseMaps.size());
            kvs.put("data", contactUseMaps);
            try {
                emailService.sendContactUs(EmailService.EmailTemplate.CONTACT_US, "BAIC国际官网用户留言信息-" + dateStr,
                        fromEmail, toEmail, Collections.emptyList(),
                        kvs, generateExcelStream(contactUseMaps));
            } catch (MessagingException e) {
                log.error("发送联系我们失败：{}", e.getMessage());
            }
        }
    }

    public static InputStreamSource generateExcelStream(List<Map<String, String>> dataListMap) {
        Workbook workbook = new HSSFWorkbook(); // HSSFWorkbook 适用于 .xls 格式
        Sheet sheet = workbook.createSheet("Data");

        // 设置列宽
        int[] columnWidths = {2000, 4000, 4000, 6000, 6000, 3000, 5000, 4000, 4000, 5000, 8000, 6000};
        for (int i = 0; i < columnWidths.length; i++) {
            sheet.setColumnWidth(i, columnWidths[i]);
        }

        // 创建单元格样式（带边框 + 自动换行）
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setWrapText(true); // 自动换行

        // 创建表头
        Row headerRow = sheet.createRow(0);
        String[] headers = {"num.", "Area", "Country", "Problem Type", "Submit Time", "Gender", "Full Name", "Nationality", "Phone", "E-mail", "Questions", "VIN"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(cellStyle);
        }

        // 填充数据
        int rowNum = 1;
        for (Map<String, String> dataMap : dataListMap) {
            Row row = sheet.createRow(rowNum);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(rowNum);
            cell0.setCellStyle(cellStyle);

            String[] keys = {"area", "countryName", "msgType", "createTime", "gender", "name", "country", "telephone", "email", "msg", "vin"};
            for (int i = 1; i < keys.length + 1; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(dataMap.getOrDefault(keys[i - 1], ""));
                cell.setCellStyle(cellStyle);
            }
            rowNum++;
        }

        // 将 Excel 写入内存
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            workbook.write(out);
            workbook.close();
            return new ByteArrayResource(out.toByteArray()); // 返回 ByteArrayResource 作为 InputStreamSource
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
