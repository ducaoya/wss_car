package com.example.wsscarapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.wsscarapi.service.DeliveryService;
import com.example.wsscarapi.tool.Result;
import com.example.wsscarapi.tool.Token;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:8080", "https://wusansi.doromolll.xyz", "http://wusansi.doromolll.xyz"},allowCredentials = "true")
@RequestMapping("/delivery")
public class DeliveryController {
    @Resource
    private DeliveryService deliveryService;

    @PostMapping("/get")
    @ResponseBody
    public Result getListAsBuyer(@RequestBody JSONObject json, @CookieValue(value = "token",required = false) String token){
        Token tk = new Token();
        Result result = new Result(401,"Wrong login status","登录状态错误");
        if(token != null && tk.isTokenValid(token)){
            String id = json.getString("id");;
            if(id != null){
                return  deliveryService.get(id);
            }else {
                result.setStatusCode(200);
                result.setStatus("params error");
                result.setMessage("参数错误");
                return result;
            }
        }else {
            return result;
        }
    }
}
