package com.bckj.nacosdemo.accountservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bckj.nacosdemo.commons.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
