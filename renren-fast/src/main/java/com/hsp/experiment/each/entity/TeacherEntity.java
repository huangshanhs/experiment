package com.hsp.experiment.each.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-09 14:40:43
 */
@Data
@TableName("teacher")
public class TeacherEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 联系老师
     */
    @ApiModelProperty(value = "联系老师")
    private String teacherNum;
    /**
     * 老师姓名
     */
    @ApiModelProperty(value = "老师姓名")
    private String teacherName;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更改时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date updateTime;

}
