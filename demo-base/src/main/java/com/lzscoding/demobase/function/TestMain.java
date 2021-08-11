package com.lzscoding.demobase.function;

import org.apache.commons.lang3.StringUtils;

public class TestMain {
    public static void main(String[] args) {
        String result = CheckFunction.Function.tryOf(m -> returnNullOrNotNull(m), "not null arg").orElse("传入参数值为空！");
        System.out.println(result);

        String result1 = CheckFunction.Function.tryOf(m -> returnNullOrNotNull(m),"").orElse("传入参数值为空！");
        System.out.println(result1);
        String result2 = CheckFunction.Function.tryOf(m -> errorMethod(),"").orElse("即使出错了 也继续输出");
        System.out.println(result2);

    }


    public static String returnNullOrNotNull(String arg) {
        if (!StringUtils.isBlank(arg)) {
            return arg;
        }
        return null;
    }

    public static String errorMethod(){
        throw new RuntimeException("errorMethod 出错了");
    }
}
