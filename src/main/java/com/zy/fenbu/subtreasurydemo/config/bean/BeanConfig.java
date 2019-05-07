package com.zy.fenbu.subtreasurydemo.config.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@Data
public class BeanConfig {

    public String url;

    public String username;

    public String password;

    public String driverClassName;
}
