package com.wth.train.service;

import com.wth.train.pojo.Usr;
import org.springframework.stereotype.Service;

/*
義務層、LoginServiceインターフェース
登録について、転送したデータ処理
*/

@Service
public interface LoginService {
    //ユーザー検索
    Usr queryUsr(String userId,String password);
}
