package com.hsp.experiment.each.service.impl;

import com.hsp.experiment.each.entity.StudentEntity;
import com.hsp.experiment.each.entity.TeacherEntity;
import com.hsp.experiment.each.service.StudentService;
import com.hsp.experiment.each.service.TeacherService;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hsp.experiment.each.dao.FacilityDao;
import com.hsp.experiment.each.entity.FacilityEntity;
import com.hsp.experiment.each.service.FacilityService;


@Service("facilityService")
public class FacilityServiceImpl extends ServiceImpl<FacilityDao, FacilityEntity> implements FacilityService {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String facilityName = (String) params.get("facilityName");
        IPage<FacilityEntity> page = this.page(
                new Query<FacilityEntity>().getPage(params),
                new QueryWrapper<FacilityEntity>().like(StringUtils.isNotBlank(facilityName), "facility_name", facilityName)
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils getCheckList(Map<String, Object> params) {
        String facilityStatus = (String) params.get("facilityStatus");
        String username = (String) params.get("username");
        QueryWrapper<TeacherEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("id", username);
        TeacherEntity teacherInfo = teacherService.getOne(wrapper);
        if (teacherInfo != null) {
            String teacherName = teacherInfo.getTeacherName();
            IPage<FacilityEntity> page = this.page(
                    new Query<FacilityEntity>().getPage(params),
                    new QueryWrapper<FacilityEntity>().eq(StringUtils.isNotBlank(facilityStatus), "facility_status", facilityStatus)
                            .eq(StringUtils.isNotBlank(teacherName), "teacher_name", teacherName)
            );
            return new PageUtils(page);
        } else {
            IPage<FacilityEntity> page = this.page(
                    new Query<FacilityEntity>().getPage(params),
                    new QueryWrapper<FacilityEntity>().eq(StringUtils.isNotBlank(facilityStatus), "facility_status", facilityStatus)
            );
            return new PageUtils(page);
        }
    }

    @Override
    public PageUtils getRequestList(Map<String, Object> params) {
        String facilityStatus = (String) params.get("facilityStatus");
        String username = (String) params.get("username");
        QueryWrapper<TeacherEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("id", username);
        TeacherEntity teacherInfo = teacherService.getOne(wrapper);
        if (teacherInfo != null) {
            String teacherName = teacherInfo.getTeacherName();
            IPage<FacilityEntity> page = this.page(
                    new Query<FacilityEntity>().getPage(params),
                    new QueryWrapper<FacilityEntity>()
                            .eq(StringUtils.isNotBlank(facilityStatus), "facility_status", facilityStatus)
                            .eq(StringUtils.isNotBlank(username), "username", username)
                            .or()
                            .eq(StringUtils.isNotBlank(teacherName), "teacher_name", teacherName)
                            .eq(StringUtils.isNotBlank(facilityStatus), "facility_status", facilityStatus)
            );
            return new PageUtils(page);
        } else {
            IPage<FacilityEntity> page = this.page(
                    new Query<FacilityEntity>().getPage(params),
                    new QueryWrapper<FacilityEntity>()
                            .eq(StringUtils.isNotBlank(facilityStatus), "facility_status", facilityStatus)
                            .eq(StringUtils.isNotBlank(username), "username", username)
            );
            return new PageUtils(page);
        }
    }

}