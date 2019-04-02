package com.bckj.nacosdemo.accountservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bckj.nacosdemo.commons.entity.Company;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

@Mapper
public interface CompanyMapper extends BaseMapper<Company> {

}
