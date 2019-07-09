package com.fastjson.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

/**
 * fastjson custom annotation
 * chenyouhong
 * String cover Double format
 */
@Slf4j
public class StringToIntegerFormat implements ObjectDeserializer {

    private static final int scale = 2;

    @Override
    public Integer deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Integer aInteger = null;
        try {
            aInteger = Integer.valueOf(parser.getLexer().stringVal());
        } catch (Exception e) {
            log.info("Integer format error, original string={}, error={}", parser.getLexer().stringVal(), e);
        }
        return aInteger;
    }


    @Override
    public int getFastMatchToken() {
        return 0;
    }

}