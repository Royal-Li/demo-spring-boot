package com.lzscofing.demoredis.controller;

import com.lzscoding.demomybatisplus.pojo.User;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.security.util.Password;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 测试springsession 存储到redis中
 *
 * @author 180626
 */
@RestController
public class RedisSesssionController {


    @RequestMapping("/userlogin")
    public ResponseEntity<?> userLogin(@RequestParam("username") String username,
                                       @RequestParam("password") String password,
                                       HttpSession httpSession) {

        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        User user = User.builder().name(username).email(password).build();
        httpSession.setAttribute("user", user);

        return new ResponseEntity<>("登陆成功 : " + user, HttpStatus.OK);
    }

    @RequestMapping("/usersession")
    public ResponseEntity<?> getSession(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        return new ResponseEntity<>("根据session获取用户成功 : " + user, HttpStatus.OK);
    }

}
