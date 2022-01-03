package com.example.wsscarapi.service;

import com.example.wsscarapi.dao.CarDao;
import com.example.wsscarapi.entity.CarEntity;
import com.example.wsscarapi.tool.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarService {
    @Resource
    private CarDao carDao;

//    分页查询二手车列表
    public Result getCarList(int page, int size){
        Result result = new Result(200,"ok","");
        List<CarEntity> list = carDao.findAll();
        int total = list.size();
        list = list.subList((page-1)*size,page*size);

        Map data = new HashMap();
        data.put("list",list);
        data.put("total",total);
        result.setData(data);

        return  result;
    }

//    搜索
    public Result search(int page, int size,String title,int min_price,int max_price,int car_age){
        Result result = new Result(200,"ok","");
        List<CarEntity> list;
        if(title != null && title != ""){
            list = carDao.findAllByTitleLike("%"+title+"%");
        }else {
            list = carDao.findAll();
        }

        if(min_price != -1 && max_price != -1){
            list=list.stream()
                    .filter((CarEntity car)->car.getPrice()>=min_price && car.getPrice()<=max_price)
                    .collect(Collectors.toList());
        }

        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        if(car_age !=-1){
            list=list.stream()
                    .filter((CarEntity car)->{
                        calendar.setTime(car.getFirstRegistered());
                        int year = calendar.get(Calendar.YEAR);
                        return nowYear-year<=car_age;
                    })
                    .collect(Collectors.toList());
        }

        int total = list.size();

        if(total<(page-1)*size){
            result.setStatus("error");
            result.setMessage("页面和页面尺寸过大");
        }else {
            if(total<page*size){
                list = list.subList((page-1)*size,total);
            }else {
                list = list.subList((page-1)*size,page*size);
            }
            Map data = new HashMap();
            data.put("list",list);
            data.put("total",total);
            result.setData(data);
        }

        return  result;
    }

    public Result add(CarEntity car){
        Result result = new Result(200,"ok","");

        if(carDao.save(car) != null){
            result.setMessage("添加成功");
            Map data = new HashMap();
            data.put("car",car);
            result.setData(data);
        }else {
            result.setStatus("error");
            result.setMessage("添加失败，请重试");
        }

        return result;
    }

    public Result getCar(String car){
        Result result = new Result(200,"ok","获取成功");
        Optional<CarEntity> carEntity = carDao.findById(car);

        Map data = new HashMap();
        data.put("car",carEntity);
        result.setData(data);

        return result;
    }
}
