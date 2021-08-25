package com.hsp.experiment.each.controller;

import com.hsp.experiment.each.entity.RentEntity;
import com.hsp.experiment.each.service.RentService;
import io.renren.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/each/rent")
public class RentController {

    @Autowired
    private RentService rentService;

    @PostMapping("saveInfo")
    public R saveInfo(@RequestBody RentEntity rentEntity) {
        rentEntity.setId(rentEntity.getId());
        rentService.save(rentEntity);
        return R.ok();
    }
}
