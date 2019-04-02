package com.bckj.nacosdemo.accountservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bckj.nacosdemo.accountservice.mapper.UserMapper;
import com.bckj.nacosdemo.accountservice.service.CompanyService;
import com.bckj.nacosdemo.accountservice.service.UserService;
import com.bckj.nacosdemo.commons.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    CompanyService companyService;

    @Override
    public User getDetail(Integer id) {
        User detail = getById(id);
        if (detail != null) {
            detail.setCompany(companyService.getById(detail.getCompanyId()));
        }
        return detail;
    }
}
