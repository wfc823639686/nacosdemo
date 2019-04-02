package com.bckj.nacosdemo.commons.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_company")
public class Company {
    @TableId("copn_id")
    private long id;
    @TableField("copn_code")
    private String code;
    @TableField("copn_name")
    private String name;
    @TableField("copn_level")
    private String level;
    @TableField("copn_ifdetail")
    private String ifDetail;
    @TableField("copn_topid")
    private String topId;

}
