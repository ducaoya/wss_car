package com.example.wsscarapi.service;

import com.example.wsscarapi.dao.UserDao;
import com.example.wsscarapi.entity.UserEntity;
import com.example.wsscarapi.tool.Result;
import com.example.wsscarapi.tool.Token;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    //    检测用户是否存在
    public boolean checkUserExist(String username){

        return userDao.existsById(username);
    }

    //    注册
    public Result signup(UserEntity user){
        Result result = new Result(200,"ok","");
        if(this.checkUserExist(user.getUsername())){
            result.setMessage(result.getMessage()+",用户已存在");
            result.setStatus("User already exists");
        }else {
            if(userDao.save(user) != null){
                result.setMessage("注册成功");
                Map data=new HashMap();
                data.put("user",user);
                result.setData(data);
            }else {
                result.setStatus("signup failed");
                result.setMessage("注册失败，请重试");
            }
        }
        return  result;
    }

//    登录
    public Result login(String username , String password){
        Result result = new Result(200,"ok","");
        UserEntity user1 = userDao.findByPhone(username);
        UserEntity user2 = userDao.findByUsername(username);
        UserEntity user3 = new UserEntity();
        String ps = "";
        if(user1 != null){
            ps = user1.getPassword();
            user3=user1;
        }else if(user2 != null){
            ps = user2.getPassword();
            user3=user2;
        }else {
            result.setStatus("username or phone error");
            result.setMessage("用户名或电话错误");
        }

        if(ps.equals(password)){
            result.setMessage("登录成功");
            Token token = new Token();
            String tk = token.getAccessToken();
            Map data = new HashMap();
            data.put("token",tk);
            data.put("user",user3);
            result.setData(data);
        }else {
            result.setStatus("password error");
            result.setMessage("密码错误");
        }

        return result;
    }
}
