package com.pet.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "cb")
public class ValuteClientConfig {
    String url;
}
