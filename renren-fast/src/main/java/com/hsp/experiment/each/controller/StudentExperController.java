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

import com.hsp.experiment.each.entity.StudentExperEntity;
import com.hsp.experiment.each.service.StudentExperService;


/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-09 14:40:43
 */
@RestController
@RequestMapping("/each/studentexper")
public class StudentExperController {
    @Autowired
    private StudentExperService studentExperService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("each:studentexper:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = studentExperService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("each:studentexper:info")
    public R info(@PathVariable("id") String id) {
        StudentExperEntity studentExper = studentExperService.getById(id);

        return R.ok().put("studentExper", studentExper);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("each:studentexper:save")
    public R save(@RequestBody StudentExperEntity studentExper) {
        studentExperService.save(studentExper);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("each:studentexper:update")
    public R update(@RequestBody StudentExperEntity studentExper) {
        studentExperService.updateById(studentExper);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("each:studentexper:delete")
    public R delete(@RequestBody String[] ids) {
        studentExperService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
