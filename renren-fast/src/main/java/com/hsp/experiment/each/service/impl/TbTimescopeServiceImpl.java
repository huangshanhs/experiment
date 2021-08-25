package com.hsp.experiment.each.service.impl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hsp.experiment.each.dao.TbTimescopeDao;
import com.hsp.experiment.each.entity.TbTimescopeEntity;
import com.hsp.experiment.each.service.TbTimescopeService;


@Service("tbTimescopeService")
public class TbTimescopeServiceImpl extends ServiceImpl<TbTimescopeDao, TbTimescopeEntity> implements TbTimescopeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TbTimescopeEntity> page = this.page(
                new Query<TbTimescopeEntity>().getPage(params),
                new QueryWrapper<TbTimescopeEntity>()
        );
        return new PageUtils(page);
    }

}