package com.laoshiren.hello.leaf.configuration;

import com.laoshiren.hello.leaf.api.LeafGenerator;
import com.laoshiren.hello.leaf.api.impl.DefaultLeafGenerator;
import com.laoshiren.hello.leaf.domain.LeafServerDomain;
import com.laoshiren.hello.leaf.properties.LeafProperties;
import com.laoshiren.hello.leaf.utils.NumberMatcherUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * ProjectName:     hello-leaf
 * Package:         com.laoshiren.hello.leaf.configuration
 * ClassName:       LeafConfigure
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2020/7/2 10:37
 * Version:         1.0.0
 */
@Slf4j
@EnableConfigurationProperties(value = LeafProperties.class)
public class LeafDefaultAutoConfigure {

    @Resource
    private LeafProperties leafProperties;

    @Bean
    public LeafGenerator initLeafGenerator(){
        return new DefaultLeafGenerator();
    }

    @Bean
    public LeafServerDomain initServerDomain() throws Exception {
        log.info("leaf id generator init .....");
        LeafServerDomain domain = new LeafServerDomain();
        String host = leafProperties.getHost();

        if (StringUtils.isBlank(host)) {
            throw new Exception( "Leaf init fail -- host is blank" );
        }
        String portString = leafProperties.getPort() ;
        if (NumberMatcherUtils.isNumberMatch( leafProperties.getPort() )) {
            if ( StringUtils.equals("80",portString) ) {
                portString = "";
            } else {
                portString = ":"+portString;
            }
        }
        domain.setHost(leafProperties.getHost());
        domain.setPort(portString);
        domain.setUrl(leafProperties.getUrl());
        log.info("leaf id generator host --- {}",leafProperties.getHost()+portString+leafProperties.getUrl());
        return domain;
    }

}