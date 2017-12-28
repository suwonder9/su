package com.wonder.controller;

import com.wonder.entity.UserEntity;
import com.wonder.service.UserService;
import com.wonder.springSecurity.AuthUserImpl;
import com.wonder.utils.JwtUtils;
import com.wonder.vo.HttpResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthUserImpl authUser;
    @Value("${jwt.token.expiration.time}")
    private Long expirationTime;

    @GetMapping("/findByAccount")
    public String findByAccount(String account){
        return userService.getUser(account).getAccount();
    }

    @GetMapping("/add")
    public void addUser(String account,String password){
        userService.addUser(account,password);
    }

    @PostMapping("/login")
    public HttpResp login(String userName,String password){
        HttpResp resp = new HttpResp();
        try {
            UserDetails userDetails = authUser.loadUserByUsername(userName);
            if (password.equals(userDetails.getPassword())){
                resp.setData(JwtUtils.generateToken(userDetails,expirationTime));
                resp.setStatus(HttpResp.HttpRespCode.SUCCESS.getStatus());
            }
        }catch (UsernameNotFoundException e){
            log.info("登录失败，账号出错了，{}",userName);
        }

        return resp;
    }

    @GetMapping("/list")
    public HttpResp userList(){
        List<UserEntity> vos = userService.userList();
        HttpResp resp = new HttpResp();
        resp.setStatus(HttpResp.HttpRespCode.SUCCESS.getStatus());
        resp.setData(vos);
        return resp;
    }
}
