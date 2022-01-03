package com.example.wsscarapi.service;

import com.example.wsscarapi.dao.CommentDao;
import com.example.wsscarapi.entity.CommentEntity;
import com.example.wsscarapi.tool.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {
    @Resource
    private CommentDao commentDao;

    public Result add(CommentEntity comment){
        Result result = new Result(200,"ok","");
        try{
            System.out.println("comment===>"+comment);
            if(commentDao.save(comment) != null){
                Map data = new HashMap();
                data.put("collection",comment);
                result.setData(data);
            }else {
                result.setStatus("error");
                result.setMessage("回复失败，请重试");
            }
        }catch (Exception e){
            System.out.println("error===>"+e);
            result.setStatus("error");
            result.setMessage("回复失败，请重试");
        }

        return  result;
    }

    public Result getList(String car){
        Result result = new Result(200,"ok","");
        List<CommentEntity> list = commentDao.findAllByCar(car);

        Map data = new HashMap();
        data.put("list",list);
        result.setData(data);

        return  result;
    }
}
