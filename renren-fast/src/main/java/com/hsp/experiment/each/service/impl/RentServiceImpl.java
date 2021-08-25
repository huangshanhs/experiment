package com.hsp.experiment.each.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hsp.experiment.each.dao.RentDao;
import com.hsp.experiment.each.entity.RentEntity;
import com.hsp.experiment.each.service.RentService;
import org.springframework.stereotype.Service;

@Service
public class RentServiceImpl extends ServiceImpl<RentDao, RentEntity> implements RentService {
}
