package com.hsp.experiment.each.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.annotation.SysLog;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hsp.experiment.each.entity.TeacherEntity;
import com.hsp.experiment.each.service.TeacherService;


/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-09 14:40:43
 */
@RestController
@RequestMapping("/each/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public R findAllTeacher() {
        ///调用service的方法查询所有的操作
        List<TeacherEntity> list = teacherService.list(null);
        return R.ok().put("items", list);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("each:teacher:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = teacherService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @SysLog("查询教师信息")
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("each:teacher:info")
    public R info(@PathVariable("id") String id) {
        TeacherEntity teacher = teacherService.getById(id);

        return R.ok().put("teacher", teacher);
    }

    /**
     * 保存
     */
    @SysLog("保存教师信息")
    @RequestMapping("/save/{username}")
//    @RequiresPermissions("each:teacher:save")
    public R save(@PathVariable("username") String username, @RequestBody TeacherEntity teacher) {
        teacherService.save(teacher);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改教师信息")
    @RequestMapping("/update/{username}")
    public R update(@PathVariable("username") String username, @RequestBody TeacherEntity teacher) {
        if (username.equals("admin") || username.equals(teacher.getId())) {
            teacherService.updateById(teacher);
            return R.ok();
        }
        return R.error("您尚无权限进行此操作，请联系管理员处理");
    }

    /**
     * 删除
     */
    @SysLog("删除教师信息")
    @RequestMapping("/delete/{username}")
//    @RequiresPermissions("each:teacher:delete")
    public R delete(@PathVariable("username") String username, @RequestBody String[] ids) {
        if (!username.equals("admin")) {
            throw new RRException("您尚无权限进行此操作，请联系管理员处理");
        }
        teacherService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 通过用户id删除
     */
    @SysLog("删除教师信息")
    @RequestMapping("/deleteByUserId")
    public R deleteByUserId(@RequestBody String[] ids) {
        List<String> teacherIds = userService.queryUserById(ids);
        teacherService.removeByIds(teacherIds);
        return R.ok();
    }
}
