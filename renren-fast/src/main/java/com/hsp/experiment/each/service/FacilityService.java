package com.hsp.experiment.each.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsp.experiment.each.entity.FacilityEntity;
import io.renren.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-10 23:14:46
 */
public interface FacilityService extends IService<FacilityEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils getCheckList(Map<String, Object> params);

    PageUtils getRequestList(Map<String, Object> params);

}

