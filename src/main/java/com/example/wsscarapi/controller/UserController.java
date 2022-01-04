package com.example.wsscarapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.wsscarapi.entity.UserEntity;
import com.example.wsscarapi.service.UserService;
import com.example.wsscarapi.tool.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:8080", "https://wusansi.doromolll.xyz", "http://wusansi.doromolll.xyz"},allowCredentials = "true")
@RequestMapping("/user")
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

            ResponseCookie cookies = ResponseCookie.from("token", result.getData().get("token").toString()) // key & value
                    .httpOnly(true)		// 禁止js读取
                    .secure(false)		// 在http下也传输
                    .domain("doromolll.xyz")// 域名
                    .path("/")
                    .maxAge(Duration.ofHours(24))	// 24个小时候过期
//                    .sameSite("none")	// 大多数情况也是不发送第三方 Cookie，但是导航到目标网址的 Get 请求除外
                    .build()
                    ;

            // 设置Cookie Header
            response.setHeader(HttpHeaders.SET_COOKIE, cookies.toString());

            Map data = result.getData();
            data.remove("token");
            result.setData(data);
        }

        return result;
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "test api";
    }
}
