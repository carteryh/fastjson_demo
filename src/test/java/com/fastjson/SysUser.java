package com.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.fastjson.deserializer.DateCompatibilityFormat;
import com.fastjson.deserializer.StringToDoubleFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String loginCode;

    private String password;

    @JSONField(deserializeUsing = DateCompatibilityFormat.class)
    private Date createDate;

    private String mobile;

    @JSONField(deserializeUsing = StringToDoubleFormat.class)
    private Double age;

}
