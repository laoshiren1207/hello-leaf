package com.laoshiren.hello.leaf.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ProjectName:     hello-leaf
 * Package:         com.laoshiren.hello.leaf.properties
 * ClassName:       LeafProperties
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2020/7/2 10:35
 * Version:         1.0.0
 */
@ConfigurationProperties(prefix = "spring.laoshiren.leaf")
public class LeafProperties {

    private String host;

    private String url;

    private String port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
