package com.laoshiren.hello.leaf.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * ProjectName:     hello-leaf
 * Package:         com.laoshiren.hello.leaf.domain
 * ClassName:       LeafServerDomain
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2020/7/2 10:54
 * Version:         1.0.0
 */
@Data
public class LeafServerDomain implements Serializable {
    private static final long serialVersionUID = 4737088286461144077L;

    private String host;

    private String url;

    private String port;



}
