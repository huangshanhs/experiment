package com.hsp.experiment.each.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-10 23:14:46
 */
@Data
@TableName("facility")
public class FacilityEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 申请人
     */
    @ApiModelProperty(value = "申请人")
    private String username;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    private String facilityName;
    /**
     * 设备简介
     */
    @ApiModelProperty(value = "设备简介")
    private String facilityContent;
    /**
     * 设备状态
     */
    @ApiModelProperty(value = "设备状态")
    private Integer facilityStatus;
    /**
     * 设备数量
     */
    @ApiModelProperty(value = "设备数量")
    private Integer facilityCount;
    /**
     * 实验名称
     */
    @ApiModelProperty(value = "实验名称")
    private String experimentId;
    /**
     * 指导老师
     */
    @ApiModelProperty(value = "指导老师")
    private String teacherName;

    /**
     * 申请说明
     */
    @ApiModelProperty(value = "申请说明")
    private String description;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
