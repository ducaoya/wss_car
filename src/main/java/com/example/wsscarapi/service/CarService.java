package com.example.wsscarapi.service;

import com.example.wsscarapi.dao.CarDao;
import com.example.wsscarapi.entity.CarEntity;
import com.example.wsscarapi.tool.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.*;

@Service
public class CarService {
    @Resource
    private CarDao carDao;

//    分页查询二手车列表
    public Result getCarList(int page, int size){
        Result result = new Result(200,"ok","");
        Pageable request = PageRequest.of(page,size);
        Page<CarEntity> list = carDao.findAll(request);

        Map data = new HashMap();
        data.put("list",list.getContent());
        data.put("total",list.getTotalElements());
        result.setData(data);

        return  result;
    }

//    搜索
    public Result search(int page, int size,String title,int min_price,int max_price,int car_age){
        Result result = new Result(200,"ok","");
        Page<CarEntity> list;
        Pageable request = PageRequest.of(page,size);

        Specification<CarEntity> specification = new Specification<CarEntity>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if(title != null && !title.equals("")){
                    Predicate predicateByTitle = criteriaBuilder.like(root.get("title"),'%'+title+'%');
                    list.add(predicateByTitle);
                }

                if(min_price != -1 && max_price != -1){
                    Predicate predicateByPrice = criteriaBuilder.between(root.get("price"),min_price,max_price);
                    list.add(predicateByPrice);
                }

                if(car_age != -1){
                    Calendar calendar = Calendar.getInstance();
                    Date nowDate = new Date(calendar.getTimeInMillis());
                    int y = calendar.get(Calendar.YEAR)-car_age;
                    int m = calendar.get(Calendar.MONTH);
                    int d = calendar.get(Calendar.DATE);
                    calendar.set(y,m,d);
                    Date date = new Date(calendar.getTimeInMillis());
                    Predicate predicateByAge = criteriaBuilder.between(root.get("firstRegistered"),date,nowDate);
                    list.add(predicateByAge);
                }

                Predicate[] predicates = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(predicates));
            }
        };

        list = carDao.findAll(specification,request);

        Map data = new HashMap();
        data.put("list",list.getContent());
        data.put("total",list.getTotalElements());
        result.setData(data);

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
