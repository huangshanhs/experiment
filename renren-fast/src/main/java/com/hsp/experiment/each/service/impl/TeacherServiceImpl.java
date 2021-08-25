package com.hsp.experiment.each.service.impl;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hsp.experiment.each.dao.TeacherDao;
import com.hsp.experiment.each.entity.TeacherEntity;
import com.hsp.experiment.each.service.TeacherService;


@Service("teacherService")
public class TeacherServiceImpl extends ServiceImpl<TeacherDao, TeacherEntity> implements TeacherService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String teacherName = (String) params.get("teacherName");
        IPage<TeacherEntity> page = this.page(
                new Query<TeacherEntity>().getPage(params),
                new QueryWrapper<TeacherEntity>().like(StringUtils.isNotBlank(teacherName), "teacher_name", teacherName)
        );

        return new PageUtils(page);
    }

}