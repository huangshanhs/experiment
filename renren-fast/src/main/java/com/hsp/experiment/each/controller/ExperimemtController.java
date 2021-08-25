package com.hsp.experiment.each.controller;

import java.util.*;

import com.hsp.experiment.each.entity.FacilityEntity;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hsp.experiment.each.entity.ExperimemtEntity;
import com.hsp.experiment.each.service.ExperimemtService;


/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-09 14:40:43
 */
@RestController
@RequestMapping("/each/experimemt")
public class
ExperimemtController {
    @Autowired
    private ExperimemtService experimemtService;

    @GetMapping("/findAll")
    public R findAllTeacher() {
        ///调用service的方法查询所有的操作
        List<ExperimemtEntity> list = experimemtService.list(null);
        return R.ok().put("items", list);
    }

    @PostMapping("/past")
    public boolean past(@RequestBody ExperimemtEntity experimemtEntity) {
        boolean flag = experimemtService.isTimePast(experimemtEntity);
        return flag;
    }

    /**
     * 列表
     */

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = experimemtService.queryPage(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/getFacilityInfo/{id}")
    public R getFacilityInfo(@PathVariable String id) {
        List<FacilityEntity> facilityEntities = experimemtService.queryNameById(id);
        return R.ok().put("facilityEntities", facilityEntities);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("each:experimemt:info")
    public R info(@PathVariable("id") String id) {
        ExperimemtEntity experimemt = experimemtService.getById(id);

        return R.ok().put("experimemt", experimemt);
    }

    /**
     * 保存
     */
    @SysLog("保存实验信息")
    @RequestMapping("/save")
//    @RequiresPermissions("each:experimemt:save")
    public R save(@RequestBody ExperimemtEntity experimemt) {
        experimemtService.saveExperimentInfo(experimemt);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改实验信息")
    @RequestMapping("/update")
//    @RequiresPermissions("each:experimemt:update")
    public R update(@RequestBody ExperimemtEntity experimemt) {
        experimemtService.updateById(experimemt);
        return R.ok();
    }

    /**
     * 开始实验
     */
    @SysLog("开始实验")
    @RequestMapping("/start")
    public R start(@RequestBody ExperimemtEntity experimemt) {
        experimemtService.startExperiment(experimemt);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("刪除实验項目")
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids) {
        experimemtService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }


}
