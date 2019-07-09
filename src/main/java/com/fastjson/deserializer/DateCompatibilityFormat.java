package com.fastjson.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * fastjson custom annotation
 * chenyouhong
 * date format
 */
@Slf4j
public class DateCompatibilityFormat implements ObjectDeserializer {

    private static final List<String> formatStyles = Lists.newArrayList("yyyy.MM.dd", "yyyy.MM", "yyyy.MM.dd hh:mm:ss");

    @Override
    public Date deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        DateTime dateTime = null;
        try {
            for (String formatStyle: formatStyles) {
                dateTime = this.getDateTime(formatStyle, parser.getLexer().stringVal());
                if (dateTime != null) break;
            }
        } catch (Exception e) {
            log.info("error {}", e);
        }

        return dateTime != null ? dateTime.toDate() : null;
    }

    private DateTime getDateTime(String sFormat, String sDate) {
        DateTime dateTime = null;
        try {
            dateTime = DateTimeFormat.forPattern(sFormat).parseDateTime(sDate);
        } catch (Exception e) {
            log.info("date format error, format={}, date={}, error={}", sFormat, sDate, e);
        }

        return dateTime;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

}