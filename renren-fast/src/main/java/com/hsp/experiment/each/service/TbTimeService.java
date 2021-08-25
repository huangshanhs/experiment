package com.hsp.experiment.each.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsp.experiment.each.entity.TbTimeEntity;
import io.renren.common.utils.PageUtils;

import java.util.Map;

/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-27 20:19:55
 */
public interface TbTimeService extends IService<TbTimeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

