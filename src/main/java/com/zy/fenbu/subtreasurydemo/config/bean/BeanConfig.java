package com.zy.fenbu.subtreasurydemo.config.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 匹配到application.yml的配置
 *
 * @author wangh
 */
@Data
public class BeanConfig {

    public String url;

    public String username;

    public String password;

    public String driverClassName;
}
