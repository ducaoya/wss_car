package com.example.wsscarapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.wsscarapi.entity.CarOrderEntity;
import com.example.wsscarapi.service.OrderService;
import com.example.wsscarapi.tool.Generator;
import com.example.wsscarapi.tool.Result;
import com.example.wsscarapi.tool.Token;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Calendar;

@RestController
@CrossOrigin(origins = "http://localhost:8080",allowCredentials = "true")
@RequestMapping("/api/order")
public class OderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/add")
    @ResponseBody
    public Result add(@RequestBody JSONObject json,@CookieValue(value = "token",required = false) String token){
        Token tk = new Token();
        Result result = new Result(401,"Wrong login status","登录状态错误");
        if(token != null && tk.isTokenValid(token)){
            CarOrderEntity order = new CarOrderEntity();
            order.setId(new Generator().id());
            order.setBuyer(json.getString("buyer"));
            order.setSeller(json.getString("seller"));
            Calendar calendar = Calendar.getInstance();
            Timestamp date = new Timestamp(calendar.getTimeInMillis());
            order.setCreatedDatetime(date);
            order.setCar(json.getString("car").toString());
            order.setCompleted((byte) 0);

            return  orderService.add(order);
        }else {
            return result;
        }
    }

    @PostMapping("/list")
    @ResponseBody
    public Result getListAsBuyer(@RequestBody JSONObject json,@CookieValue(value = "token",required = false) String token){
        Token tk = new Token();
        Result result = new Result(401,"Wrong login status","登录状态错误");
        if(token != null && tk.isTokenValid(token)){
            String user = json.getString("user");
            String identity = json.getString("identity");
            if(user != null && identity != null){
                return  orderService.getList(user,identity);
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

    @PostMapping("/handle")
    @ResponseBody
    public Result handle(@RequestBody JSONObject json,@CookieValue(value = "token",required = false) String token){
        Token tk = new Token();
        Result result = new Result(401,"Wrong login status","登录状态错误");
        if(token != null && tk.isTokenValid(token)){
            String id = json.getString("id");
            String handle = json.getString("handle");
            String msgFromBuyer = json.getString("msgFromBuyer");
            String buyerContact = json.getString("buyerContact");
            String msgFromSeller = json.getString("msgFromSeller");
            String sellerContact = json.getString("sellerContact");
            if(id != null && handle != null){
                return  orderService.handle(id,handle,msgFromBuyer,buyerContact,msgFromSeller,sellerContact);
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
