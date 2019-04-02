package com.bckj.nacosdemo.commons.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class User {
    @TableId("us_id")
    private long id;
    @TableField("us_name")
    private String name;
    @TableField("us_company_id")
    private long companyId;
    @TableField(exist = false)
    private Company company;
}
