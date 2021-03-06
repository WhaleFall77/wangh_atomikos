package com.zy.fenbu.subtreasurydemo.config.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 获取private 数据源的基本信息 获取配置文件的url userName password
 *
 * @author wangh
 */

@Data
@Component
@ConfigurationProperties(prefix = "private.spring.datasource")
public class PrivateBeanConfig extends BeanConfig {


}
