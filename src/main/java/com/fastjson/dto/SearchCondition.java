package com.fastjson.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchCondition implements Serializable {

	private String key;

	private String value;
	
}
