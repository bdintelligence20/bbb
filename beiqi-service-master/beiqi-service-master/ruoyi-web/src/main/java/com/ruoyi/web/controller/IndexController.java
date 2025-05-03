package com.ruoyi.web.controller;


import com.ruoyi.blog.domain.Article;
import com.ruoyi.blog.domain.Tag;
import com.ruoyi.blog.service.IArticleService;
import com.ruoyi.blog.service.ITagService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.home.vehicle.service.VehicleClassService;
import com.ruoyi.home.vo.vehicle.VehicleClassSummaryHomeVo;
import com.ruoyi.media.service.IActorService;
import com.ruoyi.media.service.IMovieService;
import com.ruoyi.website.domain.WebSiteCategory;
import com.ruoyi.website.domain.WebsiteBanner;
import com.ruoyi.website.domain.WebsiteLink;
import com.ruoyi.website.domain.vo.NewsSearchResultVO;
import com.ruoyi.website.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

/**
 * @author thh
 * @Desc 首页controller
 */
@Api(tags = "首页")
@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController extends BaseController {


    @Autowired
    private IMovieService movieService;

    @Autowired
    private IActorService actorService;

    @Autowired
    private IWebsiteBannerService websiteBannerService;

    @Autowired
    private IWebsiteLinkService websiteLinkService;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ITagService tagService;

    @Autowired
    private ISearchLogService searchLogService;

    @Autowired
    private IWebSiteCategoryService categoryService;

    @Autowired
    private INewsService newsService;

    @Autowired
    private IIndexShowLocationService iIndexShowLocationService;
    @Autowired
    private INewsIndexShowLocationService inewsIndexShowLocationService;
    @Autowired
    private VehicleClassService vehicleClassService;
    @Autowired
    private LangService langService;

    @GetMapping("/getHotActorList")
    public AjaxResult getHotActorList() {
        //查询前10的热门演员
        return AjaxResult.success(actorService.selectHotActorList(10));
    }

    @GetMapping("/getHotMovieList")
    public AjaxResult getHotMovieList() {
        //查询前12的热门电影
        return AjaxResult.success(movieService.selectHotMovieList(10));
    }

    //查询首页轮播
    @ApiImplicitParams({
            @ApiImplicitParam(name = "l", value = "语种(path传参/{l})", required = true),
            @ApiImplicitParam(name = "type", value = "banner类型(0: 首页 1: 新闻页 2: 其它 3: join us)", required = true)
    })
    @ApiOperation("首页banner查询")
    @GetMapping("/getAllBannerList/{l}")
    public AjaxResult getAllBannerList(@PathVariable("l") String l, @RequestParam("type") Integer type) {
        List<WebsiteBanner> list = websiteBannerService.selectIndexBanner(l, type);
        return AjaxResult.success(list);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "l", value = "语种(path传参/{l})", required = true),
            @ApiImplicitParam(name = "topOrBottom", value = "显示位置(0 顶部 1 底部)", required = true)})
    @ApiOperation("获取导航下拉树列表")
    @GetMapping("/tree/{l}")
    public AjaxResult treeselect(@PathVariable("l") String l, @RequestParam("topOrBottom") Integer topOrBottom) {
        List<WebSiteCategory> list = categoryService.getIndexCategoryList(l, topOrBottom);
        return AjaxResult.success(categoryService.buildCategoryTree(list));
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "l", value = "语种(path传参/{l})", required = true)})
    @ApiOperation("获取首页展示位")
    @GetMapping("/show/{l}")
    public AjaxResult showLocation(@PathVariable("l") String l) {
        return AjaxResult.success(iIndexShowLocationService.getIndexShowLocationList(l));
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "l", value = "语种(path传参/{l})", required = true)})
    @ApiOperation("获取首页新闻展示位")
    @GetMapping("/newsShow/{l}")
    public AjaxResult newsShowLocation(@PathVariable("l") String l) {
        return AjaxResult.success(inewsIndexShowLocationService.getIndexShowLocationList(l));
    }

    @GetMapping("/getLinkList")
    public AjaxResult getLinkList() {
        List<WebsiteLink> list = websiteLinkService.selectWebsiteLinkList(null);
        return AjaxResult.success(list);
    }


    @GetMapping("/getHotArticleList")
    public AjaxResult getHotArticleList() {
        log.info("门户获取首页排行博客");
        List<Article> list = articleService.selectHotArticleList(10);
        return AjaxResult.success(list);
    }

    /**
     * 查询最新的博客文章列表
     */
    @GetMapping("/getNewArticleList")
    public TableDataInfo getNewArticleList(Article article) {
        log.info("门户获取最新的博客文章列表");
        startPage();
        List<Article> list = articleService.selectNewArticleList();
        return getDataTable(list);
    }


    @GetMapping("/getHotTagList")
    public AjaxResult getHotTagList() {
        log.info("门户获取最热标签");
        List<Tag> list = tagService.selectHotTagList(10);
        return AjaxResult.success(list);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "l", value = "语种(path传参/{l})", required = true),
            @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = true)
    })
    @ApiOperation("搜索接口（新闻）")
    @GetMapping("/search/{l}")
    public TableDataInfo search(@PathVariable("l") String l, @RequestParam("keyword") String keyword) {
        if (StringUtils.isBlank(keyword)) {
            error("请输入关键字！");
        }
        searchLogService.insertSearchLog(keyword);
        startPage();
        List<NewsSearchResultVO> list = newsService.search(l, keyword);
        return getDataTable(list);
    }

    @GetMapping("class/home/{lang}")
    @ApiOperation("获取对应语言的车辆型号列表（首页")
    public AjaxResultT<List<VehicleClassSummaryHomeVo>> classList(@PathVariable String lang) {
        boolean isAllowedLangCode = langService.isAllowedLangCode(lang);
        if (!isAllowedLangCode) return AjaxResultT.error("不被允许的语言类型：" + lang);
        List<VehicleClassSummaryHomeVo> vehicleClassSummaryHomeVos = vehicleClassService.listClass(lang, null,false);
        vehicleClassSummaryHomeVos.sort(Comparator.comparingInt(VehicleClassSummaryHomeVo::getSort));
        return AjaxResultT.success(vehicleClassSummaryHomeVos);
    }
}
