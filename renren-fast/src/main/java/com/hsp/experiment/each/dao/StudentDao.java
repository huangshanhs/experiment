package com.hsp.experiment.each.dao;

import com.hsp.experiment.each.entity.StudentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author huangshan
 * @email hs15929153044@163.com
 * @date 2021-03-09 14:40:44
 */
@Mapper
public interface StudentDao extends BaseMapper<StudentEntity> {

}
