package com.hsp.experiment.each.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hsp.experiment.each.entity.TbTimescopeEntity;
import com.hsp.experiment.each.service.TbTimescopeService;


/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-27 20:19:55
 */
@RestController
@RequestMapping("/each/timescope")
public class TimescopeController {
    @Autowired
    private TbTimescopeService tbTimescopeService;

    @GetMapping("findAll")
    public R findAll() {
        List<TbTimescopeEntity> list = tbTimescopeService.list(null);
        return R.ok().put("items", list);

    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tbTimescopeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id) {
        TbTimescopeEntity tbTimescope = tbTimescopeService.getById(id);

        return R.ok().put("tbTimescope", tbTimescope);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TbTimescopeEntity tbTimescope) {
        Date timeStart = tbTimescope.getTimeStart();
        Date timeEnd = tbTimescope.getTimeEnd();
        if (timeEnd.compareTo(timeStart) == -1) {
            throw new RRException("时间选择出错，请选择正确的时间", 500);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String timeScope = format.format(timeStart) + "日至" + format.format(timeEnd) + "日";
        tbTimescope.setTimeScope(timeScope);
        tbTimescopeService.save(tbTimescope);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TbTimescopeEntity tbTimescope) {
        Date timeStart = tbTimescope.getTimeStart();
        Date timeEnd = tbTimescope.getTimeEnd();
        if (timeEnd.compareTo(timeStart) == -1) {
            throw new RRException("时间选择出错，请选择正确的时间", 500);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String timeScope = format.format(timeStart) + "日至" + format.format(timeEnd) + "日";
        tbTimescope.setTimeScope(timeScope);
        tbTimescopeService.updateById(tbTimescope);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids) {
        tbTimescopeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
