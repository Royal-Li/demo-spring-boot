package com.lzscoding.demobase.enums;

//import com.baomidou.mybatisplus.annotation.EnumValue;

public enum GenderEnum {

    Male(1, "男"),
    Female(0, "女");

    //    @EnumValue
    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    GenderEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
