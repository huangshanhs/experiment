package com.hsp.experiment.each.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsp.experiment.each.entity.StudentExperEntity;
import io.renren.common.utils.PageUtils;

import java.util.Map;

/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-09 14:40:43
 */
public interface StudentExperService extends IService<StudentExperEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

