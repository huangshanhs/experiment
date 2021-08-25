package com.hsp.experiment.each.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;

/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-09 14:40:43
 */
@Data
@TableName("experimemt")
public class ExperimemtEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 实验名称
     */
    @ApiModelProperty(value = "实验名称")
    private String experimentName;

    /**
     * 实验内容
     */
    @ApiModelProperty(value = "实验内容")
    private String content;

    /**
     * 实验状态 0：待完成   1：已完成
     */
    @ApiModelProperty(value = "实验状态")
    private Integer experimentStatus;

    /**
     * 实验设备
     */
    @ApiModelProperty(value = "实验设备")
    private String facilityName;

    /**
     * 设备状态 0：待授权   1：已授权
     */
    @ApiModelProperty(value = "设备状态")
    private Integer facilityStatus;

    /**
     * 指导老师
     */
    @ApiModelProperty(value = "指导老师")
    private String teacherId;

    /**
     * 实验范围
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "实验范围")
    private String experimentScope;

//	/**
//	 * 实验时间
//	 */
//	@ApiModelProperty(value = "截止日期")
//	private Date dadline;

    /**
     * 实验日期
     */
    @ApiModelProperty(value = "实验日期")
//    @TableField(fill=FieldFill.INSERT)
    private Date experimentDate;
    /**
     * 实验时间
     */
    @ApiModelProperty(value = "实验时间")
    private String experimentTime;

}
