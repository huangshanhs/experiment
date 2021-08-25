package com.hsp.experiment.each.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hsp.experiment.each.entity.TbTimeEntity;
import com.hsp.experiment.each.service.TbTimeService;


/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-27 20:19:55
 */
@RestController
@RequestMapping("/each/timemana")
public class TimeManaController {
    @Autowired
    private TbTimeService tbTimeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tbTimeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id) {
        TbTimeEntity tbTime = tbTimeService.getById(id);

        return R.ok().put("tbTime", tbTime);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TbTimeEntity tbTime) {
        tbTimeService.save(tbTime);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TbTimeEntity tbTime) {
        tbTimeService.updateById(tbTime);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids) {
        tbTimeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
