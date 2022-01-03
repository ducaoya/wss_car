package com.example.wsscarapi.service;

import com.example.wsscarapi.dao.DeliveryDao;
import com.example.wsscarapi.entity.DeliveryEntity;
import com.example.wsscarapi.tool.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class DeliveryService {
    @Resource
    private DeliveryDao deliveryDao;

    public DeliveryEntity generate(
            String id,
            String msgFromBuyer,
            String buyerContact ,
            String msgFromSeller,
            String sellerContact
    ){
        try{
            DeliveryEntity deliveryEntity = new DeliveryEntity();

            deliveryEntity.setCarOrder(id);
            deliveryEntity.setMessageFromBuyer(msgFromBuyer);
            deliveryEntity.setBuyerContact(buyerContact);
            deliveryEntity.setMessageFromSeller(msgFromSeller);
            deliveryEntity.setSellerContact(sellerContact);

            deliveryDao.save(deliveryEntity);
            return  deliveryEntity;
        }catch (Exception e){
            System.out.println("error ===>"+ e);
            return  new DeliveryEntity();
        }
    }

    public Result get(String id){
        Result result = new Result(200,"ok","");
        Optional<DeliveryEntity> deliveryEntity = deliveryDao.findById(id);

        Map data = new HashMap();
        data.put("delivery",deliveryEntity);
        result.setData(data);

        return result;
    }
}
