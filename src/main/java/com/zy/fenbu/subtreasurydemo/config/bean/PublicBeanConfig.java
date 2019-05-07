package com.zy.fenbu.subtreasurydemo.config.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Data
@Component
@ConfigurationProperties(prefix = "public.spring.datasource")
public class PublicBeanConfig extends BeanConfig{


}
