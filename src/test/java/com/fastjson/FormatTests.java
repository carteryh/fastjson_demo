package com.fastjson;

import com.alibaba.fastjson.JSON;

public class FormatTests {

	public static void main(String[] args) {
		String json = " {\"createDate\":\"2015.03\"}";
		SysUser sysUser = JSON.parseObject(json, SysUser.class);

		System.out.println(JSON.toJSONString(sysUser));

	}

}
