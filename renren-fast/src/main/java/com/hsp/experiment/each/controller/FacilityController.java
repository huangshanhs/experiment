package com.hsp.experiment.each.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hsp.experiment.each.entity.ExperimemtEntity;
import com.hsp.experiment.each.entity.RentEntity;
import com.hsp.experiment.each.entity.TeacherEntity;
import com.hsp.experiment.each.service.ExperimemtService;
import io.renren.common.annotation.SysLog;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.SysUserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hsp.experiment.each.entity.FacilityEntity;
import com.hsp.experiment.each.service.FacilityService;


/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-10 23:14:46
 */
@Api
@RestController
@RequestMapping("/each/facility")
public class FacilityController {
    @Autowired
    private FacilityService facilityService;
    @Autowired
    private ExperimemtService experimemtService;

    @ApiOperation("查询待审核")
    @GetMapping("requestWaitList")
    public R requestWaitList(@RequestParam Map<String, Object> params) {
        //查询所有待审核的设备
        PageUtils list = facilityService.getCheckList(params);
        return R.ok().put("requestWaitList", list);
    }

    @ApiOperation("查询通过审核")
    @GetMapping("requestList")
    public R requestList(@RequestParam Map<String, Object> params) {
        //查询所有待审核的设备
        PageUtils list = facilityService.getRequestList(params);
        return R.ok().put("requestList", list);
    }


    @GetMapping("/findAll")
    public R findAllTeacher() {
        ///调用service的方法查询所有的操作
        List<FacilityEntity> list = facilityService.list(null);
        return R.ok().put("items", list);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = facilityService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @SysLog("查询设备信息")
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("each:facility:info")
    public R info(@PathVariable("id") String id) {
        FacilityEntity facility = facilityService.getById(id);

        return R.ok().put("facility", facility);
    }

    /**
     * 保存
     */
    @ApiOperation("保存设备信息")
    @SysLog("保存设备信息")
    @RequestMapping("/save")
    public R save(@RequestBody FacilityEntity facility) {
        QueryWrapper<FacilityEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("facility_name", facility.getFacilityName());
        FacilityEntity facilityInfo = facilityService.getOne(wrapper);
        if (facilityInfo != null) {
            Integer count = facilityInfo.getFacilityCount();
            facility.setFacilityCount(facility.getFacilityCount() + count);
            facility.setFacilityStatus(facilityInfo.getFacilityStatus());
            facility.setTeacherName(facilityInfo.getTeacherName());
            facility.setId(facilityInfo.getId());
            facilityService.updateById(facility);
        } else {
            facilityService.save(facility);
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改设备信息")
    @RequestMapping("/update")
//    @RequiresPermissions("each:facility:update")
    public R update(@RequestBody FacilityEntity facility) {
        facilityService.updateById(facility);
        return R.ok();
    }

    /**
     * 审核未通过
     */
    @SysLog("审核未通过")
    @RequestMapping("/refused")
    public R refused(@RequestBody FacilityEntity facility) {
        FacilityEntity facilityEntity = facilityService.getById(facility);
        facility.setFacilityCount(facilityEntity.getFacilityCount() + 1);
        facilityService.updateById(facility);
        return R.ok();
    }

    /**
     * 通过审核
     */
    @SysLog("通过审核")
    @RequestMapping("/pass")
    public R pass(@RequestBody FacilityEntity facility) {
        FacilityEntity facilityEntity = facilityService.getById(facility);
        String experiment_id = facilityEntity.getExperimentId();
        ExperimemtEntity experimemtEntity = new ExperimemtEntity();
        experimemtEntity.setId(experiment_id);
        experimemtEntity.setFacilityStatus(1);
        experimemtService.updateById(experimemtEntity);
        facilityService.updateById(facility);
        return R.ok();
    }

    /**
     * 撤销申请
     */
    @SysLog("撤销申请")
    @RequestMapping("/revocation")
    public R revocation(@RequestBody FacilityEntity facility) {
        FacilityEntity facilityEntity = facilityService.getById(facility);
        facility.setFacilityCount(facilityEntity.getFacilityCount() + 1);
        facilityService.updateById(facility);
        return R.ok();
    }

    /**
     * 申请授权
     */
    @SysLog("申请设备权限")
    @RequestMapping("/requestInfo")
    public R requestInfo(@RequestBody FacilityEntity facility) {
        ExperimemtEntity experimemtEntity = experimemtService.getById(facility.getExperimentId());
        FacilityEntity facilityEntity = facilityService.getById(facility);
        if (experimemtEntity.getFacilityStatus() != 0) {
            return R.error("该实验已具备权限，无需再次申请");
        }
        if (facilityEntity.getFacilityCount() < 1) {
            return R.error("当前无可用设备，请等待其他设备使用完毕");
        }
        try {
            Integer facilityCount = facility.getFacilityCount();
            facility.setFacilityCount(facilityCount - 1);
            facility.setFacilityStatus(2);
            facilityService.updateById(facility);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RRException("申请授权出错", 500);
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("刪除设备信息")
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids) {
        facilityService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
