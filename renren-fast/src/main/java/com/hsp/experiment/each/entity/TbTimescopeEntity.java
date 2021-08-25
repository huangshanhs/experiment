package com.hsp.experiment.each.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-27 20:19:55
 */
@Data
@TableName("tb_timescope")
public class TbTimescopeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 实验时段
     */
    private String timeScope;
    /**
     * 起始时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date timeStart;
    /**
     * 截止日期
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date timeEnd;

}
