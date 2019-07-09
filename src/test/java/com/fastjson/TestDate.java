/**
  * Copyright 2019 bejson.com 
  */
package com.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Auto-generated: 2019-04-15 15:28:50
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
@NoArgsConstructor
public class TestDate {

    @JSONField(format = "yyyy.MM")
//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy.MM || yyyy.MM.dd")
//    @JsonFormat(p)
    private Date date;

}