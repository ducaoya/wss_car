package com.example.wsscarapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.wsscarapi.entity.CarOrderEntity;
import com.example.wsscarapi.entity.CollectionEntity;
import com.example.wsscarapi.service.CollectionService;
import com.example.wsscarapi.tool.Generator;
import com.example.wsscarapi.tool.Result;
import com.example.wsscarapi.tool.Token;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Calendar;

@RestController
@CrossOrigin(origins = "http://localhost:8080",allowCredentials = "true")
@RequestMapping("/api/collection")
public class CollectionController {
    @Resource
    private CollectionService collectionService;

    @PostMapping("/add")
    @ResponseBody
    public Result add(@RequestBody JSONObject json , @CookieValue(value = "token" , required = false) String token){
        Token tk = new Token();
        Result result = new Result(401,"Wrong login status","登录状态错误");
        if(token != null && tk.isTokenValid(token)){
            CollectionEntity collection = new CollectionEntity();
            collection.setId(new Generator().uuid().toString());
            Calendar calendar = Calendar.getInstance();
            Timestamp date = new Timestamp(calendar.getTimeInMillis());
            collection.setCreatedDatetime(date);
            collection.setByUser(json.getString("user"));
            collection.setCar(json.getString("car"));

            return  collectionService.add(collection);
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
           String user = json.getString("user");

            return  collectionService.getList(user);
        }else {
            return result;
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result del(@RequestBody JSONObject json , @CookieValue(value = "token" , required = false) String token){
        Token tk = new Token();
        Result result = new Result(401,"Wrong login status","登录状态错误");
        if(token != null && tk.isTokenValid(token)){
            String id = json.getString("id");

            return  collectionService.del(id);
        }else {
            return result;
        }
    }

}
