package com.hsp.experiment.each.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-27 20:19:55
 */
@Data
@TableName("tb_time")
public class TbTimeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private int id;
    /**
     * 考试时间段
     */
    private String segment;

}
