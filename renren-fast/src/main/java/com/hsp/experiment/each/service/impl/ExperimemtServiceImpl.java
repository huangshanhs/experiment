package com.hsp.experiment.each.service.impl;


import com.hsp.experiment.each.entity.FacilityEntity;
import com.hsp.experiment.each.entity.TbTimeEntity;
import com.hsp.experiment.each.entity.TbTimescopeEntity;
import com.hsp.experiment.each.service.FacilityService;
import com.hsp.experiment.each.service.TbTimeService;
import com.hsp.experiment.each.service.TbTimescopeService;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hsp.experiment.each.dao.ExperimemtDao;
import com.hsp.experiment.each.entity.ExperimemtEntity;
import com.hsp.experiment.each.service.ExperimemtService;
import sun.misc.REException;


@Service("experimemtService")
public class ExperimemtServiceImpl extends ServiceImpl<ExperimemtDao, ExperimemtEntity> implements ExperimemtService {

    @Autowired
    private FacilityService facilityService;
    @Autowired
    private TbTimescopeService timescopeService;
    @Autowired
    private TbTimeService timeService;
    @Autowired
    private ExperimemtService experimemtService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String experimentName = (String) params.get("experimentName");
        String content = (String) params.get("content");
        String facilityName = (String) params.get("facilityName");
        String teacherId = (String) params.get("teacherId");


        IPage<ExperimemtEntity> page = this.page(
                new Query<ExperimemtEntity>().getPage(params),
                new QueryWrapper<ExperimemtEntity>()
                        .like(StringUtils.isNotBlank(experimentName), "experiment_name", experimentName)
                        .like(StringUtils.isNotBlank(content), "content", content)
                        .like(StringUtils.isNotBlank(facilityName), "facility_name", facilityName)
                        .like(StringUtils.isNotBlank(teacherId), "teacher_id", teacherId)
        );

        return new PageUtils(page);
    }

    @Override
    public List<FacilityEntity> queryNameById(String id) {
        ExperimemtEntity entity = baseMapper.selectById(id);
        String name = entity.getFacilityName();
        QueryWrapper<FacilityEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("facility_name", name);
        List<FacilityEntity> list = facilityService.list(wrapper);
        return list;

    }

    @Override
    public boolean isTimePast(ExperimemtEntity experimemtEntity) {
        Date experimentDate = experimemtEntity.getExperimentDate();
        int time = Integer.parseInt(experimemtEntity.getExperimentTime().split("-")[1].split(":")[0]);
        Date beginTime = null;
        Date endTime = null;
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(experimentDate);
        calendar1.add(Calendar.HOUR_OF_DAY, time - 2);
        beginTime = calendar1.getTime();
        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTime(experimentDate);
        calendar2.add(Calendar.HOUR_OF_DAY, time);
        endTime = calendar2.getTime();
        if (new Date().before(beginTime) || new Date().after(endTime)) {
            return true;
        }
        return false;
    }

    @Override
    public void saveExperimentInfo(ExperimemtEntity experimemt) {
        String experimentScope = experimemt.getExperimentScope();
        QueryWrapper<TbTimescopeEntity> timeWrapper = new QueryWrapper<>();
        timeWrapper.eq("id", experimentScope);
        TbTimescopeEntity timeScope = timescopeService.getOne(timeWrapper);
        Date start = timeScope.getTimeStart();
        List<Date> dateList = new ArrayList<>();
        List<TbTimeEntity> tbTime = timeService.list();
        while (!start.after(timeScope.getTimeEnd())) {
            dateList.add(start);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(start);
            calendar.add(calendar.DATE, 1);
            start = calendar.getTime();
        }
        for (int i = 0; i < dateList.size(); i++) {
            Date date = dateList.get(i);
            List<ExperimemtEntity> experimentList = experimemtService
                    .list()
                    .stream()
                    .filter(e -> e.getExperimentDate().equals(date))
                    .collect(Collectors.toList());
            if (experimentList.isEmpty()) {
                experimemt.setExperimentDate(date);
                experimemt.setExperimentTime(tbTime.get(0).getSegment());
            } else {
                for (int m = 0; m < tbTime.size(); m++) {
                    List<String> experimentTeacherList = new ArrayList<>();
                    for (int n = 0; n < experimentList.size(); n++) {
                        if (tbTime.get(m).getSegment().equals(experimentList.get(n).getExperimentTime())) {
                            String teacher = experimentList.get(n).getTeacherId();
                            experimentTeacherList.add(teacher);
                        }
                    }
                    if (!experimentTeacherList.contains(experimemt.getTeacherId())) {
                        experimemt.setExperimentDate(date);
                        experimemt.setExperimentTime(tbTime.get(m).getSegment());
                    }
                }
            }
        }
        if (experimemt.getExperimentTime() == null || experimemt.getExperimentDate() == null) {
            throw new RRException("该范围实验已满，请重新选择实验范围", 2001);
        }
        experimemtService.save(experimemt);
    }

    @Override
    public void startExperiment(ExperimemtEntity experimemt) {
        ExperimemtEntity entity = experimemtService.getById(experimemt);
        String facilityName = entity.getFacilityName();
        QueryWrapper<FacilityEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("facility_name", facilityName);
        FacilityEntity facilityEntity = facilityService.getOne(wrapper);
        facilityEntity.setFacilityCount(facilityEntity.getFacilityCount() + 1);
        facilityService.updateById(facilityEntity);
        experimemtService.updateById(experimemt);
    }

}