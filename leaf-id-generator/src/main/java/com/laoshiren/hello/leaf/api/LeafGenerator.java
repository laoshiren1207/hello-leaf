package com.laoshiren.hello.leaf.api;

import java.io.IOException;

/**
 * ProjectName:     hello-leaf
 * Package:         com.laoshiren.hello.leaf.utils
 * ClassName:       LeafGeneratorUtil
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2020/7/2 10:45
 * Version:         1.0.0
 */
public interface LeafGenerator {

    /**
     * 基于美团leaf的雪花算法
     * @return long 的 String类型
     */
    String leafSnowFlakeIdGenerate() throws Exception;

}
