package com.hsp.experiment.each.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.annotation.SysLog;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hsp.experiment.each.entity.StudentEntity;
import com.hsp.experiment.each.service.StudentService;


/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-09 14:40:44
 */
@RestController
@RequestMapping("/each/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;

    @RequestMapping("/getMyInfo")
    public R getMyInfo(@RequestParam Map<String, Object> params) {
        PageUtils page = studentService.queryMyInfo(params);
        return R.ok().put("info", page);
    }

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = studentService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("each:student:info")
    public R info(@PathVariable("id") String id) {
        StudentEntity student = studentService.getById(id);

        return R.ok().put("student", student);
    }

    /**
     * 保存
     */
    @PostMapping("/save/{username}")
//    @RequiresPermissions("each:student:save")
    public R save(@PathVariable("username") String username, @RequestBody StudentEntity student) {
        if (!username.equals("admin")) {
            throw new RRException("您尚无权限进行此操作，请联系管理员处理");
        }
        studentService.save(student);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update/{username}")
//    @RequiresPermissions("each:student:update")
    public R update(@PathVariable("username") String username, @RequestBody StudentEntity student) {
        if (!username.equals("admin")) {
            throw new RRException("您尚无权限进行此操作，请联系管理员处理");
        }
        studentService.updateById(student);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{username}")
//    @RequiresPermissions("each:student:delete")
    public R delete(@PathVariable("username") String username, @RequestBody String[] ids) {
        if (!username.equals("admin")) {
            throw new RRException("您尚无权限进行此操作，请联系管理员处理");
        }
        studentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 通过用户id删除
     */
    @SysLog("删除学生信息")
    @RequestMapping("/deleteByUserId")
    public R deleteByUserId(@RequestBody String[] ids) {
        List<String> teacherIds = userService.queryUserById(ids);
        studentService.removeByIds(teacherIds);
        return R.ok();
    }

}
