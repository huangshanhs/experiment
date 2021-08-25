package com.hsp.experiment.each.service.impl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hsp.experiment.each.dao.StudentExperDao;
import com.hsp.experiment.each.entity.StudentExperEntity;
import com.hsp.experiment.each.service.StudentExperService;


@Service("studentExperService")
public class StudentExperServiceImpl extends ServiceImpl<StudentExperDao, StudentExperEntity> implements StudentExperService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StudentExperEntity> page = this.page(
                new Query<StudentExperEntity>().getPage(params),
                new QueryWrapper<StudentExperEntity>()
        );

        return new PageUtils(page);
    }

}