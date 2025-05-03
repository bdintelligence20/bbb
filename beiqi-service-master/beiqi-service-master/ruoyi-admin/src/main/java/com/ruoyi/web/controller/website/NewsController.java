package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.website.domain.News;
import com.ruoyi.website.domain.vo.NewsLangVO;
import com.ruoyi.website.domain.vo.NewsVO;
import com.ruoyi.website.service.INewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新闻Controller
 * 
 * @author thh
 * @date 2022-06-01
 */
@Api(tags = "新闻")
@RestController
@RequestMapping("/system/news")
public class NewsController extends BaseController
{
    @Autowired
    private INewsService newsService;

    /**
     * 查询新闻列表
     */
    @ApiOperation("查询新闻列表")
    @PreAuthorize("@ss.hasPermi('system:news:list')")
    @GetMapping("/list")
    public TableDataInfo list(News news)
    {
        startPage();
        List<News> list = newsService.selectNewsList(news);
        return getDataTable(list);
    }


    /**
     * 获取新闻详细信息
     */
    @ApiOperation("获取新闻详细信息")
    @PreAuthorize("@ss.hasPermi('system:news:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(newsService.selectNewsById(id));
    }

    /**
     * 新增新闻
     */
    @ApiOperation("新增新闻")
    @PreAuthorize("@ss.hasPermi('system:news:add')")
    @Log(title = "新闻", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewsVO newsVO) {
        return AjaxResult.success(newsService.insertNews(newsVO));
    }

    /**
     * 修改新闻
     */
    @ApiOperation("修改新闻")
    @PreAuthorize("@ss.hasPermi('system:news:edit')")
    @Log(title = "新闻", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewsVO newsVO) {
        return toAjax(newsService.updateNews(newsVO));
    }

    /**
     * 删除新闻
     */
    @ApiOperation("删除新闻")
    @PreAuthorize("@ss.hasPermi('system:news:remove')")
    @Log(title = "新闻", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids)
    {
        return toAjax(newsService.deleteNewsByIds(ids));
    }
}
