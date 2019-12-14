package com.tan.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.tan.mapper"})
public class MybatisConfig {
}
