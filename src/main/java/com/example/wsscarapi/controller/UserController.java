package com.example.wsscarapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.wsscarapi.entity.UserEntity;
import com.example.wsscarapi.service.UserService;
import com.example.wsscarapi.tool.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:8080"},allowCredentials = "true")
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    public Result signup(@RequestBody JSONObject user){
        UserEntity user1=new UserEntity();

        user1.setUsername(user.getString("username"));
        user1.setPassword(user.getString("password"));
        user1.setPhone(user.getString("phone"));
        user1.setProfileImage(user.getString("profileImage"));

        return userService.signup(user1);
    }

    @PostMapping("/checkuser")
    @ResponseBody
    public Result checkUser(
            @RequestBody JSONObject json
    ){
        String username = json.getString("username");
        boolean exist =  userService.checkUserExist(username);
        Result result = new Result(200,"Username already exists","用户名已存在");

        if(!exist){
            result.setStatus("ok");
            result.setMessage("用户名合格");
        }
        return  result;
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody JSONObject json, HttpServletResponse response){
        String username = json.getString("username");
        String password = json.getString("password");

        Result result = userService.login(username,password);
        if(result.getStatus().equals("ok")){
            Cookie cookie = new Cookie("token",result.getData().get("token").toString());
            cookie.setHttpOnly(true);
            cookie.setMaxAge( 24 * 60 * 60);
//            cookie.setDomain("localhost:8080");

            response.addCookie(cookie);
            Map data = result.getData();
            data.remove("token");
            result.setData(data);
        }

        return result;
    }
}
