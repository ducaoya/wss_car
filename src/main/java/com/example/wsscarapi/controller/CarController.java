package com.example.wsscarapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.wsscarapi.entity.CarEntity;
import com.example.wsscarapi.service.CarService;
import com.example.wsscarapi.tool.Generator;
import com.example.wsscarapi.tool.Result;
import com.example.wsscarapi.tool.Token;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Controller
@CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1:8080", "https://wusansi.doromolll.xyz", "http://wusansi.doromolll.xyz"},allowCredentials = "true")
@RequestMapping("/car")
public class CarController {
    @Resource
    private CarService carService;

    @PostMapping("/list")
    @ResponseBody
    public Result getCarList(@RequestBody JSONObject json){
        int page=json.getInteger("page");
        int size=json.getInteger("size");

        return carService.getCarList(page,size);
    }

    @PostMapping("/search")
    @ResponseBody
    public Result search(@RequestBody JSONObject json){
        int page=json.getInteger("page");
        int size=json.getInteger("size");
        String title = json.getString("title");

        int min_price = json.getInteger("min_price");
        int max_price = json.getInteger("max_price");
        int car_age = json.getInteger("car_age");

        return carService.search(page,size,title,min_price,max_price,car_age);
    }

    @PostMapping("/add")
    @ResponseBody
    public Result add(@RequestBody JSONObject json ,@CookieValue(value = "token",required = false) String token) throws ParseException {
        Token tk = new Token();
        Result result = new Result(401,"Wrong login status","登录状态错误");
        if(token == null){
            return result;
        }else {
            if(tk.isTokenValid(token)){

                    CarEntity car = new CarEntity();
                    car.setId(new Generator().uuid().toString());
                    car.setBrand(json.getString("brand"));
                    car.setSeries(json.getString("series"));
                    Date date1 = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(json.getString("firstRegistered")).getTime());
                    car.setFirstRegistered(date1);
                    car.setMileage(json.getString("mileage"));
                    car.setEmissionStandard(json.getString("emission_standard"));
                    car.setNumberOfAssignments(json.getInteger("numberOfAssignments"));
                    car.setEmission(json.getString("emission"));
                    car.setGearbox(json.getString("gearbox"));
                    car.setColor(json.getString("color"));
                    Date date2 = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(json.getString("productionDate")).getTime());
                    car.setProductionDate(date2);
                    car.setNumberOfKeys(json.getInteger("numberOfKeys"));
                    car.setPrice(json.getInteger("price"));
                    car.setIsSold((byte) 0);
                    car.setIsDeleted((byte) 0);
                    car.setFromUser(json.getString("fromUser"));
                    car.setTitle(json.getString("title"));
                    car.setPreviewImage(json.getString("previewImage"));
                    car.setImages(json.getString("images"));
                    car.setGuaziClueId(json.getString("guaziClueId"));

                    return carService.add(car);
                }else {
                    return result;
                }

            }
    }

    @PostMapping("/get")
    @ResponseBody
    public Result getCar(@RequestBody JSONObject json){
        String car = json.getString("car");
        return  carService.getCar(car);
    }

}
