package info.zhirong.weather.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "owner")
public class User {
    private String name;
    private Integer age;
}
