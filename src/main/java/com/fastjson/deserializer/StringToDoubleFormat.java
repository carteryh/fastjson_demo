package com.fastjson.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * fastjson custom annotation
 * chenyouhong
 * String cover Double format
 */
@Slf4j
public class StringToDoubleFormat implements ObjectDeserializer {

    private static final int scale = 2;

    @Override
    public Double deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        Double aDouble = null;
        try {
            aDouble = new DecimalFormat().parse(parser.getLexer().stringVal()).doubleValue();
        } catch (Exception e) {
            log.info("String format error, original string={}, error={}", parser.getLexer().stringVal(), e);
        }
        return aDouble;
    }


    @Override
    public int getFastMatchToken() {
        return 0;
    }

}