package com.ruoyi.material.service.impl;

import com.ruoyi.material.mapper.MaterialNewLabelMapper;
import com.ruoyi.material.mapper.MaterialTypeNewLabelMapper;
import com.ruoyi.material.service.IMaterialNewLabelServiceImpl;
import com.ruoyi.website.domain.vo.MaterialNewLabelVO;
import com.ruoyi.website.domain.vo.MaterialTypeNewLabelVO;
import com.ruoyi.website.domain.vo.MaterialVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户和new标关联Service业务层处理
 *
 * @author thh
 * @date 2023-08-03
 */
@Service
public class MaterialNewLabelServiceImpl implements IMaterialNewLabelServiceImpl {
    @Autowired
    private MaterialNewLabelMapper materialNewLabelMapper;

    @Autowired
    private MaterialTypeNewLabelMapper materialTypeNewLabelMapper;

    @Override
    public List<MaterialNewLabelVO> selectNewLabel(MaterialVO materialVo) {
        return materialNewLabelMapper.selectNewLabel(materialVo);
    }

    @Override
    public int insertNewLabelVO(MaterialNewLabelVO materialNewLabelVO) {
        if (materialNewLabelVO.getNewLabelFlag().equals("0")) {
            return materialNewLabelMapper.insertNewLabelVO(materialNewLabelVO);
        }
        return 1;
    }

    @Override
    public List<MaterialNewLabelVO> selectTypeNewLabel(MaterialNewLabelVO materialNewLabelVO) {
        return materialNewLabelMapper.selectTypeNewLabel(materialNewLabelVO);
    }

    @Override
    public Map<String, MaterialTypeNewLabelVO> selectTypeNewLabelS(MaterialNewLabelVO materialNewLabelVO) {
        //查询当前用户每个分类下的车型的浏览记录
        List<MaterialNewLabelVO> typeNewLabelList = materialTypeNewLabelMapper.selectTypeNewLabel(materialNewLabelVO);
        //查询当前用户每个素材所属的分类(车型以及类型)未浏览的记录
        List<MaterialNewLabelVO> materialNewLabelVOList = materialNewLabelMapper.selectMaterialNewLabel(materialNewLabelVO);
        //遍历素材浏览记录
        for (MaterialNewLabelVO materialNewLabel : materialNewLabelVOList) {
            //没有flag,设置为未浏览
            if (materialNewLabel.getNewLabelFlag() == null) {
                materialNewLabel.setNewLabelFlag("0");
            }
            //如果这个素材类型的车型为0
            if (materialNewLabel.getModel().equals("0") || materialNewLabel.getModel() == null || materialNewLabel.getModel().equals("") || materialNewLabel.getModel().isEmpty() || materialNewLabel.getModel().length() == 0) {
                //就去寻找类型的浏览记录,有浏览记录就可以消除new
                for (MaterialNewLabelVO typeNewLabel : typeNewLabelList) {
                    if (typeNewLabel.getType().equals(materialNewLabel.getType())) {
                        materialNewLabel.setNewLabelFlag("1");
                        break;
                    }
                }
            }
            //遍历分类的车型浏览记录
            for (MaterialNewLabelVO materialNewLabelVO1 : typeNewLabelList) {
                //如果类型相同且车型相同
                if (materialNewLabelVO1.getType().equals(materialNewLabel.getType()) && materialNewLabelVO1.getModel().equals(materialNewLabel.getModel())) {
                    if (materialNewLabel.getNewLabelFlag() == null) {
                        materialNewLabel.setNewLabelFlag("0");
                    } else {
                        //增加new标
                        materialNewLabel.setNewLabelFlag("1");
                    }
                    break;
                }
            }
        }
        /**
         * 当所有车型都没有设置new标时，外层类型也没有new
         */
        Map<String, MaterialTypeNewLabelVO> classification = Classification(materialNewLabelVOList);
        /**
         * 不包含直接赋予无new标的标志
         */
        if (!classification.containsKey("Event")) {
            MaterialTypeNewLabelVO materialTypeNewLabelVO = new MaterialTypeNewLabelVO();
            materialTypeNewLabelVO.setList(new ArrayList<>());
            materialTypeNewLabelVO.setNewLabelFlag("1");
            classification.put("Event", materialTypeNewLabelVO);
        }
        return classification;
    }

    @Override
    public int insertTypeNewLabelVO(MaterialNewLabelVO materialNewLabelVO) {
        if (materialNewLabelVO.getNewLabelFlag().equals("0")) {
            return materialTypeNewLabelMapper.insertPyteNewLabelVO(materialNewLabelVO);
        }
        return 1;
    }

    public Map<String, MaterialTypeNewLabelVO> Classification(List<MaterialNewLabelVO> materialNewLabelVOS) {
        //根据类型分类
        Map<String, List<MaterialNewLabelVO>> map = materialNewLabelVOS.stream().collect(Collectors.groupingBy(MaterialNewLabelVO::getType));
        Map<String, MaterialTypeNewLabelVO> materialTypeNewLabelVOListMap = new HashMap<>();
        for (Map.Entry<String, List<MaterialNewLabelVO>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<MaterialNewLabelVO> list = entry.getValue();
            //判断getNewLabelFlag返回值是否都等于"1"
            boolean result = list.stream().allMatch(element -> element.getNewLabelFlag().equals("1"));
            MaterialTypeNewLabelVO materialTypeNewLabelVO = new MaterialTypeNewLabelVO();
            if (result) {
                materialTypeNewLabelVO.setNewLabelFlag("1");
            } else {
                materialTypeNewLabelVO.setNewLabelFlag("0");
            }
            materialTypeNewLabelVO.setList(list);
            materialTypeNewLabelVOListMap.put(key, materialTypeNewLabelVO);
        }
        return materialTypeNewLabelVOListMap;

    }
}
