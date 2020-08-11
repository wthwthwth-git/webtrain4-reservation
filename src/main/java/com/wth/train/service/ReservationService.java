package com.wth.train.service;


import com.wth.train.pojo.DateRoom;
import com.wth.train.pojo.ReservableRoom;
import com.wth.train.pojo.Reservation;
import com.wth.train.pojo.TimeSumBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

/*
義務層、ReservationServiceインターフェース
機能について、転送したデータ処理
*/
@Service
public interface ReservationService {
    //会議室のメッセージを取得する
    DateRoom getMeetingRoom(int dateChange);
    //時間帯と合わせた予約メッセージを取得する
    TimeSumBean queryByReservableRoom(String reservedDate,int roomId) throws ParseException;
    //予約メッセージを削除
    int deleteById(int reservationId);
    //予約メッセージを追加
    int addReservation(Reservation reservation);
}
