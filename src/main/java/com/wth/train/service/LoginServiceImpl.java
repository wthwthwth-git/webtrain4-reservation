package com.wth.train.service;

import com.wth.train.dao.LoginMapper;
import com.wth.train.pojo.Usr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
LoginServiceの実現クラス
*/
@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private LoginMapper loginMapper;
    //ユーザーidとパスワードで検索、ユーザーメッセージを取得
    public Usr queryUsr(String userId,String password){
        Usr usr = loginMapper.queryUsr(userId, password);
        return usr;
    }
}
