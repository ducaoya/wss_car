package com.example.wsscarapi.service;

import com.example.wsscarapi.dao.CarOrderDao;
import com.example.wsscarapi.entity.CarOrderEntity;
import com.example.wsscarapi.entity.DeliveryEntity;
import com.example.wsscarapi.tool.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class OrderService {
    @Resource
    private CarOrderDao orderDao;

    @Resource
    private  DeliveryService deliveryService;

    public Result add(CarOrderEntity order){
        Result result = new Result(200,"ok","");
        try{
            if(orderDao.save(order)!=null){
                Map data = new HashMap();
                data.put("order",order);
                result.setData(data);
            }else {
                result.setStatus("error");
                result.setMessage("创建订单失败，请重试");
            }
        }catch (Exception e){
            System.out.println("error===>"+e);
            result.setStatus("error");
            result.setMessage("创建订单失败，请重试");
        }

        return  result;
    }

    public Result getList(String user,String identity){
        Result result = new Result(200,"ok","");
        List<CarOrderEntity> list;
        if(identity.equals("buyer")){
            list = orderDao.findAllByBuyer(user);
        }else {
            list = orderDao.findAllBySeller(user);
        }

        Map data = new HashMap();
        data.put("list",list);
        result.setData(data);

        return result;
    }

    public Result handle(
            String id,
            String handle,
            String msgFromBuyer,
            String buyerContact ,
            String msgFromSeller,
            String sellerContact
    ){
        Result result = new Result(200,"ok","");
        List<CarOrderEntity> list = orderDao.findAllById(id);
        if(list.size()>0){
            CarOrderEntity order = list.get(0);
            Map data = new HashMap();
            if(handle.equals("cancel")){
                order.setCompleted((byte) 2);
                data.put("order",order);
                result.setData(data);
            }else {
                DeliveryEntity deliveryEntity = deliveryService.generate(id,msgFromBuyer,buyerContact,msgFromSeller,sellerContact);
                data.put("delivery",deliveryEntity);
                result.setData(data);
                order.setCompleted((byte) 1);
            }
            try{
                orderDao.save(order);
            }catch (Exception e){
                System.out.println("error ===>"+ e);
            }
        }

        return result;
    }

}
