package com.wth.train.dao;

import com.wth.train.pojo.Usr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/*
Dao層、LoginMapperインターフェース
登録について、データ転送
*/
@Mapper
@Repository
public interface LoginMapper {
    //ユーザー検索する方法
    Usr queryUsr(@Param("userId")String userId,@Param("password") String password);
}
