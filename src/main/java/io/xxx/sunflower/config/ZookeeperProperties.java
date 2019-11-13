package io.xxx.sunflower.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "zookeeper")
public class ZookeeperProperties {

    private String host = "localhost:2181";
}
