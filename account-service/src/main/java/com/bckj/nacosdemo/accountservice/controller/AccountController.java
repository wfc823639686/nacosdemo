package com.bckj.nacosdemo.accountservice.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bckj.nacosdemo.accountservice.client.StatServiceClient;
import com.bckj.nacosdemo.accountservice.mapper.CompanyMapper;
import com.bckj.nacosdemo.accountservice.mapper.UserMapper;
import com.bckj.nacosdemo.accountservice.service.CompanyService;
import com.bckj.nacosdemo.accountservice.service.UserService;
import com.bckj.nacosdemo.commons.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    StatServiceClient statServiceClient;
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;

    @GetMapping("register")
    public Object register() {
//        System.out.println(statServiceClient.statAddUser("asdf"));
//        return userMapper.selectList(null);
        return null;
    }

    @GetMapping("test")
    public Object test(Integer id, Integer level) {
//        QueryWrapper<Company> qw = new QueryWrapper<>();
//        qw.lambda().eq(Company::getId, id).eq(Company::getLevel, level);
//        return companyService.list(qw);
        return userService.getDetail(id);
    }

    @PostMapping("save")
    public Object save(Company company) {
        return companyService.saveOrUpdate(company);
    }

    @GetMapping("getById")
    public Object getById(Integer id) {
        return companyService.getById(id);
    }

    @GetMapping("removeById")
    public Object removeById(Integer id) {
        return companyService.removeById(id);
    }
}
