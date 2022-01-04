package com.example.wsscarapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.wsscarapi.entity.CommentEntity;
import com.example.wsscarapi.service.CommentService;
import com.example.wsscarapi.tool.Generator;
import com.example.wsscarapi.tool.Result;
import com.example.wsscarapi.tool.Token;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Calendar;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:8080", "https://wusansi.doromolll.xyz", "http://wusansi.doromolll.xyz"},allowCredentials = "true")
@RequestMapping("/comment")
public class CommentController {
    @Resource
    CommentService commentService;

    @PostMapping("/add")
    @ResponseBody
    public Result add(@RequestBody JSONObject json , @CookieValue(value = "token" , required = false) String token){
        Token tk = new Token();
        Result result = new Result(401,"Wrong login status","登录状态错误");
        if(token != null && tk.isTokenValid(token)){
            CommentEntity comment = new CommentEntity();
            comment.setId(new Generator().uuid().toString());
            comment.setFromUser(json.getString("user"));
            comment.setCar(json.getString("car"));
            comment.setParent(json.getString("parent"));
            comment.setContent(json.getString("content"));
            Calendar calendar = Calendar.getInstance();
            Timestamp date = new Timestamp(calendar.getTimeInMillis());
            comment.setCreatedDatetime(date);

            return commentService.add(comment);
        }else {
            return result;
        }
    }

    @PostMapping("/list")
    @ResponseBody
    public Result getList(@RequestBody JSONObject json , @CookieValue(value = "token" , required = false) String token){
        Token tk = new Token();
        Result result = new Result(401,"Wrong login status","登录状态错误");
        if(token != null && tk.isTokenValid(token)){
            String car = json.getString("car");

            return  commentService.getList(car);
        }else {
            return result;
        }
    }
}
