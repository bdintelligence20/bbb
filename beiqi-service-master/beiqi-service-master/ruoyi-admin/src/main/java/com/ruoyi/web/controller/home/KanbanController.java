package com.ruoyi.web.controller.home;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.testdrive.domain.Area;
import com.ruoyi.home.testdrive.service.AreaService;
import com.xkcoding.http.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import com.ruoyi.home.testdrive.service.TestDriveCommitService;

/**
 * 数据看板
 *
 *
 */
@RestController
@RequestMapping("kanban")
@Validated
@AllArgsConstructor
@Api(value = "KanbanController", tags = "看板")
public class KanbanController {

    public static final String baseDomain = "http://hongqibam.hongqi-auto.com";
    private TestDriveCommitService testDriveCommitService;



    @GetMapping("/top")
    @ApiOperation("看板Top排行")
    public AjaxResult top(@RequestParam Map<String, String> paramsMap) {
        paramsMap.put("app", "baicglobal");
        String content = HttpUtil.get(baseDomain+"/public/index.php?s=top/index/", paramsMap, true).getBody();
        Object data = JSONUtil.parseObj(content).get("data");
        return AjaxResult.success(data);
    }

    @GetMapping("/topExport")
    @ApiOperation("看板Top排行导出")
    public void topExport(HttpServletResponse response, @RequestParam Map<String, String> paramsMap) throws IOException {
        paramsMap.put("export", "1");
        paramsMap.put("app", "baicglobal");
        String content = HttpUtil.get(baseDomain+"/public/index.php?s=top/index/", paramsMap, true).getBody();
        JSONObject data = JSONUtil.parseObj(JSONUtil.toJsonStr(JSONUtil.parseObj(content).get("data")));
        //指标
        String dimensionName = "国家";
        if(paramsMap.containsKey("dimension")){
            if(paramsMap.get("dimension").equals("pageTitle")){
                dimensionName = "页面";
            }else if(paramsMap.get("dimension").equals("deviceCategory")){
                dimensionName = "设备类型";
            }else if(paramsMap.get("dimension").equals("sessionSource")){
                dimensionName = "来源";
            }else{
                dimensionName = "国家";
            }
        }

        response.setContentType("application/csv");
        String fileName = new String(("排行统计.csv").getBytes("utf-8"),"ISO-8859-1");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        response.setCharacterEncoding("UTF-8");
        //BufferedWriter writer = new BufferedWriter(response.getWriter());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        // 需要写入 utf8bom 头否则会出现中文乱码
        String bom = new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
        writer.write(bom);
        writer.write(String.format("%s,%s,%s", "日期", dimensionName, "浏览量"));
        writer.newLine();


        List<String> keyList = new ArrayList<String>(data.keySet());
        Collections.sort(keyList);


        for (String key : keyList) {
            String tmp = String.format("%s,%s", key, data.get(key));
            writer.write(tmp);
            writer.newLine();
        }
        writer.flush();
        writer.close();

    }

    @GetMapping("/trend")
    @ApiOperation("看板趋势")
    public AjaxResult trend(@RequestParam Map<String, String> paramsMap) {
        paramsMap.put("app", "baicglobal");
        String content = HttpUtil.get(baseDomain+"/public/index.php?s=trend/index/", paramsMap, true).getBody();
        Object data = JSONUtil.parseObj(content).get("data");
        return AjaxResult.success(data);
    }

    @GetMapping("/user")
    @ApiOperation("看板用户统计")
    public AjaxResult user(@RequestParam Map<String, String> paramsMap) {
        String content = HttpUtil.get(baseDomain+"/public/index.php?s=user/index/", paramsMap, true).getBody();
        Object data = JSONUtil.parseObj(content).get("data");
        return AjaxResult.success(data);
    }

    @GetMapping("/userExport")
    @ApiOperation("看板用户统计导出")
    public void userExport(HttpServletResponse response, @RequestParam Map<String, String> paramsMap) throws IOException {
        paramsMap.put("export", "1");
        paramsMap.put("app", "baicglobal");
        String content = HttpUtil.get(baseDomain+"/public/index.php?s=user/index/", paramsMap, true).getBody();
        JSONObject data = JSONUtil.parseObj(JSONUtil.toJsonStr(JSONUtil.parseObj(content).get("data")));
        response.setContentType("application/csv");
        String fileName = new String(("用户统计.csv").getBytes("utf-8"),"ISO-8859-1");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        response.setCharacterEncoding("UTF-8");
        //BufferedWriter writer = new BufferedWriter(response.getWriter());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        // 需要写入 utf8bom 头否则会出现中文乱码
        String bom = new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
        writer.write(bom);
        writer.write(String.format("%s,%s,%s,%s,%s,%s,%s", "日期", "用户数", "新用户数", "平均访问时长(秒)", "跳出率","网页浏览量","平均访问页数"));
        writer.newLine();

        List<String> keyList = new ArrayList<String>(data.keySet());
        Collections.sort(keyList);

        for (String key : keyList) {
            JSONObject value = JSONUtil.parseObj(JSONUtil.toJsonStr(data.get(key)));
            String tmp = String.format("%s,%s,%s,%s,%s,%s,%s", key.split(",")[0], value.get("activeUsers").toString(), value.get("newUsers").toString(), value.get("averageSessionDuration").toString(),value.get("bounceRate").toString(), value.get("pv").toString(), value.get("screenPageViewsPerUser").toString());
            writer.write(tmp);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    @GetMapping("/portrait")
    @ApiOperation("看板用户画像统计")
    public AjaxResult portrait(@RequestParam Map<String, String> paramsMap) {
        paramsMap.put("app", "baicglobal");
        String content = HttpUtil.get(baseDomain+"/public/index.php?s=user/portrait/", paramsMap, true).getBody();
        Object data = JSONUtil.parseObj(content).get("data");
        return AjaxResult.success(data);
    }

    @GetMapping("/portraitExport")
    @ApiOperation("看板用户画像统计导出")
    public void portraitExport(HttpServletResponse response, @RequestParam Map<String, String> paramsMap) throws IOException {
        paramsMap.put("export", "1");
        paramsMap.put("app", "baicglobal");
        String content = HttpUtil.get(baseDomain+"/public/index.php?s=user/portrait/", paramsMap, true).getBody();
        JSONObject data = JSONUtil.parseObj(JSONUtil.toJsonStr(JSONUtil.parseObj(content).get("data")));

        //指标
        String dimensionName = "年龄";
        if(paramsMap.containsKey("dimension")){
            if(paramsMap.get("dimension").equals("userAgeBracket")){
                dimensionName = "年龄";
            }else if(paramsMap.get("dimension").equals("userGender")){
                dimensionName = "性别";
            }else if(paramsMap.get("dimension").equals("brandingInterest")){
                dimensionName = "兴趣";
            }else{
                dimensionName = "年龄";
            }
        }

        response.setContentType("application/csv");
        String fileName = new String(("用户画像-"+dimensionName+".csv").getBytes("utf-8"),"ISO-8859-1");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        response.setCharacterEncoding("UTF-8");
        //BufferedWriter writer = new BufferedWriter(response.getWriter());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        // 需要写入 utf8bom 头否则会出现中文乱码
        String bom = new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
        writer.write(bom);
        writer.write(String.format("%s,%s,%s", "日期", dimensionName, "用户数"));
        writer.newLine();

        List<String> keyList = new ArrayList<String>(data.keySet());
        Collections.sort(keyList);


        for (String key : keyList) {
            String tmp = String.format("%s,%s", key, data.get(key));
            writer.write(tmp);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    @GetMapping("/testDrive")
    @ApiOperation("预约试驾服务咨询国家")
    public AjaxResult testDrive(@RequestParam(name = "days", defaultValue = "") String days) {
        String startDate, endDate;
        if(days.equals("today")){
            startDate = endDate = DateUtil.today();
        }else if(days.equals("yesterday")){
            startDate = endDate = DateUtil.yesterday().toString().substring(0,10);
        }else if(days.equals("7days")){
            startDate = DateUtil.lastWeek().toString().substring(0,10);
            endDate = DateUtil.today();
        }else if(days.equals("30days")){
            startDate =DateUtil.lastMonth().toString().substring(0,10);
            endDate = DateUtil.today();
        }else if(days.contains("$")){  //自定义日期
            String[] dateArr = days.split("$");
            startDate = dateArr[0];
            endDate = dateArr[1];
        }else{
            startDate = "2000-01-01";
            endDate = DateUtil.today();
        }
        return AjaxResult.success(testDriveCommitService.countryBy(startDate+" 00:00:00", endDate+" 23:59:59"));
    }

    @GetMapping("/testDriveExport")
    @ApiOperation("预约试驾服务咨询国家导出")
    public void testDriveExport(HttpServletResponse response, @RequestParam(name = "days", defaultValue = "") String days) throws IOException{
        String startDate, endDate;
        if(days.equals("today")){
            startDate = endDate = DateUtil.today();
        }else if(days.equals("yesterday")){
            startDate = endDate = DateUtil.yesterday().toString().substring(0,10);
        }else if(days.equals("7days")){
            startDate = DateUtil.lastWeek().toString().substring(0,10);
            endDate = DateUtil.today();
        }else if(days.equals("30days")){
            startDate =DateUtil.lastMonth().toString().substring(0,10);
            endDate = DateUtil.today();
        }else if(days.contains("$")){  //自定义日期
            String[] dateArr = days.split("$");
            startDate = dateArr[0];
            endDate = dateArr[1];
        }else{
            startDate = "2000-01-01";
            endDate = DateUtil.today();
        }
        List<Map<String, Object>> testDriveCommitList = testDriveCommitService.countryDateBy(startDate+" 00:00:00", endDate+" 23:59:59");
        String fileName = new String(("预约试驾国家.csv").getBytes("utf-8"),"ISO-8859-1");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        response.setCharacterEncoding("UTF-8");
        //BufferedWriter writer = new BufferedWriter(response.getWriter());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        // 需要写入 utf8bom 头否则会出现中文乱码
        String bom = new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
        writer.write(bom);
        writer.write(String.format("%s,%s,%s", "日期", "国家", "咨询量"));
        writer.newLine();

//        List<String> keyList = new ArrayList<String>(data.keySet());
//        Collections.sort(keyList);


        for (Map<String, Object> testDriveCommit: testDriveCommitList) {
            String tmp = String.format("%s,%s,%s", testDriveCommit.get("create_date"), testDriveCommit.get("area_name"), testDriveCommit.get("total"));
            writer.write(tmp);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }


}
