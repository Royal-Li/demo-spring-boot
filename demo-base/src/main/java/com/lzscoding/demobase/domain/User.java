package com.lzscoding.demobase.domain;

//import com.baomidou.mybatisplus.annotation.TableName;

import com.lzscoding.demobase.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data // 实现了：1、所有属性的get和set方法；2、toString 方法；3、hashCode方法；4、equals方法
@Builder // 建造者模式
@NoArgsConstructor // 无参构造函数
@AllArgsConstructor // 有参构造函数
//@TableName(autoResultMap = true)
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private GenderEnum gender;
    private Date birthday;


//    public static int compareAge(User u1, User u2) {
//        return u1.getAge() > u2.getAge() ? u1.getAge() : u2.getAge();
//    }

}
