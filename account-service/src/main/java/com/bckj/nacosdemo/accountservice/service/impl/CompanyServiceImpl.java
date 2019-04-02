package com.bckj.nacosdemo.accountservice.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bckj.nacosdemo.accountservice.mapper.CompanyMapper;
import com.bckj.nacosdemo.accountservice.service.CompanyService;
import com.bckj.nacosdemo.commons.entity.Company;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

}
