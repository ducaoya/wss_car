package com.example.wsscarapi.service;

import com.example.wsscarapi.dao.CollectionDao;
import com.example.wsscarapi.entity.CollectionEntity;
import com.example.wsscarapi.tool.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectionService {
    @Resource
    private CollectionDao collectionDao;

    public Result add(CollectionEntity collection){
        Result result = new Result(200,"ok","");
        try{
            if(collectionDao.save(collection)!=null){
                Map data = new HashMap();
                data.put("collection",collection);
                result.setData(data);
            }else {
                result.setStatus("error");
                result.setMessage("收藏失败，请重试");
            }
        }catch (Exception e){
            System.out.println("error===>"+e);
            result.setStatus("error");
            result.setMessage("收藏失败，请重试");
        }

        return  result;
    }

    public Result getList(String user){
        Result result = new Result(200,"ok","");
        List<CollectionEntity> list = collectionDao.findAllByByUser(user);

        Map data = new HashMap();
        data.put("list",list);
        result.setData(data);

        return  result;
    }

    public Result del(String id){
        Result result = new Result(200,"ok","删除成功");
        try{
            collectionDao.deleteById(id);
        }catch (Exception e){
            System.out.println("error === > " + e);
            result.setStatus("error");
            result.setMessage("取消收藏失败，请重试");
        }

        return  result;
    }
}
