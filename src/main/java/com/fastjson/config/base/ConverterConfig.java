//package com.lz.ac.config.base;
//
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson.support.config.FastJsonConfig;
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import com.lz.ac.filter.ZreContextValueFilter;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.StringHttpMessageConverter;
//
//import java.nio.charset.Charset;
//import java.util.Arrays;
//
//@Configuration
//public class ConverterConfig {
//
//    @Bean
//    public HttpMessageConverters fastJsonHttpMessageConverters() {
//        //创建FastJson信息转换对象
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter =
//                new FastJsonHttpMessageConverter();
//        //创建FastJson对象并设定序列化规则
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        //添加自定义valueFilter
//        fastJsonConfig.setSerializeFilters(new ZreContextValueFilter());
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
//        //规则赋予转换对象
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        StringHttpMessageConverter stringHttpMessageConverter =
//                new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        return new HttpMessageConverters(fastJsonHttpMessageConverter, stringHttpMessageConverter);
//    }
//
//}