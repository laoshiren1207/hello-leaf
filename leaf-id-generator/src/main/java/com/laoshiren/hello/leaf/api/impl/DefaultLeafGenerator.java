package com.laoshiren.hello.leaf.api.impl;

import com.laoshiren.hello.leaf.api.LeafGenerator;
import com.laoshiren.hello.leaf.domain.LeafServerDomain;
import com.laoshiren.hello.leaf.utils.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * ProjectName:     hello-leaf
 * Package:         com.laoshiren.hello.leaf.api.impl
 * ClassName:       DefaultLeafGenerator
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2020/7/2 10:53
 * Version:         1.0.0
 */
@Slf4j
public class DefaultLeafGenerator implements LeafGenerator {

    @Resource
    private LeafServerDomain leafServerDomain;

    @Override
    public String leafSnowFlakeIdGenerate() throws Exception {
        String url = "";
        url += leafServerDomain.getHost();
        url += leafServerDomain.getPort();
        url += "/";
        url += leafServerDomain.getUrl();
        log.info(" get request --> {}",url);
        Response data = OkHttpUtils.getInstance().getData(url);
        String id = data.body().string();
        log.info(" get response --> {}",id);
        if (StringUtils.isBlank(id)) {
            throw new Exception("leaf Id Generator Failed");
        }

        return id;
    }
}
