package com.hsp.experiment.each.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-09 14:40:44
 */
@Data
@TableName("student")
public class StudentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
//	/**
//	 * 用户名
//	 */
//	@ApiModelProperty(value = "用户名")
//	private String username;
    /**
     * 学生姓名
     */
    @ApiModelProperty(value = "学生姓名")
    private String studentName;
    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式")
    private String studentNum;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更改时间
     */
    @ApiModelProperty(value = "更改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
