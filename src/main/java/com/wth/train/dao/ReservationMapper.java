package com.wth.train.dao;

import com.wth.train.pojo.MeetingRoom;
import com.wth.train.pojo.ReservableRoom;
import com.wth.train.pojo.Reservation;
import com.wth.train.pojo.SumBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/*
Dao層、ReservationMapperインターフェース
予約機能について、データ転送
*/
@Mapper
@Repository
public interface ReservationMapper {
    //会議室のメッセージを取得
    List<MeetingRoom> getMeetingRoom(Date date);
    //予約に関して合わせ情報を取得する
    List<SumBean> queryByReservableRoom(ReservableRoom reservableRoom);
    //ルーム名が取得
    String queryRoomName(int roomId);
    //予約メッセージを削除する
    int deleteById(@Param("reservationId") int reservationId);
    //予約メッセージを追加する
    int addReservation(Reservation reservation);
}
