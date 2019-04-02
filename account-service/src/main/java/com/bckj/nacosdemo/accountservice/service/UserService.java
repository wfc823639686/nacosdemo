package com.bckj.nacosdemo.accountservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bckj.nacosdemo.commons.entity.User;

public interface UserService extends IService<User> {

    User getDetail(Integer id);
}
