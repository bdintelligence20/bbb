package com.ruoyi.website.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.BannerLang;
import com.ruoyi.website.domain.CategoryLang;
import com.ruoyi.website.domain.WebSiteCategory;
import com.ruoyi.website.domain.WebsiteBanner;
import com.ruoyi.website.domain.vo.CategoryLangVO;
import com.ruoyi.website.domain.vo.CategoryVO;
import com.ruoyi.website.domain.vo.WebSiteBannerVO;
import com.ruoyi.website.domain.vo.WebSiteCategoryPO;
import com.ruoyi.website.mapper.CategoryLangMapper;
import com.ruoyi.website.mapper.WebSiteCategoryMapper;
import com.ruoyi.website.service.IWebSiteCategoryService;
import org.apache.poi.util.ArrayUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 导航Service业务层处理
 * 
 * @author thh
 * @date 2022-06-01
 */
@Service
public class WebSiteCategoryServiceImpl implements IWebSiteCategoryService
{
    @Autowired
    private WebSiteCategoryMapper webSiteCategoryMapper;


    @Autowired
    private CategoryLangMapper categoryLangMapper;

    /**
     * 查询导航
     * 
     * @param id 导航主键
     * @return 导航
     */
    @Override
    public CategoryVO selectCategoryById(Long id) {

        List<CategoryLang> categoryLangs = new ArrayList<>();
        WebSiteCategory webSiteCategory = webSiteCategoryMapper.selectCategoryById(id);
        CategoryLang categoryLang = new CategoryLang();
        BeanUtil.copyProperties(webSiteCategory, categoryLang);
        categoryLang.setLanguage("en");
        categoryLang.setId(webSiteCategory.getId());
        categoryLangs.add(categoryLang);

        CategoryLang lang = new CategoryLang();
        lang.setCategoryId(id);
        categoryLangs.addAll(categoryLangMapper.selectCategoryLangList(lang));

        return toObject(categoryLangs);
    }

    /**
     * 查询导航列表
     * 
     * @param webSiteCategory 导航
     * @return 导航
     */
    @Override
    public List<WebSiteCategory> selectCategoryList(WebSiteCategory webSiteCategory) {
        return webSiteCategoryMapper.selectCategoryList(webSiteCategory);
    }

    /**
     * 新增导航
     * 
     * @param categoryVO 导航
     * @return 结果
     */
    @Override
    public int insertCategory(CategoryVO categoryVO) {
        if (ObjectUtil.isEmpty(categoryVO)){
            throw new ServiceException("参数不能为空！");
        }
        //适应前端结构增加变形
        List<CategoryLang> categoryLangs = toList(categoryVO);

        List<CategoryLang> categorys = new ArrayList<>();
        WebSiteCategory webSiteCategory = new WebSiteCategory();
        for (CategoryLang categoryLang : categoryLangs) {
            categoryLang.setCreateTime(DateUtils.getNowDate());
            if ("en".equalsIgnoreCase(categoryLang.getLanguage())){
                BeanUtils.copyProperties(categoryLang, webSiteCategory);
            }else {
                CategoryLang lang = new CategoryLang();
                BeanUtils.copyProperties(categoryLang, lang);
                categorys.add(lang);
            }
        }
        int i = webSiteCategoryMapper.insertCategory(webSiteCategory);
        for (CategoryLang lang : categorys) {
            lang.setCategoryId(webSiteCategory.getId());
        }
        categoryLangMapper.batchInsert(categorys);
        return i;
    }

    /**
     * 修改导航
     * 
     * @param categoryVO 导航
     * @return 结果
     */
    @Override
    public int updateCategory(CategoryVO categoryVO) {
        if (ObjectUtil.isEmpty(categoryVO)){
            throw new ServiceException("参数不能为空");
        }
        List<CategoryLang> categoryLangs = toList(categoryVO);
        int i = 0;
        for (CategoryLang categoryLang : categoryLangs) {
            categoryLang.setUpdateTime(DateUtils.getNowDate());
            if ("en".equalsIgnoreCase(categoryLang.getLanguage())){
                WebSiteCategory webSiteCategory = new WebSiteCategory();
                BeanUtils.copyProperties(categoryLang, webSiteCategory);
                i = webSiteCategoryMapper.updateCategory(webSiteCategory);
            }else {
                categoryLangMapper.updateCategoryLang(categoryLang);
            }
        }

        return i;
    }

    /**
     * 批量删除导航
     * 
     * @param ids 需要删除的导航主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByIds(List<Long> ids) {
        if (CollUtil.isEmpty(ids)){
            throw new ServiceException("参数不能为空!");
        }
        int i = webSiteCategoryMapper.deleteCategoryByIds(ids);
        categoryLangMapper.deleteByCategoryIds(ids);
        return i;
    }

    /**
     * 删除导航信息
     * 
     * @param id 导航主键
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Long id) {
        List<WebSiteCategory> categories = webSiteCategoryMapper.selectByParentId(id);
        if (CollUtil.isNotEmpty(categories)){
            throw new ServiceException("请先删除子导航");
        }
        return webSiteCategoryMapper.deleteCategoryById(id);
    }

    @Override
    public List<WebSiteCategoryPO> buildCategoryTree(List<WebSiteCategory> categories) {
        List<WebSiteCategoryPO> webSiteCategoryPOS = new ArrayList<>();
        if (CollUtil.isEmpty(categories)){
            return webSiteCategoryPOS;
        }
        for (WebSiteCategory category : categories) {
            WebSiteCategoryPO categoryPO = new WebSiteCategoryPO();
            BeanUtil.copyProperties(category, categoryPO);
            webSiteCategoryPOS.add(categoryPO);
        }
        return buildTree(webSiteCategoryPOS);
    }

    @Override
    public List<WebSiteCategory> getIndexCategoryList(String lang, Integer topOrBottom) {
        if (StrUtil.isEmpty(lang)){
            throw new ServiceException("参数不能为空");
        }
        List<WebSiteCategory> webSiteCategories = new ArrayList<>();
        if ("en".equalsIgnoreCase(lang)){
            WebSiteCategory webSiteCategory = new WebSiteCategory();
            webSiteCategory.setVisible("0");
            webSiteCategory.setStatus("0");
            webSiteCategory.setTopOrBottom(topOrBottom);
            webSiteCategories.addAll(webSiteCategoryMapper.selectCategoryList(webSiteCategory));
        }else {
            CategoryLang categoryLang = new CategoryLang();
            categoryLang.setLanguage(lang);
            categoryLang.setVisible("0");
            categoryLang.setStatus("0");
            categoryLang.setTopOrBottom(topOrBottom);
            List<CategoryLang> categoryLangs = categoryLangMapper.selectCategoryLangList(categoryLang);
            if (CollUtil.isEmpty(categoryLangs)){
                return webSiteCategories;
            }
            for (CategoryLang calng : categoryLangs) {
                WebSiteCategory webSiteCategory = new WebSiteCategory();
                BeanUtils.copyProperties(calng, webSiteCategory);
                webSiteCategory.setId(calng.getCategoryId());
                webSiteCategories.add(webSiteCategory);
            }
        }
        return webSiteCategories;
    }

    public List<WebSiteCategoryPO> buildTree(List<WebSiteCategoryPO> categories)
    {
        List<WebSiteCategoryPO> returnList = new ArrayList<>();
        List<Long> tempList = new ArrayList<Long>();
        for (WebSiteCategoryPO dept : categories) {
            tempList.add(dept.getId());
        }

        for (Iterator<WebSiteCategoryPO> iterator = categories.iterator(); iterator.hasNext();) {
            WebSiteCategoryPO categoryPO = iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(categoryPO.getParentId())) {
                recursionFn(categories, categoryPO);
                returnList.add(categoryPO);
            }
        }

        if (returnList.isEmpty()) {
            returnList = categories;
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<WebSiteCategoryPO> list, WebSiteCategoryPO t)
    {
        // 得到子节点列表
        List<WebSiteCategoryPO> childList = getChildList(list, t);
        t.setChildren(childList);
        for (WebSiteCategoryPO tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<WebSiteCategoryPO> list, WebSiteCategoryPO t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    /**
     * 得到子节点列表
     */
    private List<WebSiteCategoryPO> getChildList(List<WebSiteCategoryPO> list, WebSiteCategoryPO t) {
        List<WebSiteCategoryPO> tlist = new ArrayList<>();
        Iterator<WebSiteCategoryPO> it = list.iterator();
        while (it.hasNext()) {
            WebSiteCategoryPO n =  it.next();
            if (n.getParentId().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    //对象转数组
    private List<CategoryLang> toList(CategoryVO categoryVO){
        List<CategoryLang> categoryLangs = new ArrayList<>();
        for (CategoryLangVO lang : categoryVO.getCategoryLangs()) {
            CategoryLang categoryLang = new CategoryLang();
            BeanUtils.copyProperties(categoryVO, categoryLang);
            categoryLang.setLanguage(lang.getLanguage());
            categoryLang.setName(lang.getName());
            categoryLang.setTopOrBottom(categoryVO.getTopOrBottom());
            if (!"en".equalsIgnoreCase(lang.getLanguage())){
                categoryLang.setId(lang.getId());
            }
            categoryLangs.add(categoryLang);
        }
        return categoryLangs;
    }

    //数组转对象
    private CategoryVO toObject(List<CategoryLang> langs){
        CategoryVO categoryVO = new CategoryVO();
        List<CategoryLangVO> categoryLangs = new ArrayList<>();
        for (CategoryLang lang : langs) {
            if ("en".equalsIgnoreCase(lang.getLanguage())){
                categoryVO.setId(lang.getId());
                BeanUtils.copyProperties(lang, categoryVO);
                categoryVO.setTopOrBottom(lang.getTopOrBottom());
            }
            CategoryLangVO categoryLangVO = new CategoryLangVO();
            BeanUtils.copyProperties(lang, categoryLangVO);
            categoryLangs.add(categoryLangVO);

        }
        categoryVO.setCategoryLangs(categoryLangs);
        return categoryVO;
    }
}
