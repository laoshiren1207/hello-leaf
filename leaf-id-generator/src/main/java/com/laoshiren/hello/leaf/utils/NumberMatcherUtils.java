package com.laoshiren.hello.leaf.utils;

/**
 * ProjectName:     hello-leaf
 * Package:         com.laoshiren.hello.leaf.utils
 * ClassName:       NumberMatcherUtils
 * Author:          laoshiren
 * Git:             xiangdehua@pharmakeyring.com
 * Description:
 * Date:            2020/7/2 11:12
 * Version:         1.0.0
 */
public class NumberMatcherUtils {

    private static String NUMBER_PATTERN = "^\\d+$";

    public static boolean isNumberMatch (String input){
        return input.matches(NUMBER_PATTERN);
    }

}
