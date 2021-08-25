package com.hsp.experiment.each.service.impl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hsp.experiment.each.dao.TbTimeDao;
import com.hsp.experiment.each.entity.TbTimeEntity;
import com.hsp.experiment.each.service.TbTimeService;


@Service("tbTimeService")
public class TbTimeServiceImpl extends ServiceImpl<TbTimeDao, TbTimeEntity> implements TbTimeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TbTimeEntity> page = this.page(
                new Query<TbTimeEntity>().getPage(params),
                new QueryWrapper<TbTimeEntity>()
        );

        return new PageUtils(page);
    }

}