package com.hsp.experiment.each.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsp.experiment.each.entity.StudentEntity;
import io.renren.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-09 14:40:44
 */
public interface StudentService extends IService<StudentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryMyInfo(Map<String, Object> params);
}

