package com.ruoyi.web.controller.website;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.website.domain.vo.NewsSearchResultVO;
import com.ruoyi.website.service.INewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "新闻页")
@RestController
@RequestMapping("/website/news")
public class NewsController extends BaseController
{
    @Autowired
    private INewsService newsService;



    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "导航id", required = true),
            @ApiImplicitParam(name = "l", value = "语种", required = true),
            @ApiImplicitParam(name = "recommend", value = "是否推荐位(0:否 1:是)", required = true)
    })
    @ApiOperation("新闻列表，根据导航ID和语种查询新闻")
	@GetMapping("/nlist/{l}")
    public TableDataInfo getNewsList(@PathVariable("l") String l, @RequestParam("categoryId") Long categoryId,@RequestParam("recommend")Integer recommend){
        startPage();
        List<NewsSearchResultVO> list = newsService.getNewsList(l, categoryId, recommend);
        return getDataTable(list);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "新闻id", required = true),
            @ApiImplicitParam(name = "l", value = "语种", required = true)
    })
    @ApiOperation("新闻详情页")
    @GetMapping("/ndetail/{l}")
    public AjaxResult getNewsContent(@PathVariable("l") String l, @RequestParam("id") Long id){
        return  AjaxResult.success(newsService.getNewsDetail(l, id));
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "导航ID", required = true),
            @ApiImplicitParam(name = "l", value = "语种", required = true)
    })
    @ApiOperation("Overview OurVersion详情页")
    @GetMapping("/odetail/{l}")
    public AjaxResult getOurOrOverContent(@PathVariable("l") String l, @RequestParam("id") Long categoryId){
        return  AjaxResult.success(newsService.getOODetail(l, categoryId));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "l", value = "语种", required = true),
            @ApiImplicitParam(name = "recommend", value = "是否推荐位(0:否 1:是) 不传就查所有已发布得新闻")
    })
    @ApiOperation("查询推荐位新闻或全部已发布新闻")
    @GetMapping("/rlist/{l}")
    public AjaxResult getNewsListOrRecommed(@PathVariable("l") String l, Integer recommend){
        return AjaxResult.success(newsService.getNewsList(l, null, recommend));
    }
}
