package com.hsp.experiment.each.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hsp.experiment.each.entity.ExperimemtEntity;
import com.hsp.experiment.each.entity.FacilityEntity;
import io.renren.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-09 14:40:43
 */
public interface ExperimemtService extends IService<ExperimemtEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<FacilityEntity> queryNameById(String id);

    boolean isTimePast(ExperimemtEntity experimemtEntity);

    void saveExperimentInfo(ExperimemtEntity experimemt);

    void startExperiment(ExperimemtEntity experimemt);
}

